'use strict';
/* Controllers */
var app = angular.module('myApp', []);
app.controller('loginController', function ($scope, $http, $location) {
    $scope.formInfo = {};
    $scope.login = function () {
        $scope.phoneRequired = '';

        if (!$scope.formInfo.Phone) {
            $scope.phoneRequired = 'Phone Required';
        }
        else {
            if ($scope.formInfo.Phone.length == 11) {
                var url = $location.absUrl() + "login";
                var config = {
                    headers: {
                        'Accept': 'text/plain'
                    }
                }

                var data = {
                    phone: $scope.formInfo.Phone
                };
                $http.post(url, data, config).then(function (response) {
                    $scope.postResultMessage = response.data;
                }, function error(response) {
                    $scope.postResultMessage = "Error with status: " + response.statusText;
                });
            }
        }
    }
});

app.controller('getcontroller', function($scope, $http, $location) {
    $scope.getfunction = function(){
        var url = $location.absUrl() + "getallcustomer";

        $http.get(url).then(function (response) {
            $scope.response = response.data
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });
    }
});