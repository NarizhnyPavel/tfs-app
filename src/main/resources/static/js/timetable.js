var app = angular.module('homepg', []);
app.controller('timetableCtrl', function ($scope, $http) {
    $scope.word = "hi!";
});
app.directive('grid', function () {
    return {
        link: function ($scope, element, attrs) {
            $scope.data = $scope[attrs["day"]];
        }
        , restrict: "E"
        , templateUrl: "../templates/timetable.html"
            //        template: "<span>alive</span>"
    }
});