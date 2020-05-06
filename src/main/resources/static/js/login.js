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

    var config = {
        headers : {
            'Accept': 'text/plain'
        }
    }

    $scope.login = function () {
        if (!$scope.enterCode || $scope.buttonLabel === "Отправить код заново") {
            if (!$scope.formInfo.Phone) {
                $scope.phoneRequired = 'Phone Required';
            } else {
                if ($scope.formInfo.Phone.length === 11) {
                    var url = $location.absUrl() + "login/login";

                    $scope.buttonLabel = "Войти";
                    $http.post(url, $scope.formInfo.Phone, config).then(function (response) {
                        if (response.data === "codeSent") {
                            $scope.enterCode = true;
                            $scope.postResultMessage = "";
                            $scope.openRegistrationButton = false;
                            $scope.postResultMessage = "Код подтверждения отправлен";
                            angular.element('.errorMessage').css('color', "black");
                            document.getElementById('inputPhone').readOnly = true;
                            document.getElementById('inputCode').readOnly = false;
                            angular.element('.loginBlock').css('height', "300px");
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
            var config1 = {
                headers : {
                    'Content-Type': 'application/json'
                }
            }
            var data = {
                phone: $scope.formInfo.Phone,
                code: $scope.formInfo.Code
            };

            $http.post($location.absUrl() + "login/checkCode", data, config1).then(function (response) {
                var data2 = response.data;
                if (data2.id !== 0) {
                    $window.localStorage.user = data2;
                    var link;
                    // switch (data2.role) {
                    //     case 1:
                    //         link = angular.element('<a href="main/administrator.html"></a>');
                    //         break;
                    //     case 2:
                    //         link = angular.element('<a href="main/teacher.html"></a>');
                    //         break;
                    //     case 3:
                    //         link = angular.element('<a href="main/superStudent.html"></a>');
                    //         break;
                    //     case 4:
                    //         link = angular.element('<a href="main/student.html"></a>');
                    //         break;
                    // }
                    link = angular.element('<a href="main/homepage.html"></a>');
                    angular.element(document.body).append(link);
                    link[0].click();
                    link.remove();
                } else {
                    angular.element('.errorMessage').css('color', "red");
                    $scope.postResultMessage = 'Неверный код подтверждения';
                    $scope.errorShow = true;
                    // отправить код заново
                    document.getElementById('inputCode').readOnly = true;
                    $scope.buttonLabel = "Отправить код заново";
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
            $http.post(url + "login/checkPhone", $scope.formInfo.Phone, config).then(function (response) {
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
                phone: $scope.formInfo.Phone,
                group: $scope.formInfo.Group,
                name: $scope.formInfo.Name,
                role: 4
            };
            $http.post(url + "login/register", data, config).then(function (response) {
                if (response.data === "success") {
                    alert("successful registered");
                    $window.location.reload();
                }
                if (response.data === "groupError") {
                    $scope.postResultMessage = "Неверный номер группы!";
                    angular.element('#inputGroup').css('border-color', "red");
                    angular.element('.loginBlock').css('height', "310px");
                }
            }, function error(response) {
                $scope.postResultMessage = "Error with status: " + response.statusText;
            });
        }
    }
});


