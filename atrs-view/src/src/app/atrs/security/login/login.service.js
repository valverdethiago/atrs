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
        
        function login(user, credentials) { 
        	var loginUrl = API_CONFIG.restAppName+'/oauth/token';
        	var params = 'grant_type=password&username='+user.username+'&password='+user.password+"&client_id=ldap";
        	return $http.post(loginUrl, params, { 
                headers : { 
                    'Authorization': 'Basic ' + credentials,
                    'Content-type': 'application/x-www-form-urlencoded; charset=utf-8'  
                }                
            })
        };	
        
        /*
         function login(user) { 
             var data = {grant_type:"password", username: "admin", password: "admin", client_id: "admin"};
        	 var encoded = $window.btoa("clientIdPassword:admin");     
        	 var req = {
	            method: 'POST',
	            url: "http://localhost:3000/rest/oauth/token",
	            headers: {
	                "Authorization": "Basic " + encoded,
	                "Content-type": "application/x-www-form-urlencoded; charset=utf-8"
	            },
	            data: $httpParamSerializer(data)
	        }
	        return $http(req); 
        };	
         */
        
        function logout() {  
        	return $http.post('/rest/logout');
        };	
        
        function retrieveUserLoggedIn() {  
        	return $http.get('/rest/user/retrieve');
        };	
    }
})();