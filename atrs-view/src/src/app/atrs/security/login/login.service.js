(function() {
    'use strict';

    angular
        .module('atrs-module')
        .factory('loginService', loginService);
        

    /* @ngInject */
    function loginService($window, $http, API_CONFIG) {
        var service = {
            login : login,
            logout : logout,
            retrieveUserLoggedIn : retrieveUserLoggedIn
        };
        return service;
        
        function login(user) { 
            var credentials = $window.btoa(user.username+':'+user.password);  
        	var loginUrl = API_CONFIG.restAppName+'/oauth/token';
        	var params = 'grant_type=password&username='+user.username+'&password='+user.password;
        	return $http.post(loginUrl, params, { 
                headers : { 
                    'Authorization': 'Basic ' + credentials,
                    'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'  
                }                
            })
        };	
        
        function logout() {  
        	return $http.post('/rest/logout');
        };	
        
        function retrieveUserLoggedIn() {  
        	return $http.get('/rest/user/retrieve');
        };	
    }
})();