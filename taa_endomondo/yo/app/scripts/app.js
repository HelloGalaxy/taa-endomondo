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
       .when('/displaymyfriends', {
        templateUrl: 'views/myFriendsList.html',
        controller: 'UserCtrl'
      })// myChallengesList
       .when('/displaymychallenges', {
        templateUrl: 'views/myChallengesList.html',
        controller: 'UserCtrl'
      })
      .when('/displayplans', {
        templateUrl: 'views/plansPage.html',
        controller: 'PlanCtrl'
      })//plansPageJoin
       .when('/displayplansJoin', {
        templateUrl: 'views/plansPageJoin.html',
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
      .when('/addfriend', {
        templateUrl: 'views/personListAsFriends.html',
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
