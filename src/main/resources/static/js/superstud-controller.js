var app = angular.module('superstudApp', []);
app.controller('superstud-controller', function ($scope, $http, $location, $window) {
    $scope.name = $window.localStorage.getItem("name");
});