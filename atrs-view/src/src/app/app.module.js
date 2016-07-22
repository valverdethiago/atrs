(function() {
    'use strict';

    angular
        .module('app', [
            'triangular','ngAnimate', 'ngCookies', 'ngSanitize', 'ngMessages', 'ngMaterial',
            'ui.router', 'pascalprecht.translate', 'LocalStorageModule', 'textAngular',  
            'md.data.table', 'angularUtils.directives.dirPagination','ngStorage', 'atrs-module'
        ])
        // create a constant for languages so they can be added to both triangular & translate
        .constant('APP_LANGUAGES', [{
            name: 'LANGUAGES.ENGLISH',
            key: 'en'
        }])
        // set a constant for the API we are connecting to
        .constant('API_CONFIG', {
            url:  'http://localhost:3000/',
            restAppName : '/rest'
        })
        ;
})();