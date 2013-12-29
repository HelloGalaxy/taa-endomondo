'use strict';

angular.module('yoApp', ['ngResource','ngCookies'])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main2.html',
        controller: 'UserCtrl'
      })
      .when('/displayusers', {
        templateUrl: 'views/inscritsPage.html',
        controller: 'UserCtrl'
      })
      .when('/displayplans', {
        templateUrl: 'views/plansPage.html',
        controller: 'PlanCtrl'
      })
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'UserCtrl'
      })
      .when('/logged', {
        templateUrl: 'views/loggeduser.html',
        controller: 'UserCtrl'
      })
      .when('/signup', {
        templateUrl: 'views/signUp.html',
        controller: 'UserCtrl'
      })
      .when('/sendmssg', {
        templateUrl: 'views/messageForm.html',
        controller: 'UserCtrl'
      })
      .when('/routemapper', {
        templateUrl: 'views/vueRoute.html',
        controller: 'PlanCtrl'
      })
       .when('/newworkout', {
        templateUrl: 'views/workoutForm.html',
        controller: 'PlanCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
