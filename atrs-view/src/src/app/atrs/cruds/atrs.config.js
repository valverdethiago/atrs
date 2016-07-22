(function() {
    'use strict';

    var module = angular
        .module('atrs-module')
        .config(rootModuleConfig);
    
    var util = {
    	defaultPageRequest : {
           	itensPerLine : 3,
            pageNumber : 1,
            pageSize : 6,
            offset : 0
    	},
    	isUndefinedOrNull : isUndefinedOrNull, 
    	isNotNull : isNotNull,
    	adjustSearchResultForGrid : adjustSearchResultForGrid,
    	showMessage : showMessage
    	
    };
    
    module.constant('util', util);
    

    /* @ngInject */
    function rootModuleConfig($translatePartialLoaderProvider, $stateProvider, $httpProvider, paginationTemplateProvider) {
        $translatePartialLoaderProvider.addPart('app/atrs');
        $httpProvider.interceptors.push(authInterceptor);
        $stateProvider
        .state('projects', {
            parent : 'triangular.admin-default',
            data: {
                layout: {
                    sideMenuSize: 'icon'
                }
            },
            url: '/projects',
            templateUrl: 'app/atrs/cruds/projects.html',
            controller: 'ProjectController',
            controllerAs: 'projectController'
        })
        .state('login', {
        	url : '/login',
        	templateUrl : 'app/atrs/security/login/login.tmpl.html',
            controller: 'LoginController',
            controllerAs: 'loginController'
        });        
        paginationTemplateProvider.setPath('app/atrs/util/new-pagination.tpl.html');         
    };
    
    /** @ngInject */
    function authInterceptor($log, $q, $location, $localStorage) {
    	return {
    		'request': function (config) {
    			config.headers = config.headers || {};
    			if ($localStorage.token) {
    				config.headers.Authorization = 'Bearer ' + $localStorage.token;
    			}
    			return config;
    		},
		    'responseError' : function(rejection) {
			    if (rejection.status == 401 || rejection.status == 403) {
			    	if (rejection.config.url == 'rest/security/login') {
			    		return $q.reject(rejection);
				   	}
	            	$localStorage.$reset();
				    $location.url('/login');
			    }
			    return $q.reject(rejection);
		    }
	    };
    };	
			
    function adjustSearchResultForGrid(searchResult, pageRequest) {	
		searchResult.limit = (searchResult.number + 1) * searchResult.numberOfElements;        		
    	if( searchResult.content.length < pageRequest.itensPerLine ) {
    		searchResult.columns = new Array(searchResult.content.length);        		
    	}
    	if( searchResult.content.length >= pageRequest.itensPerLine ) {
    		searchResult.columns = new Array(pageRequest.itensPerLine);
    	}
    	searchResult.content.forEach(function (value, i) {
    		var i_x = i % pageRequest.itensPerLine;
    		var array = searchResult.columns[i_x];
    		if(typeof array == 'undefined') {
    			array = new Array();
    			searchResult.columns[i_x] = array;
    		}
    		array.push(value);
    	});
    };
    
    function isNotNull(val) {
	    return !(val === null); 
	}
    
    function isUndefinedOrNull(val) {
	    return angular.isUndefined(val) || val === null; 
	}
    
    function showMessage($mdToast, message) {
    	$mdToast.show(
    		$mdToast.simple()
    	    	.textContent(message)
    	        .position('top right')
    	        .hideDelay(4000)
    	);            	  
    }
    
    
    
})();