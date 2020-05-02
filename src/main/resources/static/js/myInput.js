let selectedTeacher;
let selectedType;
let selectedClassroom;
let selectedWorkday;
let selectedSubject;

var app = angular.module('pgApp', []);
app.controller('control', function ($scope, $http) {

});

app.directive('groupBox', function () {
    return{
        controller: function ($scope) {
            $scope.delete = function (id) {
                let index = $scope.groups2.findIndex(group => group.id === id);
                $scope.groups2.splice(index, 1);
            }
        }, restrict: "E"
        , templateUrl: "../templates/groupBox.html"
    }
});

app.directive('addLessonForm', function () {
    return{
        controller: function ($scope, $http) {
            $scope.show = function () {
                var posistion = document.getElementById("week").value * 100 +
                    document.getElementById("workday").value * 10+
                    + document.getElementById("time").value;
                alert('teacherId: ' + selectedTeacher + '\n'+
                    'classroomId: ' + selectedClassroom + '\n' +
                    'groupsId: ' + $scope.groups2.length+ '\n' +
                    'position: ' + posistion + '\n' +
                    'subject: ' + selectedSubject+ '\n' +
                    'type: ' + document.getElementById("type").value)
            } ;

            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http.get('/university/weeks', config).then(function (response2) {
                $scope.weeks =  response2.data;
            });
            $http.post('/workdays', config).then(function (response2) {
                $scope.workdays = response2.data;
            });
            $http.post('/times', config).then(function (response2) {
                $scope.times = response2.data;
            });
            $http.post('/types', config).then(function (response2) {
                $scope.types = response2.data;
            });
            $scope.groups2 = [];
            $('#teacher').autocomplete({
                source: function (request, response) {
                    $http.post('/professors', request.term, config).then(function (response2) {
                        response(response2.data);
                    });
                },
                minLength: 2,
                select: function displayItem(event, ui) {
                    selectedTeacher = ui.item.id;
                }
            });

            $('#groups').autocomplete({
                source: function (request, response) {
                    $http.post('/groups', request.term, config).then(function (response2) {
                        response(response2.data);
                    });
                },
                // source: $scope.groups,
                minLength: 1,
                select: function displayItem(event, ui) {
                    let index = $scope.groups2.findIndex(group => group.id === ui.item.id);
                    if(index === -1) {
                        $scope.groups2.push(ui.item);
                    }
                    // document.getElementById("groups").value = ' ';
                    $('#groups').text('Ваш текст');
                    $scope.$apply();
                },
                close: function () {
                    $('#groups').text('Ваш текст');
                }
            });

            $('#classroom').autocomplete({
                source: function (request, response) {
                    $http.post('/classrooms', request.term, config).then(function (response2) {
                        response(response2.data);
                    });
                },
                minLength: 1,
                select: function displayItem(event, ui) {
                    selectedClassroom = ui.item.id;
                }
            });

            $('#subject').autocomplete({
                source: function (request, response) {
                    $http.post('/subjects', request.term, config).then(function (response2) {
                        response(response2.data);
                    });
                },
                minLength: 3,
                select: function displayItem(event, ui) {
                    selectedSubject = ui.item.id;
                }
            });
        }, restrict: "E"
        , templateUrl: "../templates/addLessonForm.html"
        }
    });