(function() {
    'use strict';

    angular
        .module('atrs-module')
        .factory('loginService', loginService);
        

    /* @ngInject */
    function loginService($http, API_CONFIG) {
        var service = {
            login : login,
            logout : logout,
            retrieveUserLoggedIn : retrieveUserLoggedIn
        };
        return service;
        
        function login(user, credentials) {  
        	var loginUrl = API_CONFIG.restAppName+'/oauth/token';
        	var params = 'grant_type=password&username='+user.username+'&password='+user.password;
        	return $http.post(loginUrl, params, { 
                headers : { 
                    'Authorization': 'Basic ' + credentials,
                    'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'  
                }
                
            })
        };	
        
        function retrieveUserLoggedIn() {  
        	return $http.get(API_CONFIG.restAppName+'/user/retrieve');
        };	
        
        function logout() {
        	return $http.get(urlBase+'/logout');
        };
    }
})();