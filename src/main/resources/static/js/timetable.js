let weekNum;
let lessonToView;
var infoShow;
'use strict';
var app = angular.module('homepg', []);
app.controller('timetableCtrl', function ($scope, $http) {
    weekNum = 1;
    $scope.infoShow = true;
});
app.directive('grid', function () {
    return {
        scope: {}
        , controller: function ($scope, $attrs, $http) {
            var data = {
                userId: 41
                , role: 4
                , weekNum: 1
            };
            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http.post('lesson/info', data, config).then(function (response) {
                $scope.days = response;
            }, function error(response) {
                alert("Error with status: " + response.statusText);
            });
            $scope.weekNumber = weekNum;
            $scope.show = function (lesson) {
                alert("name: " + lesson.name + "\ntype: " + lesson.type, 'title');
                lessonToView = lesson;
                infoShow = true;
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
            lessonToView = $scope.days[1].lessons[2];
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