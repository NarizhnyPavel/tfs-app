var app = angular.module('studentApp', []);
app.controller('student-controller', function ($scope, $http, $location, $window) {
    $scope.name = $window.localStorage.getItem("name").name;
});