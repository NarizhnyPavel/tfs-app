let university;
let user;
var app = angular.module('homepg', []);

app.controller('control', function ($scope, $http) {

    var config = {
        headers: {
            'Content-Type': 'application/json'
        }
    };
    $http.get('/user/1', config).then(function (response) {
        user = response.data;
    })
    $http.get('/university', config).then(function (response) {
        university = response.data;
        document.getElementById('mainBlockId').style.backgroundColor = '#' + university.color1;
        document.getElementById('menu').style.backgroundColor = '#' + university.color2;
        // document.getElementById('timetable').style.backgroundColor = '#' + university.color2;
        document.querySelector('.page').style.backgroundColor = '#' + university.color2;
    });
    // alert(university.name);
    $scope.info = "hello";
    $scope.infoShow = false;
});

let minWeekNum;
let maxWeekNum;
let lessonToView;
'use strict';

app.directive('searchBlock', function () {
    return {
        controller: function ($scope, $http) {
            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $scope.searchShow = false;
            $scope.filterProf = function () {
                alert('hi');
                // if (filter.professor === false){
                //     filter.professor = true;
                //     document.getElementById('#prof').style.backgroundColor = '#adadad';
                // } else {
                //     filter.professor = false;
                //     document.getElementById('#prof').style.backgroundColor = 'white';
                // }
            };
            $scope.filterGroup = function () {
                alert('hi');
                // if (filter.classroom === true && filter.group === false){
                //     filter.group = true;
                //     filter.classroom = false;
                //     document.getElementById('#group').style.backgroundColor = '#adadad';
                //     document.getElementById('#room').style.backgroundColor = 'white';
                // }else{
                //     filter.group = false;
                //     filter.classroom = true;
                //     document.getElementById('#group').style.backgroundColor = 'white';
                //     document.getElementById('#room').style.backgroundColor = '#adadad';
                // }
            };
            $scope.filterRoom = function () {
                alert('hi');
                // filter.professor = true;
                // document.getElementById('#prof').style.backgroundColor = '#5e5e5e';
            };
            $('#searchInput').autocomplete({

                source: function (request, response) {
                    var data = {
                        request: request.term,
                        type: filter
                    };
                    $http.post('/search', data, config).then(function (response2) {
                        response(response2.data);
                    });
                },
                minLength: 1,
                select: function displayItem(event, ui) {
                    alert(ui.item.id + " " + ui.item.label + " " + ui.item.type);
                }
            });
        }
        , restrict: "E"
        , templateUrl: "../templates/searchPage.html"
        , transclude: true
    }
});

app.directive('grid', function () {
    return {
        controller: function ($scope, $attrs, $http) {
            minWeekNum = 1;
            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http.get('/university/weeks', config).then(function (response) {
                maxWeekNum = response.data;
            });
            $scope.week = 1;
            var data = {
                userId: 1
                , weekNum: 1
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
                $scope.$apply();
            }
            function refresh_timetable() {
                $http.post('/lesson/info', data, config).then(function (response) {
                    $scope.days2 = response.data;
                    $scope.$apply();
                });
            }
        }
        , restrict: "E"
        , templateUrl: "../templates/timetable.html",
        transclude: true
    }
});

app.directive('infoBox', function () {
    return {
        controller: function ($scope) {
            $scope.closeInfo = function () {
                $scope.infoShow = false;
                $scope.$apply();
            }
        }
        , restrict: "E"
        , templateUrl: "../templates/modalWindow.html"
        , transclude: true
    }
});

var filter = {
    professor: true,
    group: true,
    classroom: false
};



app.directive('groupBox', function () {
    return{
        controller: function ($scope) {
            $scope.delete = function (id) {
                let index = $scope.groups2.findIndex(group => group.id === id);
                $scope.groups2.splice(index, 1);
            }
        }, restrict: "E"
        , templateUrl: "../templates/groupBox.html"
        , transclude: true
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
        , transclude: true
    }
});

let selectedTeacher = -1;
let selectedClassroom = -1;
let selectedSubject = -1;

