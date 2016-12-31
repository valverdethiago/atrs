(function() {
    'use strict';

    angular
        .module('atrs-module')
        .controller('FlightController', FlightController);
        

    /* @ngInject */
    function FlightController($scope, $mdDialog, $mdMedia, $mdToast, $translate, flightService, util) {
        var flightController = this;
        
        flightController.find = find;
        flightController.onPageChange = onPageChange;
        flightController.detail = detail;
        flightController.createNew = createNew;
        flightController.open = open;
        flightController.save = save;
        flightController.closeDialog = closeDialog;
        flightController.openDialog = openDialog;
        flightController.activate = activate;
        flightController.archive = archive;
        flightController.confirmArchivation = confirmArchivation;
        flightController.init = init;
        flightController.canArchive = canArchive;
        flightController.canActivate = canActivate;
        
        
        function init() {
            angular.copy(util.defaultPageRequest, flightController.pageRequest = {});
        	flightController.pageRequest.onlyActives = true;
        	flightController.find();    
        	$scope.$watch('fc.pageRequest.searchTerm', function(newValue, oldValue) {
        		if(util.isUndefinedOrNull(newValue)) {
        			return;
        		}
        		if(newValue.length >3) {
        			flightController.find();
        		}
        	});
        	$scope.$watch('fc.pageRequest.onlyActives', function(newValue, oldValue) {
    			flightController.find();
        	});
        };
        
        function find() {        
            flightService.find(flightController.pageRequest)
        	.success(function(searchResult) {
        		util.adjustSearchResultForGrid(searchResult, flightController.pageRequest);
        		flightController.searchResult = searchResult;  
        	})
        	.error(function (error) {
        		console.log(error);
        	});        	
        };
        
        function onPageChange(newPageNumber) {
        	var pageRequest = flightController.pageRequest;
        	pageRequest.pageNumber = newPageNumber;
        	flightController.pageRequest.offset = pageRequest.pageSize * (newPageNumber - 1);
            flightController.find();   
        };
        
        function detail(event, entity) {
            angular.copy(entity, flightController.entity = {});
            openDialog(event);
        };
        
        function createNew(event) {
            flightController.entity = {};
            openDialog(event);
        }
        
        function open(event, entity) {
        	util.showMessage($mdToast, $translate.instant('CRUDS.FLIGHTS.MESSAGES.NOT_IMPLEMENTED'));
        }
        
        function save(entity) {        
            flightService.save(entity)
        	.success(function(result) {
            	util.showMessage($mdToast, $translate.instant('CRUDS.FLIGHTS.MESSAGES.SUCCESS.SAVE'));
            	closeDialog();
                find();   
        	})
        	.error(function (error) {
            	util.showMessage($mdToast, $translate.instant('CRUDS.FLIGHTS.MESSAGES.ERROR'));
        	});        	
        };
        
        function confirmArchivation(event, entity) {
            var confirm = $mdDialog.confirm()
                  .title($translate.instant('CRUDS.FLIGHTS.MESSAGES.CONFIRMATION.ARCHIVE'))
                  .textContent($translate.instant('CRUDS.FLIGHTS.MESSAGES.CONFIRMATION.ARCHIVE_EXP'))
                  .ariaLabel($translate.instant('CRUDS.FLIGHTS.DIALOG.TITLE.CONFIRMATION'))
                  .targetEvent(event)
                  .ok($translate.instant('CRUDS.FLIGHTS.LABELS.BUTTONS.OK'))
                  .cancel($translate.instant('CRUDS.FLIGHTS.LABELS.BUTTONS.CANCEL'));
            $mdDialog.show(confirm).then(function() {
            	flightController.archive(entity);
            }, function() {
            	detail(event, entity);
            });
        }
        
        function archive(entity) {        
            flightService.archive(entity)
        	.success(function(result) {
            	util.showMessage($mdToast, $translate.instant('CRUDS.FLIGHTS.MESSAGES.SUCCESS.ARCHIVE'));
            	closeDialog();
                flightController.find();    
        	})
        	.error(function (error) {
            	util.showMessage($mdToast, $translate.instant('CRUDS.FLIGHTS.MESSAGES.ERROR') );
        	});        	
        };
        
        function canArchive(entity) {
        	if(util.isUndefinedOrNull(entity) || util.isUndefinedOrNull(entity.id)) {
        		return false;
        	}
        	return util.isUndefinedOrNull(entity.archivationDate);
        };
        
        function canActivate(entity) {
        	if(util.isUndefinedOrNull(entity) || util.isUndefinedOrNull(entity.id)) {
        		return false;
        	}
        	return !util.isUndefinedOrNull(entity.archivationDate) ;
        }
        
        function activate(event, entity) {        
            flightService.activate(entity)
        	.success(function(result) {
            	util.showMessage($mdToast, $translate.instant('CRUDS.FLIGHTS.MESSAGES.SUCCESS.ACTIVATE'));
            	closeDialog();
                find();   
        	})
        	.error(function (error) {
            	util.showMessage($mdToast, $translate.instant('CRUDS.FLIGHTS.MESSAGES.ERROR') );
        	});        	
        };
        
        function openDialog(event) {
            $mdDialog.show({ 
                controller: DialogController,
                templateUrl: 'app/atrs/cruds/flight_detail.html',
                parent: angular.element(document.body),
                targetEvent: event,
                clickOutsideToClose: true,
                fullscreen: ($mdMedia('sm') || $mdMedia('xs'))  && $scope.customFullscreen,
                locals: {
                	flightController : flightController,
                	util : util
                }
              });        	
        };
        
        function closeDialog() {
            $mdDialog.hide();
        };
        
        function DialogController($scope, flightController, util) {
            $scope.controller = flightController;  
            $scope.util = util;            
        };
                
    }
})();