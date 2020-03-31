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

                $http.post(url, $scope.formInfo.Phone, config).then(function (response) {
                    if (response.data == "codeSended") {
                        alert("Код подтверждения отправлен");
                    }
                    else{}
                }, function error(response) {
                    $scope.postResultMessage = "Error with status: " + response.statusText;
                });

                // $scope.formInfo.Phone = "";
            } else{
                $scope.phoneRequired = 'Incorrect phone!';
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

app.controller('registerController', function($scope, $http, $location) {
    $scope.getfunction = function(){
        var url = $location.absUrl() + "getallcustomer";

        $http.get(url).then(function (response) {
            $scope.response = response.data
        }, function error(response) {
            $scope.postResultMessage = "Error with status: " +  response.statusText;
        });
    }
});