app.directive('addLessonForm', function () {
    return{
        scope: {},
        controller: function ($scope, $http) {
            $scope.groups2 = [];
            $scope.positions = [];
            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            function checkFields() {
                if($scope.positions.length === 0)
                    angular.element(document.querySelector('#pos-table')).css('border', "2px solid red");
                else
                    angular.element(document.querySelector('#pos-table')).css('border', "none");
                if (selectedTeacher === -1){
                    angular.element(document.querySelector('#teacher')).css('border-color', "red");}
                else
                    angular.element(document.querySelector('#teacher')).css('border', "2px solid #cecece");
                if (selectedClassroom === -1)
                    angular.element(document.querySelector('#classroom')).css('border-color', "red");
                else
                    angular.element(document.querySelector('#classroom')).css('border', "2px solid #cecece");
                if (selectedSubject === -1)
                    angular.element(document.querySelector('#subject')).css('border-color', "red");
                else
                    angular.element(document.querySelector('#subject')).css('border', "2px solid #cecece");
                if ($scope.groups2.length === 0)
                    angular.element(document.querySelector('#groups')).css('border-color', "red");
                else
                    angular.element(document.querySelector('#groups')).css('border', "2px solid #cecece");
                if (selectedTeacher === -1 || selectedClassroom === -1 || selectedSubject === -1 || $scope.groups2.length === 0)
                    return false;
                else
                    return true;
            }
            function checkFieldsPos() {
                if (document.getElementById("week").value === "") {
                    angular.element(document.querySelector('#week')).css('border-color', "red");
                    return false;
                } else{
                    angular.element(document.querySelector('#week')).css('border', "2px solid #cecece");
                    return true;
                }
            }
            $scope.showLesson = function () {
                if (checkFields() && checkFieldsPos()) {
                    var posistion = document.getElementById("week").value * 100 +
                        document.getElementById("workday").value * 10 +
                        +document.getElementById("time").value;
                    alert('teacherId: ' + selectedTeacher + '\n' +
                        'classroomId: ' + selectedClassroom + '\n' +
                        'groupsId: ' + $scope.groups2.length + '\n' +
                        'position: ' + posistion + '\n' +
                        'subject: ' + selectedSubject + '\n' +
                        'type: ' )
                }
                // var data = {
                //     positions: $scope.positions,
                //     groups:  $scope.groups2,
                //     subject: selectedSubject,
                //     classroom: selectedClassroom,
                //     professor: selectedTeacher,
                //     type: document.getElementById("type").value
                // };
                //
                // $http.post('/lesson/add', data, config).then(function (response) {
                //     alert(response.data);
                // });
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
            $http.get('/lessontypes', config).then(function (response2) {
                $scope.types = response2.data;
            });

            $scope.addPosition = function(){
                if (checkFieldsPos()){
                    var pos_num = document.getElementById("week").value * 100 +
                        document.getElementById("workday").value * 10+
                        + document.getElementById("time").value;
                    let index = $scope.positions.findIndex(position => position.num === pos_num);
                    if(index === -1) {
                        //проверка на наличие 0** в positions
                        var label = document.getElementById("week").value + "нед. " +
                            " " + $scope.workdays[document.getElementById("workday").value - 1].label +
                            " " + $scope.times[document.getElementById("time").value - 1].label;
                        if (document.getElementById("week").value === '0')
                            label = "все нед. " +
                                " " + $scope.workdays[document.getElementById("workday").value - 1].label +
                                " " + $scope.times[document.getElementById("time").value - 1].label;
                        var pos = {
                            num: pos_num,
                            label: label
                        };
                        $scope.positions.push(pos);

                        angular.element(document.querySelector('#pos-table')).css('border', "none");

                        $scope.$apply();
                    }
                }
            };
            $('#teacher').autocomplete({
                source: function (request, response) {
                    $http.post('/professors', request.term, config).then(function (response2) {
                        response(response2.data);
                    });
                },
                minLength: 2,
                select: function displayItem(event, ui) {
                    angular.element(document.querySelector('#teacher')).css('border', "2px solid #cecece");
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
                    angular.element(document.querySelector('#groups')).css('border', "2px solid #cecece");
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
                    angular.element(document.querySelector('#classroom')).css('border', "2px solid #cecece");
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
                    angular.element(document.querySelector('#subject')).css('border', "2px solid #cecece");
                    selectedSubject = ui.item.id;
                }
            });

        }, restrict: "E"
        , templateUrl: "../templates/addLessonForm.html"
        , transclude: true
    }
});

app.directive('universitySettings', function () {
    return {
        scope:{},
        controller: function ($scope, $http) {
            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            $http.get('/university', config).then(function (response) {
                $scope.uniData = response.data;
            });
            restore_main($scope);

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
                    if(true){
                        send($scope, $http);
                    }
                }
            }
            function checkFields($scope) {
                if (!$scope.uniData.name) {
                    angular.element(document.querySelector('#name')).css('border-color', "red");
                }
                else
                    angular.element(document.querySelector('#name')).css('border', "2px solid #cecece");
                if (!$scope.uniData.weeks)
                    angular.element(document.querySelector('#weeks')).css('border-color', "red");
                else
                    angular.element(document.querySelector('#weeks')).css('border', "2px solid #cecece");
                if (!$scope.uniData.lessonDuration)
                    angular.element(document.querySelector('#duration')).css('border-color', "red");
                else
                    angular.element(document.querySelector('#duration')).css('border', "2px solid #cecece");
                if (!$scope.uniData.color1)
                    angular.element(document.querySelector('#color1')).css('border-color', "red");
                else
                    angular.element(document.querySelector('#color1')).css('border', "2px solid #cecece");
                if (!$scope.uniData.color2)
                    angular.element(document.querySelector('#color2')).css('border-color', "red");
                else
                    angular.element(document.querySelector('#color2')).css('border', "2px solid #cecece");
                if (!$scope.uniData.color3)
                    angular.element(document.querySelector('#color3')).css('border-color', "red");
                else
                    angular.element(document.querySelector('#color3')).css('border', "2px solid #cecece");
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
                    angular.element(document.querySelector('#days')).css('border', "0");
            }

            function restore_main($scope){
                $scope.buttonLabel = "Далее";
                $scope.viewPage1 = true;
                $scope.viewPage2 = false;
                $scope.returnShow = false;
            }

            function send($scope, $http){
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
        }
        , restrict: "E"
        , templateUrl: "../templates/universitySettingsBlock.html"
    }
});

function showHide(element_id) {
    var timetable = document.getElementById('timetable');
    timetable.style.display = "none";
    var search = document.getElementById('search');
    search.style.display = "none";
    var settings = document.getElementById('settings');
    settings.style.display = "none";
    var settings = document.getElementById('lesson');
    settings.style.display = "none";
    var obj = document.getElementById(element_id);
    if (obj.style.display != "block") {
        obj.style.display = "block";
    }
}