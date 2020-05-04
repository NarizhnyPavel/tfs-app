let minWeekNum;
let maxWeekNum;
let lessonToView;
'use strict';

app.controller('timetableControl', function ($scope, $http) {
    $scope.info = "hello";
    $scope.infoShow = false;
    minWeekNum = 1;
    var config = {
        headers: {
            'Content-Type': 'application/json'
        }
    };
    $http.get('/university/weeks', config).then(function (response) {
        maxWeekNum = response.data;
    });
});


app.directive('grid', function () {
    return {
         controller: function ($scope, $attrs, $http) {
            $scope.week = 1;
            var data = {
                userId: 1
                , weekNum: 1
            };
            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            refresh_timetable();
            $scope.inc_week = function () {
                if ($scope.week < maxWeekNum) {
                    $scope.week++;
                    data.weekNum++;
                    refresh_timetable();
                }
            }
            $scope.dec_week = function () {
                if ($scope.week > minWeekNum) {
                    $scope.week--;
                    data.weekNum--;
                    refresh_timetable();
                }
            }
            $scope.show = function (lesson) {
                $scope.lessonToView = lesson;
                $scope.info = "Дисциплина: " + lesson.subject + "" +
                    "\n Тип: " + lesson.lessonType +
                    "\n Преподаватель: " + lesson.professor +
                    "\n Аудитория: " + lesson.classroom +
                    "\n Статус: " + lesson.status + "";
                $scope.infoShow = true;
            }
            function refresh_timetable() {
                $http.post('/lesson/info', data, config).then(function (response) {
                    $scope.days2 = response.data;
                    $scope.$apply();
                });
            }
        }
        , restrict: "E"
        , templateUrl: "../templates/timetable.html"
    }
});

app.directive('infoBox', function () {
    return {
        controller: function ($scope) {
            // $scope.lesson = lessonToView;
            // $scope.show = true;
            $scope.closeInfo = function () {
                $scope.infoShow = false;
                $scope.$apply();
            }
        }
        , restrict: "E"
        , templateUrl: "../templates/modalWindow.html"
    }
});