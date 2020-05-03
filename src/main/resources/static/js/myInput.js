let selectedTeacher = -1;
let selectedClassroom = -1;
let selectedSubject = -1;

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

app.directive('positionBox', function () {
    return{
        controller: function ($scope) {
            $scope.deletePos = function (id) {
                let index = $scope.positions.findIndex(position => position.num === id);
                $scope.positions.splice(index, 1);
            }
        }, restrict: "E"
        , templateUrl: "../templates/positionsBox.html"
    }
});

app.directive('addLessonForm', function () {
    return{
        controller: function ($scope, $http) {
            $scope.groups2 = [];
            $scope.positions = [];
            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $scope.show = function () {
                if (checkFields($scope) && checkFieldsPos()) {
                    var posistion = document.getElementById("week").value * 100 +
                        document.getElementById("workday").value * 10 +
                        +document.getElementById("time").value;
                    alert('teacherId: ' + selectedTeacher + '\n' +
                        'classroomId: ' + selectedClassroom + '\n' +
                        'groupsId: ' + $scope.groups2.length + '\n' +
                        'position: ' + posistion + '\n' +
                        'subject: ' + selectedSubject + '\n' +
                        'type: ' + document.getElementById("type").value)
                }
                var data = {
                    positions: $scope.positions,
                    groups:  $scope.groups2,
                    subject: selectedSubject,
                    classroom: selectedClassroom,
                    professor: selectedTeacher,
                    type: document.getElementById("type").value
                };

                $http.post('/lesson/add', data, config).then(function (response) {
                    alert(response.data);
                });
            } ;

            $http.get('/university/weeks', config).then(function (response2) {
                $scope.weeks =  response2.data;
            });
            $http.post('/workdays', config).then(function (response2) {
                $scope.workdays = response2.data;
            });
            $http.post('/times', config).then(function (response2) {
                $scope.times = response2.data;
            });
            $http.get('/lessontypes', config).then(function (response2) {
                $scope.types = response2.data;
            });

            $scope.addPosition = function(){
                if (checkFieldsPos() === true){
                    var pos_num = document.getElementById("week").value * 100 +
                        document.getElementById("workday").value * 10+
                        + document.getElementById("time").value;
                    let index = $scope.positions.findIndex(position => position.num === pos_num);
                    if(index === -1) {
                        var label = document.getElementById("week").value+ "нед. " +
                            " " + $scope.workdays[document.getElementById("workday").value - 1].label +
                            " " + $scope.times[document.getElementById("time").value - 1].label;
                        var pos = {
                            num: pos_num,
                            label: label
                        };
                        angular.element('#pos-table').css('border', "none");
                        $scope.positions.push(pos);
                        $scope.$apply();

                    }
                }
                angular.element('#pos-table').css('border', "none");
            }
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
                minLength: 1,
                select: function displayItem(event, ui) {
                    let index = $scope.groups2.findIndex(group => group.id === ui.item.id);
                    if(index === -1) {
                        $scope.groups2.push(ui.item);
                    }
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

function checkFields($scope) {
    if($scope.positions.length === 0)
        angular.element('#pos-table').css('border', "2px solid red");
    else
        angular.element('#pos-table').css('border', "none");
    if (selectedTeacher === -1)
        angular.element('#teacher').css('border-color', "red");
    else
        angular.element('#teacher').css('border', "2px solid #cecece");
    if (selectedClassroom === -1)
        angular.element('#classroom').css('border-color', "red");
    else
        angular.element('#classroom').css('border', "2px solid #cecece");
    if (selectedSubject === -1)
        angular.element('#subject').css('border-color', "red");
    else
        angular.element('#subject').css('border', "2px solid #cecece");
    if ($scope.groups2.length === 0)
        angular.element('#groups').css('border-color', "red");
    else
        angular.element('#groups').css('border', "2px solid #cecece");
    if (selectedTeacher === -1 || selectedClassroom === -1 || selectedSubject === -1 || $scope.groups2.length === 0)
        return false;
    else
        return true;
}

function checkFieldsPos() {
    if (document.getElementById("week").value === "") {
        angular.element('#week').css('border-color', "red");
        return false;
    } else{
        angular.element('#week').css('border', "2px solid #cecece");
        return true;
    }
}