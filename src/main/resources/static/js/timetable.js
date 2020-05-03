let minWeekNum;
let maxWeekNum;
let lessonToView;
var infoShow;
'use strict';

app.controller('timetableControl', function ($scope, $http) {

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
                userId: 11
                , role: 3
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
                alert("name: " + lesson.subject +
                    "\ntype: " + lesson.lessonType+
                    "\nProfessor: " + lesson.professor+
                    "\nClassroom: " + lesson.classroom+
                    "\nstatus: " + lesson.status);
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
                    $scope.days2 = response.data;
                    $scope.$apply();
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