(function() {
    'use strict';

    angular
        .module('atrs-module')
        .factory('flightService', FlightService);
        

    /* @ngInject */
    function FlightService($http, API_CONFIG) {
    	var urlBase = API_CONFIG.restAppName+'/flight' ;
        var service = {
            activate: activate,
        	archive: archive,
            save: save,
            load: load,
            remove: remove,
            find: find,
            list: list
        };
        return service;
        
        function save(flight) {
        	return $http.post(urlBase+'/save', flight);
        };
        
        function archive(flight) {
        	return $http.post(urlBase+'/archive', flight);
        };
        
        function activate(flight) {
        	return $http.post(urlBase+'/activate', flight);
        };

        function load (id) {
        	return $http.put(urlBase+'/load/' + id);
        };

        function remove (id) {
            return $http.delete(urlBase + '/delete/' + id);
        };

        function list () {
            return $http.get(urlBase + '/list');
        };

        function find (searchObject) {
            return $http.post(urlBase + '/find', searchObject);
        };
    }
})();