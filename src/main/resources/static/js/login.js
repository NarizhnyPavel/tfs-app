/* Controllers */
'use strict';
var serverUrl = "https://timeforstudyetu.herokuapp.com";
// var serverUrl = "http://localhost:8080";

var app = angular.module('myApp', []);

app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);

app.controller('loginController', function ($scope, $http, $location, $window) {
    document.getElementById('inputPhone').value = '';
    $scope.formInfo = {
        Phone: '',
        Name: "",
        Code: "",
        Group: "",
    };
    $scope.enterCode = false;
    $scope.errorShow = false;
    $scope.enterLogin = true;
    $scope.openRegistrationButton = false;
    $scope.registartionFields = false;
    $scope.buttonLabel = "Войти/Зарегистрироваться";
    $scope.postResultMessage = '';

    var config = {
        headers : {
            'Accept': 'text/plain',
        }
    }
    $http.get(serverUrl + '/university', {
        headers : {
            'Content-Type': 'application/json'
        }
    }).then(function (response) {
        $scope.university = response.data;
        $window.localStorage.setItem('color1', response.data.color1);
        $window.localStorage.setItem('color2', response.data.color2);
        $window.localStorage.setItem('color3', response.data.color3);
        angular.element(document.querySelector('.loginBlock')).css('backgroundColor', '#' + $window.localStorage.getItem("color1"));
        angular.element(document.querySelector('.loginHeader')).css('backgroundColor','#' + $window.localStorage.getItem("color2"));
        angular.element(document.querySelector('#buttonLogin')).css('backgroundColor','#' + $window.localStorage.getItem("color3"));
        angular.element(document.querySelector('#buttonRegister')).css('backgroundColor','#' + $window.localStorage.getItem("color3"));
    });
    $scope.login = function () {
        if (!$scope.enterCode || $scope.buttonLabel === "Отправить код заново") {
            if (!document.getElementById('inputPhone').value) {
                document.getElementById('inputPhone').valueRequired = 'Phone Required';
            } else {
                if (document.getElementById('inputPhone').value.length === 11) {
                    var url = serverUrl + "/login/login";
                    $scope.buttonLabel = "Войти";
                    $http.post(url, document.getElementById('inputPhone').value, config).then(function (response) {
                        if (response.data === "codeSent") {
                            $scope.enterCode = true;
                            $scope.postResultMessage = "";
                            $scope.openRegistrationButton = false;
                            $scope.postResultMessage = "Код подтверждения отправлен";
                            angular.element(document.querySelector('.errorMessage')).css('color', "white");
                            angular.element(document.querySelector('#inputPhone')).attr('readOnly', 'true');
                            angular.element(document.querySelector('.loginBlock')).css('height', '300px');
                            $scope.errorShow = true;
                        } else if (response.data === "registrationNeeded"){
                            $scope.postResultMessage = "Пользователь с таким телефоном не зарегистрирован";
                            $scope.errorShow = true;
                            $scope.openRegistrationButton = true;
                            angular.element(document.querySelector('.loginBlock')).css('height', '270px');
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
                phone: document.getElementById('inputPhone').value,
                code: document.getElementById('inputCode').value
            };

            $http.post(serverUrl + "/login/checkCode", data, config1).then(function (response) {
                var data2 = response.data;
                if (data2.id !== 0) {
                    localStorage.setItem("userId", data2.id);
                    localStorage.setItem("userName", data2.name);
                    localStorage.setItem("userRole", data2.role);
                    localStorage.setItem("userTel", document.getElementById('inputPhone').value);
                    var link;
                    link = angular.element('<a href="main/homepage.html"></a>');
                    angular.element(document.body).append(link);
                    link[0].click();
                    link.remove();
                } else {
                    angular.element('.errorMessage').css('color', "red");
                    $scope.postResultMessage = 'Неверный код подтверждения';
                    $scope.errorShow = true;
                    // отправить код заново
                    angular.element(document.querySelector('#inputCode')).attr('readOnly', 'true');
                    $scope.buttonLabel = "Отправить код заново";
                }
            }, function error(response) {
                $scope.postResultMessage = "Error with status: " + response.statusText;
            });
        }
    }
    $scope.registrateOpen = function () {
        angular.element(document.querySelector('.loginBlock')).css('height', '300px');
        var config = {
            headers: {
                'Accept': 'text/plain'
            }
        };
        if ($scope.registartionFields === false) {
            $http.post(serverUrl + "/login/checkPhone", document.getElementById('inputPhone').value, config).then(function (response) {
                if (response.data === "registered") {
                    angular.element(document.querySelector('.loginBlock')).css('height', '270px');
                    $scope.postResultMessage = "Пользователь с таким телефоном уже зарегистрирован";
                    $scope.errorShow = true;
                } else {
                    $scope.registartionFields = true;
                    $scope.enterLogin = false;
                    $scope.postResultMessage = "";
                    $scope.errorShow = false;
                    angular.element(document.querySelector('#inputPhone')).attr('readOnly', 'true');
                }
            }, function error(response) {
                $scope.postResultMessage = "Error with status: " + response.statusText;
            });
        }else{
            var data = {
                phone: document.getElementById('inputPhone').value,
                group: $scope.formInfo.Group,
                name: $scope.formInfo.Name,
                role: 4
            };
            $http.post(serverUrl + "/login/register", data, config).then(function (response) {
                if (response.data === "success") {
                    alert("successful registered");
                    $window.location.reload();
                }
                if (response.data === "groupError") {
                    $scope.postResultMessage = "Неверный номер группы!";
                    angular.element(document.querySelector('#inputGroup')).css('border-color', "red");
                    angular.element(document.querySelector('.loginBlock')).css('height', "330px");
                }
            }, function error(response) {
                $scope.postResultMessage = "Error with status: " + response.statusText;
            });
        }
    }
});


$.fn.setCursorPosition = function(pos) {
    if ($(this).get(0).setSelectionRange) {
        $(this).get(0).setSelectionRange(pos, pos);
    } else if ($(this).get(0).createTextRange) {
        var range = $(this).get(0).createTextRange();
        range.collapse(true);
        range.moveEnd('character', pos);
        range.moveStart('character', pos);
        range.select();
    }
};

