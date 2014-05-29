var selfReportApp = angular.module('selfReportApp',['ngRoute', 'sreportControllers']);

selfReportApp.config(function ($routeProvider) {
    $routeProvider
        .when( '/',
        {
            controller: 'TopicsController',
            templateUrl: release + '/js/topics.html'
        })
        .when( '/createObservation/:topicId',
        {
            controller: 'CreateObservationController',
            templateUrl: release + '/js/createObservation.html'
        })
        .when( '/editTopics',
        {


        })
        .when( '/history',
        {


        })
        .otherwise({ redirectTo: '/' });
    });
