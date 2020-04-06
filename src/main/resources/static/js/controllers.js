/* Controllers */
'use strict';
var app = angular.module('myApp', []);
app.controller('loginController', function ($scope, $http, $location, $window) {
    $scope.formInfo = {};
    $scope.enterCode = false;
    $scope.errorShow = false;
    $scope.enterLogin = true;
    $scope.openRegistrationButton = false;
    $scope.registartionFields = false;
    $scope.buttonLabel = "Войти/Зарегистрироваться";
    $scope.postResultMessage = '';

    $scope.login = function () {
        if (!$scope.enterCode) {
            if (!$scope.formInfo.Phone) {
                $scope.phoneRequired = 'Phone Required';
            } else {
                if ($scope.formInfo.Phone.length === 11) {
                    var url = $location.absUrl() + "login";
                    var config = {
                        headers: {
                            'Accept': 'text/plain'
                        }
                    };

                    $scope.buttonLabel = "Войти";
                    $http.post(url, $scope.formInfo.Phone, config).then(function (response) {
                        if (response.data === "codeSended") {
                            $scope.enterCode = true;
                            $scope.postResultMessage = "";
                            $scope.openRegistrationButton = "false";
                            // alert("Код подтверждения отправлен");
                            $scope.postResultMessage = "Код подтверждения отправлен";
                            angular.element('.errorMessage').css('color', "black");
                            document.getElementById('inputPhone').readOnly = true;
                            angular.element('.loginBlock').css('height', "260px");
                            $scope.errorShow = true;
                        } else if (response.data === "registrationNeeded"){
                            $scope.postResultMessage = "Пользователь с таким телефоном не зарегистрирован";
                            $scope.errorShow = true;
                            $scope.openRegistrationButton = true;
                            angular.element('.loginBlock').css('height', "230px");
                        }
                    }, function error(response) {
                        $scope.postResultMessage = "Error with status: " + response.statusText;
                    });
                } else {
                    $scope.postResultMessage = 'Неверный формат телефона';
                    $scope.errorShow = true;
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
                $window.location.reload();
                if (response.data !== "code error") {
                } else if (response.data === "code error") {
                    angular.element('.errorMessage').css('color', "red");
                    $scope.postResultMessage = 'Неверный код подтверждения';
                    $scope.errorShow = true;
                    // отправить код заново
                }
            }, function error(response) {
                $scope.postResultMessage = "Error with status: " + response.statusText;
            });
        }
    }
    $scope.registrateOpen = function () {
        var url = $location.absUrl();
        var config = {
            headers: {
                'Accept': 'text/plain'
            }
        };
        if ($scope.registartionFields === false) {
            $http.post(url + "checkPhone", $scope.formInfo.Phone, config).then(function (response) {
                if (response.data === "registered") {
                    $scope.postResultMessage = "Пользователь с таким телефоном уже зарегистрирован";
                    $scope.errorShow = true;
                } else {
                    $scope.registartionFields = true;
                    $scope.enterLogin = false;
                    $scope.postResultMessage = "";
                    $scope.errorShow = false;
                    document.getElementById('inputPhone').readOnly = true;
                    angular.element('.loginBlock').css('height', "300px");
                }
            }, function error(response) {
                $scope.postResultMessage = "Error with status: " + response.statusText;
            });
        }else{
            var data = {
                id: 0,
                phone: $scope.formInfo.Phone,
                name: $scope.formInfo.Name,
                role: 0,
                groupNumber: $scope.formInfo.Group
            };
            $http.post(url + "register", data, config).then(function (response) {
                if (response.data === "success") {
                    alert("successful registered");
                    $window.location.reload();
                } else {
                    $scope.postResultMessage = response.data;
                    $scope.errorShow = false;
                    document.getElementById('inputPhone').readOnly = true;
                    angular.element('.loginBlock').css('height', "300px");
                }
            }, function error(response) {
                $scope.postResultMessage = "Error with status: " + response.statusText;
            });
        }
    }
});



