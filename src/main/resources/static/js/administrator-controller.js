var app = angular.module('myApp', []);
app.controller('administrator-controller', function ($scope, $http, $location, $window) {
    $scope.name = "hello";
    // $scope.name = $winndow.localStorage.getItem("name").name;
});