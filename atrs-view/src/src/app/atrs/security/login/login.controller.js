(function() {
    'use strict';

    angular
        .module('atrs-module')
        .controller('LoginController', LoginController);

    /* @ngInject */
    function LoginController($location, $cookies, $translate, $window, $http, $mdToast, $auth, $localStorage, loginService, util) {
        var loginController = this;
        loginController.login = login;        
        loginController.logout = logout;      
        loginController.init = init;       
        loginController.retrieveUserInfo = retrieveUserInfo;
        // create blank user variable for login form
        loginController.user = {
            username: '',
            password: ''
        };
        loginController.clientId = "ldap";
        loginController.secret = "secret"
        loginController.credentials = $window.btoa(loginController.clientId+':'+loginController.secret); 

        function login() {
        	loginService.login(loginController.user, loginController.credentials)
        	.success(function(result) {
                $localStorage.token = result.access_token;
                loginController.retrieveUserInfo();
                $location.url('/');
        	})
        	.error(function (error) {
        		console.log(error);
                $localStorage.$reset();
            	util.showMessage($mdToast, $translate.instant('SECURITY.MESSAGES.INVALID_CREDENTIALS'));
        	});   
 
        }
        
        function retrieveUserInfo() {
        	loginService.retrieveUserLoggedIn()
        	.success(function(result) {
        		console.log(result);
                $localStorage.user = result;                
        	})
        	.error(function(error) {
            	util.showMessage($mdToast, $translate.instant('SECURITY.MESSAGES.RETRIEVE_USER_INFO_ERROR'));
        	});
        }

        function logout() {
            $localStorage.$reset();
            $location.url('/login');
        };
        
        function init() {
        	if(!util.isUndefinedOrNull($localStorage.token)) {
                $location.url('/');
        	}
        }
    }
        		
        	
    
})();