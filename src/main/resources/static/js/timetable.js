//let week;
let minWeekNum;
let maxWeekNum;
let lessonToView;
var infoShow;
'use strict';
var app = angular.module('homepg', []);
app.controller('timetableCtrl', function ($scope, $http) {
    minWeekNum = 1;
    $scope.infoShow = true;
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
        scope: {}
        , controller: function ($scope, $attrs, $http) {
            $scope.week = 1;
            var data = {
                userId: 41
                , role: 4
                , weekNum: $scope.week
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
                    refresh_timetable();
                }
            }
            $scope.dec_week = function () {
                if ($scope.week > minWeekNum) {
                    $scope.week--;
                    refresh_timetable();
                }
            }
            $scope.show = function (lesson) {
                alert("name: " + lesson.name + "\ntype: " + lesson.type, 'title');
            }
            $scope.days = [
                {
                    dayName: "Понедельник"
                    , lessons: [
                        {
                            time: "9:50"
                            , name: "TPR1"
                            , type: "лекция"
                        }
                        , {
                            time: "11:40"
                            , name: "MiSPIS"
                            , type: "практика"
                        }
                        , {
                            time: "13:35"
                            , name: "Sociology"
                            , type: "семинар"
                        }
                        , ]
                    , }
                , {
                    dayName: "Вторник"
                    , lessons: [
                        {
                            time: "9:50"
                            , name: "TPR2"
                            , type: "лекция"
                        }
                        , {
                            time: "11:40"
                            , name: "MiSPIS"
                            , type: "практика"
                        }
                        , {
                            time: "13:35"
                            , name: "Sociology"
                            , type: "семинар"
                        }
                        , {
                            time: "13:35"
                            , name: "Sociology"
                            , type: "семинар"
                        }
                        , ]
                    , }
                , {
                    dayName: "Среда"
                    , lessons: [
                        {
                            time: "9:50"
                            , name: "TPR3"
                            , type: "лекция"
                        }
                        , {
                            time: "11:40"
                            , name: "MiSPIS"
                            , type: "практика"
                        }
                        , {
                            time: "13:35"
                            , name: "Sociology"
                            , type: "семинар"
                        }
                        , {
                            time: "13:35"
                            , name: "Sociology"
                            , type: "семинар"
                        }
                        , ]
                    , }
                , {
                    dayName: "Четверг"
                    , lessons: [
                        {
                            time: "9:50"
                            , name: "TPR4"
                            , type: "лекция"
                        }
                        , {
                            time: "11:40"
                            , name: "MiSPIS"
                            , type: "практика"
                        }
                        , {
                            time: "13:35"
                            , name: "Sociology"
                            , type: "семинар"
                        }

                        , {
                            time: "15:15"
                            , name: "WEbCOURSE"
                            , type: "семинар"
                        }, ]
                    , }
                , ];
            function refresh_timetable() {
                $http.post('/lesson/info', data, config).then(function (response) {
                    alert('successs');
                    $scope.days2 = response.data;
                });
            }
        }
        , restrict: "E"
        , templateUrl: "../templates/timetable.html"
    }
});
app.directive('message', function () {
    return {
        controller: function ($scope, $http) {
            $scope.lesson = lessonToView;
            $scope.show = true;
            $scope.close = function () {
                $scope.show = false;
            }
        }
        , restrict: "E"
        , templateUrl: "../templates/message.html"
    }
});

function getWeekNum() {
    var num = "1";
    return $scope.weekNumber;
}