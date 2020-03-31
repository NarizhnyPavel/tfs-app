/* Controllers */
'use strict';
var app = angular.module('myApp', []);
app.controller('loginController', function ($scope, $http, $location) {
    $scope.formInfo = {};
    $scope.enterCode = false;
    $scope.enterLogin = true;
    $scope.buttonLabel = "Sign up/Register";
    $scope.phoneRequired = '';
    $scope.login = function () {
        if (!$scope.enterCode) {
            if (!$scope.formInfo.Phone) {
                $scope.phoneRequired = 'Phone Required';
            } else {
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
                            $scope.enterCode = true;
                            $scope.buttonLabel = "Sign up";
                        } else {
                            let tpl = document.querySelector('#some_template');
                            let container = document.querySelector('#container');
                            insertAfter(container, tpl.content);
                        }
                    }, function error(response) {
                        $scope.postResultMessage = "Error with status: " + response.statusText;
                    });


                } else {
                    $scope.phoneRequired = 'Incorrect phone!';
                }
            }
        } else{
            var config = {
                headers : {
                    'Accept': 'text/plain'
                }
            }
            var data = {
                phone: $scope.formInfo.Phone,
                code: $scope.formInfo.Code
            };

            $http.post($location.absUrl() + "checkCode", data, config).then(function (response) {
                alert(response.data);
                if (response.data == "logged") {
                }
            }, function error(response) {
                $scope.postResultMessage = "Error with status: " + response.statusText;
            });
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