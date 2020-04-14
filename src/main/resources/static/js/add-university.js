'use strict';
var app = angular.module('addUniversityApp', []);
app.controller('control', function ($scope, $http, $location, $window) {
    $scope.uniData = {};
    $scope.buttonLabel = "Далее";
    $scope.viewPage1 = true;
    $scope.viewPage2 = false;
    $scope.postResultMessage = "";
    $scope.returnShow = false;

    $scope.uniData.Name = "LETI";
    $scope.uniData.Weeks = 2;
    $scope.uniData.Duration = 90;
    $scope.uniData.Color = "#FFFFFF";
    $scope.uniData.Logo = "https://yadi.sk/d/someurl";


    $scope.button_click = function () {
        if ($scope.viewPage1){
        if ((($scope.uniData.Name)&&($scope.uniData.Weeks)&&
            ($scope.uniData.Duration)&&($scope.uniData.Color)&&
            ($scope.uniData.Logo))&&(($scope.uniData.WD_mon)||($scope.uniData.WD_tue)
            ||($scope.uniData.WD_wed)||($scope.uniData.WD_thu)
            ||($scope.uniData.WD_fri)||($scope.uniData.WD_sat)
            ||($scope.uniData.WD_sun))){

            $scope.viewPage1 = false;
            $scope.viewPage2 = true;
            $scope.returnShow = true;
            $scope.buttonLabel = "Зарегистрировать";
        }else{
            checkFields($scope);
        }}
        else{

        }
    }

});

function checkFields($scope) {
    if (!$scope.uniData.Name) {
        angular.element('#name').css('border-color', "red");
    }
    else
        angular.element('#name').css('border', "2px solid #cecece");
    if (!$scope.uniData.Weeks)
        angular.element('#weeks').css('border-color', "red");
    else
        angular.element('#weeks').css('border', "2px solid #cecece");
    if (!$scope.uniData.Duration)
        angular.element('#duration').css('border-color', "red");
    else
        angular.element('#duration').css('border', "2px solid #cecece");
    if (!$scope.uniData.Color)
        angular.element('#color').css('border-color', "red");
    else
        angular.element('#color').css('border', "2px solid #cecece");
    if (!$scope.uniData.Logo)
        angular.element('#url').css('border-color', "red");
    else
        angular.element('#url').css('border', "2px solid #cecece");
    if (!(($scope.uniData.WD_mon)||($scope.uniData.WD_tue)
        ||($scope.uniData.WD_wed)||($scope.uniData.WD_thu)
        ||($scope.uniData.WD_fri)||($scope.uniData.WD_sat)
        ||($scope.uniData.WD_sun))) {
        angular.element("#days").css('border', "2px solid red");
    } else
        angular.element('#days').css('border', "0");
}
