var app = angular.module('homepg', []);
app.controller('timetableCtrl', function ($scope, $http) {
    $scope.word = "hi!";
});
app.directive('grid', function () {
    return {
        //        link: function ($scope, element, attrs) {
        //            $scope.data = $scope[attrs["day"]];
        //        }
        scope: {}
        , controller: function ($scope, $element, $attrs, $http) {
            $scope.show = function (lesson) {
                alert(lesson.name);
            }
            $scope.dayName = $attrs["day"];
            $scope.num = $attrs["num"];
            $scope.lessons = [
                {
                    time: "9:50"
                    , name: "TPR"
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
            , ];
            $scope.lessons
            $scope.dayNames = [
                {
                    num: "1"
                    , name: "Понедельник"
                }
                , {
                    num: "2"
                    , name: "Вторник"
                }
                , {
                    num: "3"
                    , name: "Среда"
                }
                , {
                    num: "4"
                    , name: "Четверг"
                }
                , {
                    num: "5"
                    , name: "Пятница"
                }
                , {
                    num: "6"
                    , name: "Суббота"
                }
            , ];
        }
        , restrict: "E"
        , templateUrl: "../templates/timetable.html"
    }
});
//function show() {
//    alert("hi!");
//}