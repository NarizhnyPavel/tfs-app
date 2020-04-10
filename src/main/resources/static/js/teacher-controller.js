var app = angular.module('teacherApp', []);

app.controller('teacherController', function ($scope, $http, $location, $window) {
    // $scope.name = "hello";
    var user = $window.localStorage.userInfo;
    $scope.name = user.name;
});
