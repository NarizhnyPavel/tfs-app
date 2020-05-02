'use strict';
var app = angular.module('addUniversityApp', []);
app.controller('control', function ($scope, $http, $location, $window) {
    $scope.uniData = {};
    restore_main($scope);

    $scope.uniData.Name = "LETI";
    $scope.uniData.Weeks = 2;
    $scope.uniData.Duration = 90;
    $scope.uniData.Color = "#FFFFFF";
    $scope.uniData.Logo = "https://yadi.sk/d/someurl";
    $scope.lessonGrid1 = "08:00";
    $scope.lessonGrid2 = "09:50";
    $scope.lessonGrid3 = "11:20";
    $scope.lessonGrid4 = "13:45";
    $scope.lessonGrid5 = "15:35";

    $scope.return = function(){
        restore_main($scope);
        checkFields($scope);
    }

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
            //TODO check lessonGrid
            if(true){
                send($scope, $http);
            }
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

function restore_main($scope){
    $scope.buttonLabel = "Далее";
    $scope.viewPage1 = true;
    $scope.viewPage2 = false;
    $scope.returnShow = false;
}

function send($scope, $http){
    var workdays_data = 0;
    if ($scope.uniData.WD_mon)
        workdays_data = workdays_data * 10 + 1;
    if ($scope.uniData.WD_tue)
        workdays_data = workdays_data * 10 + 2;
    if ($scope.uniData.WD_wed)
        workdays_data = workdays_data * 10 + 3;
    if ($scope.uniData.WD_thu)
        workdays_data = workdays_data * 10 + 4;
    if ($scope.uniData.WD_fri)
        workdays_data = workdays_data * 10 + 5;
    if ($scope.uniData.WD_sat)
        workdays_data = workdays_data * 10 + 6;
    if ($scope.uniData.WD_sun)
        workdays_data = workdays_data * 10 + 7;
    var lessons = {
        position1: $scope.lessonGrid1,
        position2: $scope.lessonGrid2,
        position3: $scope.lessonGrid3,
        position4: $scope.lessonGrid4,
        position5: $scope.lessonGrid5,
        position6: $scope.lessonGrid6,
        position7: $scope.lessonGrid7
    };
    var data = {
        name: $scope.uniData.Name,
        weeks: $scope.uniData.Weeks,
        workDays: workdays_data,
        lessonDuration: $scope.uniData.Duration,
        color: $scope.uniData.Color,
        logo: $scope.uniData.Logo,
        lessonGridPosition: lessons
    };
    var config = {
        headers: {
            'Content-Type': 'application/json'
        }
    };
    var url = "/university/add";
    $http.post(url, data, config).then(function (response) {
        alert("вроде success");
    }, function error(response) {
        alert("Error with status: " + response.statusText);
    });
}

function showHide(element_id) {
    var universityInfo = document.getElementById('universityInfo');
    universityInfo.style.display = "none";
    // var search = document.getElementById('search');
    // search.style.display = "none";
    // var settings = document.getElementById('settings');
    // settings.style.display = "none";
    var obj = document.getElementById(element_id);
    if (obj.style.display != "block") {
        obj.style.display = "block"; //Показываем элемент
    }
}