'use strict';
var app = angular.module('pgApp', []);
app.controller('addUniControl', function ($scope, $http) {});

app.directive('addUniversity', function () {
    return {
        controller: function ($scope, $http) {
            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http.get('/university', config).then(function (response) {
                $scope.uniData = response.data;
                alert(university.uniData.color1)
            });
            restore_main($scope);

            // $scope.uniData.name = "СПбГЭТУ 'ЛЭТИ' им.Ульянова-Ленина";
            // $scope.uniData.weeks = 2;
            // $scope.uniData.Duration = 90;
            // $scope.uniData.сolor1 = '#' + $scope.uniData.сolor1;
            // $scope.uniData.color2 = '#' + $scope.uniData.сolor2;
            // $scope.uniData.color3 = '#' + $scope.uniData.сolor3;
            // $scope.uniData.Logo = "https://yadi.sk/d/someurl";
            // $scope.lessonGrid1 = $scope.uniData.lessonGridPosition.position1;
            // $scope.lessonGrid2 = $scope.uniData.lessonGridPosition.position2;
            // $scope.lessonGrid3 = $scope.uniData.lessonGridPosition.position3;
            // $scope.lessonGrid4 = $scope.uniData.lessonGridPosition.position4;
            // $scope.lessonGrid5 = $scope.uniData.lessonGridPosition.position5;
            // $scope.lessonGrid6 = $scope.uniData.lessonGridPosition.position6;
            // $scope.lessonGrid7 = $scope.uniData.lessonGridPosition.position7;

            $scope.return = function(){
                restore_main($scope);
                checkFields($scope);
            }

            $scope.button_click = function () {
                if ($scope.viewPage1){
                    if ((($scope.uniData.name)&&($scope.uniData.weeks)&&
                        ($scope.uniData.lessonDuration)&&($scope.uniData.color1)&&($scope.uniData.color2)&&($scope.uniData.color3))
                        // && (($scope.uniData.WD_mon)||($scope.uniData.WD_tue)
                        // ||($scope.uniData.WD_wed)||($scope.uniData.WD_thu)
                        // ||($scope.uniData.WD_fri)||($scope.uniData.WD_sat)
                        // ||($scope.uniData.WD_sun))
                        ){

                        $scope.viewPage1 = false;
                        $scope.viewPage2 = true;
                        $scope.returnShow = true;
                        $scope.buttonLabel = "Сохранить";
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
        }
        , restrict: "E"
        , templateUrl: "../templates/universitySettingsBlock.html"
    }
});

function checkFields($scope) {
    if (!$scope.uniData.name) {
        angular.element('#name').css('border-color', "red");
    }
    else
        angular.element('#name').css('border', "2px solid #cecece");
    if (!$scope.uniData.weeks)
        angular.element('#weeks').css('border-color', "red");
    else
        angular.element('#weeks').css('border', "2px solid #cecece");
    if (!$scope.uniData.lessonDuration)
        angular.element('#duration').css('border-color', "red");
    else
        angular.element('#duration').css('border', "2px solid #cecece");
    if (!$scope.uniData.color1)
        angular.element('#color1').css('border-color', "red");
    else
        angular.element('#color1').css('border', "2px solid #cecece");
    if (!$scope.uniData.color2)
        angular.element('#color2').css('border-color', "red");
    else
        angular.element('#color2').css('border', "2px solid #cecece");
    if (!$scope.uniData.color3)
        angular.element('#color3').css('border-color', "red");
    else
        angular.element('#color3').css('border', "2px solid #cecece");
    // if (!$scope.uniData.Logo)
    //     angular.element('#url').css('border-color', "red");
    // else
    //     angular.element('#url').css('border', "2px solid #cecece");
    if (!(($scope.uniData.workDays.monday)||($scope.uniData.workDays.tuesday)
        ||($scope.uniData.workDays.wednesday)||($scope.uniData.workDays.thursday)
        ||($scope.uniData.workDays.friday)||($scope.uniData.workDays.saturday)
        ||($scope.uniData.workDays.sunday))) {
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
    // var workdays_data = 0;
    // if ($scope.uniData.workDays.monday)
    //     workdays_data = workdays_data * 10 + 1;
    // if ($scope.uniData.workDays.tuesday)
    //     workdays_data = workdays_data * 10 + 2;
    // if ($scope.uniData.workDays.wednesday)
    //     workdays_data = workdays_data * 10 + 3;
    // if ($scope.uniData.workDays.thursday)
    //     workdays_data = workdays_data * 10 + 4;
    // if ($scope.uniData.workDays.friday)
    //     workdays_data = workdays_data * 10 + 5;
    // if ($scope.uniData.workDays.saturday)
    //     workdays_data = workdays_data * 10 + 6;
    // if ($scope.uniData.workDays.sunday)
    //     workdays_data = workdays_data * 10 + 7;
    var lessons = {
        position1: $scope.uniData.lessonGridPosition.position1,
        position2: $scope.uniData.lessonGridPosition.position2,
        position3: $scope.uniData.lessonGridPosition.position3,
        position4: $scope.uniData.lessonGridPosition.position4,
        position5: $scope.uniData.lessonGridPosition.position5,
        position6: $scope.uniData.lessonGridPosition.position6,
        position7: $scope.uniData.lessonGridPosition.position7
    };
    var data = {
        name: $scope.uniData.name,
        weeks: $scope.uniData.weeks,
        workDays: $scope.uniData.workDays,
        lessonDuration: $scope.uniData.lessonDuration,
        color1: $scope.uniData.color1,
        color2: $scope.uniData.color2,
        color3: $scope.uniData.color3,
        logo : "",
        lessonGridPosition: lessons
    };
    var config = {
        headers: {
            'Content-Type': 'application/json'
        }
    };
    var url = "/university/update";
    $http.post(url, data, config).then(function (response) {
        alert("Сохранено");
    }, function error(response) {
        alert("Сохранено");
    });
}

function showHide(element_id) {
    var universityInfo = document.getElementById('universityInfo');
    universityInfo.style.display = "none";
    var obj = document.getElementById(element_id);
    if (obj.style.display != "block") {
        obj.style.display = "block"; //Показываем элемент
    }
}