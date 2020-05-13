
var app = angular.module('homepg', []);

var serverUrl = "http://localhost:8100";
var config = {
    headers: {
        'Content-Type': 'application/json'
    }
};

app.controller('control', function ($scope, $http, $window) {
    $scope.user = {
        id: $window.localStorage.getItem("userId"),
        name: $window.localStorage.getItem("userName"),
        role: $window.localStorage.getItem("userRole"),
    };
    $scope.dispetcherPages = false;
    $scope.studprofPages = false;
    if ($scope.user.role === "1") {
        $scope.dispetcherPages = true;
        document.querySelector("#lesson").style.display = "block";
    }
    if ($scope.user.role === "3" || $scope.user.role === "4" || $scope.user.role === "2"){
        document.querySelector("#timetable").style.display = "block";
        $scope.studprofPages = true;
    }
    $scope.university = "";
    $http.get(serverUrl + '/university', config).then(function (response) {
        $scope.university = response.data;
        $window.localStorage.setItem('color1', $scope.university.color1);
        $window.localStorage.setItem('color2', $scope.university.color2);
        $window.localStorage.setItem('color3', $scope.university.color3);
        document.getElementById('mainBlockId').style.backgroundColor = '#' + $window.localStorage.getItem("color1");
        document.getElementById('menu').style.backgroundColor = '#' + $window.localStorage.getItem("color2");
        document.querySelector('.page').style.backgroundColor = '#' + $window.localStorage.getItem("color2");

    });
    $scope.infoShow = false;
    $scope.entityToViewInTimeTable = {
        id: $scope.user.id, type: $scope.user.role
        , weekNum: 1
    };
    $scope.groups2 = [];
    $scope.$on('myCustomEvent', function (event, data) {
        $scope.entityToViewInTimeTable.id = data.someProp.id;
        $scope.$broadcast('myCustomEvent2', {
            someProp: $scope.entityToViewInTimeTable
        });
    });
    $scope.$on('groupsSettEvent', function (event, data) {
        console.log('контроллер получиль ' + data.comment);
        $scope.$broadcast('groupsSettEvent2', {
            prop: data.someProp,
            comm: data.comment
        });
    });
});

var filter = {
    professor: true,
    group: true,
    classroom: false
};

app.directive('searchBlock', function () {
    return {
        controller: function ($scope, $http) {
            $scope.searchShow = false;
            angular.element(document.querySelector('#prof')).css('backgroundColor', '#adadad');
            angular.element(document.querySelector('#group')).css('backgroundColor', '#adadad');
            $scope.filterProf = function () {

                if (filter.professor === false){
                    filter.professor = true;
                    angular.element(document.querySelector('#prof')).css('backgroundColor', '#adadad');
                } else {
                    filter.professor = false;
                    angular.element(document.querySelector('#prof')).css('backgroundColor', 'white');
                }
            };
            $scope.filterGroup = function () {
                filter.group = !filter.group;
                filter.classroom = !filter.group;
                if (filter.classroom === true && filter.group === false){
                    angular.element(document.querySelector('#group')).css('backgroundColor', 'white');
                    angular.element(document.querySelector('#room')).css('backgroundColor', '#adadad');
                }else{

                    angular.element(document.querySelector('#group')).css('backgroundColor', '#adadad');
                    angular.element(document.querySelector('#room')).css('backgroundColor', 'white');
                }
            };
            $scope.filterRoom = function () {
                filter.group = !filter.group;
                filter.classroom = !filter.group;
                if (filter.group === true && filter.classroom === false){
                    angular.element(document.querySelector('#room')).css('backgroundColor', 'white');
                    angular.element(document.querySelector('#group')).css('backgroundColor', '#adadad');
                }else{
                    angular.element(document.querySelector('#room')).css('backgroundColor', '#adadad');
                    angular.element(document.querySelector('#group')).css('backgroundColor', 'white');
                }
            };
            $('#searchDead').autocomplete({
                source: function (request, response) {
                    var data = {
                        request: request.term,
                        type: filter
                    };
                    $http.post(serverUrl + '/search', data, config).then(function (response2) {
                        response(response2.data);
                    });
                },
                minLength: 1,
                select: function displayItem(event, ui) {
                    $scope.entityToViewInTimeTable.id = ui.item.id;
                    $scope.entityToViewInTimeTable.type = ui.item.type;
                },
                close: function refresh(event, ui) {
                    $scope.$emit('myCustomEvent', {
                        someProp: $scope.entityToViewInTimeTable
                    });

                }
            });
        }
        , restrict: "E"
        , templateUrl: "../templates/searchPage.html"
        , transclude: true
    }
});

app.directive('gridMain', function () {
    return {
        scope:{},
        controller: function ($scope, $attrs, $http, $window) {
            $scope.timetableShow = false;
            $scope.minWeekNum = 1;
            $http.get(serverUrl + '/university/weeks', config).then(function (response) {
                $scope.maxWeekNum = response.data;
            });
            $scope.week = 1;
            var data = {
                userId: $window.localStorage.getItem("userId")
                , weekNum: 1
            };
            refresh_timetable();
            // document.querySelector('.day').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
            $scope.inc_week = function () {
                if ($scope.week < $scope.maxWeekNum) {
                    $scope.week++;
                    data.weekNum++;
                    refresh_timetable();
                }
            }
            $scope.dec_week = function () {
                if ($scope.week > $scope.minWeekNum) {
                    $scope.week--;
                    data.weekNum--;
                    refresh_timetable();
                }
            }
            $scope.show = function (lesson) {
                $scope.lessonToView = lesson;
                $window.localStorage.setItem("lessonToViewStatus", $scope.lessonToView.status);
                $window.localStorage.setItem("lessonToViewId", $scope.lessonToView.id);
                $scope.statusShow = true;
                $scope.groupShow = true;
                $scope.profShow = true;
                $scope.info = "Дисциплина: " + lesson.subject + "" +
                    "\n Тип: " + lesson.lessonType +
                    "\n Преподаватель: " + lesson.professor +
                    "\n Аудитория: " + lesson.classroom +
                    "\n Статус: " + lesson.status + "";
                $scope.infoShow = true;
                $scope.$apply();
            }
            function refresh_timetable() {
                $http.post(serverUrl + '/lesson/info', data, config).then(function (response) {
                    document.querySelector('.timetableStyle').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
                    $scope.timetableShow = true;
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

app.directive('gridSearch', function () {
    return {
        scope:{
        },
        controller: function ($scope, $attrs, $http, $window) {
            $scope.timetableShow = false;
            $scope.tableId = "table2";
            $scope.entityFromSearch = {
                id: "-1", type: "0"
                , weekNum: 1
            };
            $scope.minWeekNum = 1;
            $scope.days2 = [];
            $http.get(serverUrl + '/university/weeks', config).then(function (response) {
                $scope.maxWeekNum = response.data;
            });
            $scope.week = 1;
            function changeWeek(){
                $scope.week = $scope.entityFromSearch.weekNum;
            }
            $scope.$watch('entityFromSearch.weekNum', changeWeek());
            $scope.inc_week = function () {
                if ($scope.entityFromSearch.weekNum < $scope.maxWeekNum || $scope.entityFromSearch !== -1) {
                    $scope.week++;
                    $scope.entityFromSearch.weekNum++;
                    refresh_timetable();
                }
            }
            $scope.dec_week = function () {
                if ($scope.entityFromSearch.weekNum > $scope.minWeekNum || $scope.entityFromSearch !== -1) {
                    $scope.week--;
                    $scope.entityFromSearch.weekNum--;
                    refresh_timetable();
                }
            }
            $scope.show = function (lesson) {
                $scope.lessonToView = lesson;
                $scope.statusShow = false;
                $scope.groupShow = true;
                $scope.profShow = true;
                $scope.infoShow = true;
                $scope.$apply();
            }
            function refresh_timetable() {
                console.log('пытаюсь обновить таблицу для ' + $scope.entityFromSearch.id);
                console.log($scope.entityFromSearch);
                $http.post(serverUrl + '/lesson/by', $scope.entityFromSearch, config).then(function (response) {
                    if(response.data.length !== 0) {
                        $scope.timetableShow = true;

                    }
                    $scope.days2 = response.data;
                    $scope.$apply();
                });
            }
            $scope.$on('myCustomEvent2', function (event, data) {
                // console.log(data); // Данные, которые нам прислали
                $scope.entityFromSearch = data.someProp;
                $scope.week = 1;
                $scope.entityFromSearch.weekNum = 1;
                refresh_timetable();
                document.querySelector('#table2').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
            });
        }
        , restrict: "E"
        , require: "^searchBlock"
        , templateUrl: "../templates/timetable.html",
        transclude: true
    }
});

app.directive('infoBox', function () {
    return {
        controller: function ($scope, $window, $http) {
            $scope.cancelShow = (($window.localStorage.getItem("userRole") === '3'));
            $scope.closeInfo = function () {
                $scope.infoShow = false;
                // $scope.$apply();
            }
            $scope.cancelLesson = function () {
                if (confirm("Отменить выбранное занятие?\n отмена снимается спустя неделю после изменения статуса")) {
                    console.log('отменяю пару с id ' + $window.localStorage.getItem("lessonToViewId"));
                    $http.post(serverUrl + '/lesson/stop', $window.localStorage.getItem("lessonToViewId"), config).then(function (response) {
                        document.querySelector('.timetableStyle').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
                        $scope.days2 = response.data;
                        $scope.$apply();
                    });
                } else {
                    console.log('отказались от отмены')
                }
                $scope.infoShow = false;

            }
            $scope.checkLess = function () {
                if ($window.localStorage.getItem("lessonToViewStatus") === "true" && $window.localStorage.getItem("userRole") === '2')
                    return true;
                else
                    return false;
            }
        }
        , restrict: "E"
        , templateUrl: "../templates/modalWindow.html"
        , transclude: true
    }
});

app.directive('groupBox', function () {
    return{
        // scope:{},
        controller: function ($scope, $http) {
            var ableToDelAll = false;
            $scope.$on('groupsSettEvent2', function (event, data) {
                console.log('я получиль' + data.comm);
                $scope.groups2 = data.prop;
                ableToDelAll = data.comm;
            });
            $scope.delete = function (id) {
                console.log('могу ли я удалять все? ' + ableToDelAll);
                if (ableToDelAll || ($scope.groups2.length > 1)) {
                    let index = $scope.groups2.findIndex(group => group.id === id);
                    $scope.groups2.splice(index, 1);
                    console.log($scope.groups2.length);
                }
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

let selectedTeacher = 170;
let selectedClassroom = 5;
let selectedSubject = 5;

app.directive('addLessonForm', function () {
    return{
        scope: {},
        controller: function ($scope, $http) {
            $scope.groups2 = [];
            $scope.positions = [];
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
                    var data = {
                        position: $scope.positions,
                        groups:  $scope.groups2,
                        subject: selectedSubject,
                        classroom: selectedClassroom,
                        professor: selectedTeacher,
                        lessonType: document.getElementById("type").value
                    };
                    $http.post(serverUrl + '/lesson/add', data, config).then(function (response) {
                        let answer = response.data;
                        for (let i = 0; i < answer.length; i++) {
                            let posIndex = $scope.positions.findIndex(pos => pos.num === answer[i].position);
                            if (posIndex !== -1){
                                console.log(posIndex);
                                let position = $scope.positions[posIndex];
                                position.status = 1;
                                let group = true;
                                for (let j = 0; j < answer[i].groups.length; j++) {
                                    if($scope.groups2[j].number)
                                        $scope.groups2[j].number = answer[i].groups[j].number;
                                    if(!answer[i].groups[j].number)
                                        group = false;
                                }
                                console.log('для позиции '+ position.label + ' group = ' + group);
                                if (answer[i].professor === 0 || answer[i].classroom === 0 || !group){
                                    position.status = 0;
                                    let message = "";
                                    if (answer[i].professor === 0) {
                                        message = message + "преподавтель уже занят \n";
                                        angular.element(document.querySelector('#teacher')).css('border-color', "red");
                                    } else
                                        angular.element(document.querySelector('#teacher')).css('border', "2px solid #cecece");
                                    if (answer[i].classroom === 0) {
                                        message = message + "аудитория уже занята \n";
                                        angular.element(document.querySelector('#classroom')).css('border-color', "red");
                                    } else
                                        angular.element(document.querySelector('#classroom')).css('border', "2px solid #cecece");
                                    if (!group){
                                        console.log('Зашёл сюда');
                                        let errGroups = $scope.groups2.filter(group => group.number === 0);
                                        console.log(errGroups.length);
                                        if (errGroups.length > 1)
                                            message = message + "у групп ";
                                        else
                                            message = message + "у группы ";
                                        for (let j = 0; j < errGroups.length; j++) {
                                            message = message + errGroups[j].label + " ";
                                        }
                                        message = message + "уже есть пары";
                                    }
                                    position.errmessage = message;
                                    console.log(position.errmessage);
                                }
                            }
                        }
                        $scope.$apply();
                    });
                }
            };
            $http.get(serverUrl + '/university/weeks', config).then(function (response2) {
                $scope.weeks =  response2.data;
            });
            $http.post(serverUrl + '/workdays', config).then(function (response2) {
                $scope.workdays = response2.data;
            });
            $http.post(serverUrl + '/times', config).then(function (response2) {
                $scope.times = response2.data;
            });
            $http.get(serverUrl + '/lessontypes', config).then(function (response2) {
                $scope.types = response2.data;
            });

            $scope.addPosition = function(){
                if (checkFieldsPos()){
                    var pos_num = document.getElementById("week").value * 100 +
                        document.getElementById("workday").value * 10+
                        + document.getElementById("time").value;
                    if (pos_num < 100)
                        pos_num = "0" + pos_num;
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
                            num: "" + pos_num,
                            label: label,
                            status: 1,
                            errmessage: ""
                        };
                        $scope.positions.push(pos);

                        angular.element(document.querySelector('#pos-table')).css('border', "none");

                        $scope.$apply();
                    }
                }
            };
            $('#teacher').autocomplete({
                source: function (request, response) {
                    $http.post(serverUrl + '/professors', request.term, config).then(function (response2) {
                        response(response2.data);
                    });
                },
                minLength: 1,
                select: function displayItem(event, ui) {
                    angular.element(document.querySelector('#teacher')).css('border', "2px solid #cecece");
                    selectedTeacher = ui.item.id;
                }
            });

            $('#groups').autocomplete({
                source: function (request, response) {
                    $http.post(serverUrl + '/groups', request.term, config).then(function (response2) {
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
                    $scope.$emit('groupsSettEvent', {
                        someProp: $scope.groups2,
                        comment: true
                    });
                },
                close: function () {
                    document.getElementById('groups').value = "";
                    // $('#groups').text('Ваш текст');
                }
            });

            $('#classroom').autocomplete({
                source: function (request, response) {
                    $http.post(serverUrl + '/classrooms', request.term, config).then(function (response2) {
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
                    $http.post(serverUrl + '/subjects', request.term, config).then(function (response2) {
                        response(response2.data);
                    });
                },
                minLength: 2,
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
        controller: function ($scope, $http, $window) {
            document.querySelector('.register-fields').style.backgroundColor = '#' + $window.localStorage.getItem("color2");
            document.querySelector('#returnButtonUni').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
            document.querySelector('#clickUni').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
            $http.get(serverUrl + '/university', config).then(function (response) {
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
                    ){
                        document.querySelector('.register-fields').style.heigth = '470px';
                        $scope.viewPage1 = false;
                        $scope.viewPage2 = true;
                        $scope.returnShow = true;
                        $scope.buttonLabel = "Сохранить";
                    }else{
                        checkFields($scope);
                    }}
                else{
                    //TODO проверка на изменение рабочих дней или количества недель
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
                document.querySelector('.register-fields').style.heigth = '420px';
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
                var url = serverUrl + "/university/update";
                $http.post(url, data, config).then(function (response) {
                    alert("Сохранено");
                }, function error(response) {
                    alert("Сохранено");
                });
                // jQuery(function ($) {
                //     $.mask.definitions['q'] = '[A-Fa-f0-9]';
                //     $("#color1").click(function() {
                //         $(this).setCursorPosition(1);
                //     }).mask("#9999");;
                // });
                // $("#inputPhone").click(function(){
                //     $(this).setCursorPosition(1);
                // }).mask("79999999999");
                // jQuery(function ($) {
                //     $(function () {
                //         $("#durationUni").mask("99");
                //     });
                // });
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
    var lesson = document.getElementById('lesson');
    if (lesson !== null)
        lesson.style.display = "none";
    var parser = document.getElementById('parser');
    if (parser !== null)
        parser.style.display = "none";
    var obj = document.getElementById(element_id);
    if (obj.style.display != "block") {
        obj.style.display = "block";
    }
}

app.directive('timetableView', function($compile){
    "use strict";
    return{
        scope:{
            entity1: '='
        },
        restrict: 'E',
        replace: true,
        link: function(scope, element, attrs){
            var render = function(){
                var template = "";
                switch(attrs.type){
                    case "main":
                        template = '<grid-main ></grid-main>';
                        break;
                    case "search":
                        template = '<grid-search></grid-search>';
                        break;
                    // case "essay":
                    //     template = '<essay></essay>';
                    //     break;
                }
                element.html(template);
                $compile(element.contents())(scope);
            }
            attrs.$observe('type', function(value) {
                render();
            });
            render();
        }
    };
});

app.directive('searchView', function($compile){
    "use strict";
    return{
        scope:{
            entity: '='
        },
        restrict: 'E',
        replace: true,
        link: function(scope, element, attrs){
            var render = function(){
                var template = "";
                switch(attrs.type){
                    case "main":
                        template = '<grid-main ></grid-main>';
                        break;
                    case "search":
                        template = '<grid-search entity="entity"></grid-search>';
                        break;
                    // case "essay":
                    //     template = '<essay></essay>';
                    //     break;
                }
                element.html(template);
                $compile(element.contents())(scope);
            }
            attrs.$observe('type', function(value) {
                render();
            });
            render();
        }
    };
});

app.directive('userSettings', function () {
    return {
        // scope: {},
        controller: function ($scope, $window, $http) {
            $scope.user = {
                id: $window.localStorage.getItem("userId"),
                role: $window.localStorage.getItem("userRole"),
                name: $window.localStorage.getItem("userName"),
                tel: $window.localStorage.getItem("userTel")
            };
            $('#groupsset').autocomplete({
                source: function (request, response) {
                    $http.post(serverUrl + '/groups', request.term, config).then(function (response2) {
                        response(response2.data);
                    });
                },
                minLength: 1,
                select: function displayItem(event, ui) {
                    let index = $scope.groups2.findIndex(group => group.id === ui.item.id);
                    if (index === -1) {
                        $scope.groups2.push(ui.item);
                    }
                    console.log('я отправиль');
                    $scope.$emit('groupsSettEvent', {
                        someProp: $scope.groups2,
                        comment: false
                    });
                    angular.element(document.querySelector('#groupsset')).css('border', "2px solid #cecece");
                    $scope.$apply();
                },
                close: function () {
                    document.getElementById('groupsset').value = "";
                }
            });
            $scope.groups2 = [];
            $scope.messShow = false;
            let groupsInitLength = 0;
            $http.get(serverUrl + '/user/groups/' + $scope.user.id, config).then(function (response) {
                $scope.groups2 = response.data;
                console.log('загружены группы длины ' + $scope.groups2.length);
                groupsInitLength = $scope.groups2.length;
                $window.localStorage.setItem("groupsLength", $scope.groups2.length);
            });
            console.log($window.localStorage.getItem("groupsLength"));
            $scope.$emit('groupsSettEvent', {
                someProp: $scope.groups2,
                comment: false
            });
            $scope.codeSended = false;
            $scope.errorShowSet = false;
            $scope.errorMess = "";
            $scope.saveButEnable = true;
            $scope.codeButLabel = "выслать код";
            $scope.savesettings = function() {
                // console.log(document.querySelector('#telInputSet').value + ' ' + $scope.user.tel);
                if ((document.querySelector('#telInputSet').value === $scope.user.tel) &&
                    (document.querySelector('#nameInput').value !== $scope.user.name || $window.localStorage.getItem("groupsLength") !== (''+$scope.groups2.length))){
                    var data = {
                        groups: $scope.groups2,
                        id: $scope.user.id,
                        name: document.querySelector('#nameInput').value,
                        phone: document.querySelector('#telInputSet').value
                    };
                    $http.post(serverUrl + "/user/update", data , {headers: {'Accept': 'text/plain'}}).then(function (response) {
                        console.log(response.data);
                        $window.localStorage.setItem("userTel", document.querySelector('#telInputSet').value);
                        $window.localStorage.setItem("userName", document.querySelector('#nameInput').value);
                        $scope.messShow = true
                    });
                }else{
                    if (document.querySelector('#telInputSet').value !== $scope.user.tel){
                    $scope.errorMess = "требуется подтвердить телефон смс кодом";
                    $scope.errorShowSet = true;
                    document.querySelector('#settingsPage').style.height = "320px";
                    }
                }
            };
            $scope.varifytel = function() {
                if (document.querySelector('#telInputSet').value !== $scope.user.tel) {
                    $http.post(serverUrl + "/login/checkPhone", document.getElementById('telInputSet').value, {headers: {'Accept': 'text/plain'}}).then(function (response) {
                        if (response.data === "registered") {
                            $scope.errorMess = "этот телефон уже зарегистрирован"
                            $scope.errorShowSet = true;
                            document.querySelector('#settingsPage').style.height = "320px";
                        } else {
                            $scope.saveButEnable = false;
                            document.querySelector('#saveSett').disabled = true;
                            if (!$scope.codeSended || $scope.errorMess === "неверный код подтверждения") {
                                $http.post(serverUrl + "/user/edit/phone", document.getElementById('telInputSet').value, {headers: {'Accept': 'text/plain'}}).then(function (response) {
                                    console.log(response.data);
                                    if (response.data === "codeSent") {
                                        $scope.errorShowSet = false;
                                        $scope.errorMess = "";
                                        document.querySelector('#settingsPage').style.height = "290px";
                                        $scope.codeButLabel = "подтвердить";
                                        $scope.codeSended = true;
                                        angular.element(document.querySelector('#codeInput')).attr('readOnly', 'false');
                                        document.getElementById('codeInput').readOnly = false;
                                    }
                                });
                            } else {
                                $http.post(serverUrl + "/user/check/code", {
                                    phone: document.getElementById('telInputSet').value,
                                    code: document.getElementById('codeInput').value
                                }, {headers: {'Accept': 'text/plain'}}).then(function (response) {
                                    console.log()
                                    if (response.data === "success") {
                                        console.log('ну типа проверили');

                                        $scope.saveButEnable = true;
                                        $scope.errorMess = "новый телефон подтверждён"
                                        angular.element(document.querySelector('#telInputSet')).attr('readOnly', 'true');
                                        document.querySelector('#saveSett').disabled = false;
                                        $scope.errorShowSet = true;
                                        $scope.user.tel = document.querySelector('#telInputSet').value;
                                        document.querySelector('#settingsPage').style.height = "320px";
                                        $scope.codeButLabel = "выслать код";
                                        $scope.codeSended = false;
                                        angular.element(document.querySelector('#codeInput')).attr('readOnly', 'false');
                                    }
                                    if (response.data === "error") {
                                        $scope.errorMess = "неверный код подтверждения";
                                        $scope.codeButLabel = "выслать код";
                                        $scope.errorShowSet = true;
                                        document.querySelector('#settingsPage').style.height = "320px";
                                        angular.element(document.querySelector('#codeInput')).attr('readOnly', 'true');
                                    }
                                });
                            }
                        }
                    });

                }
            };
            $("#telInputSet").mask("79999999999");
            $("#codeInput").click(function(){
                $(this).setCursorPosition(1);
            }).mask("9999");

            document.querySelector('#settingsPage').style.backgroundColor = '#' + $window.localStorage.getItem("color2");
            if ($scope.user.role === "2")
                document.querySelector('#settingsPage').style.height = "210px";
            document.querySelector('#varifyId').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
            document.querySelector('#saveSett').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
            document.querySelector('#nameInput').value = $scope.user.name;
            document.querySelector('#telInputSet').value = $scope.user.tel;
        },
        restrict: "E",
        templateUrl: "../templates/userSettings.html"
        , transclude: true

    };
});

app.directive('groupList', function () {
    return {
        // scope: {},
        controller: function ($scope, $window, $http) {
            $http.post(serverUrl + '/user/groups/' + $scope.user.id, config).then(function (response) {
                $scope.groups2 = response.data;
                console.log('загружены группы длины ' + $scope.groups2.length);
                groupsInitLength = $scope.groups2.length;
                $window.localStorage.setItem("groupsLength", $scope.groups2.length);
            });
        },
        restrict: "E",
        templateUrl: "../templates/userSettings.html"
        , transclude: true


    };
});

$.fn.setCursorPosition = function(pos) {
    if ($(this).get(0).setSelectionRange) {
        $(this).get(0).setSelectionRange(pos, pos);
    } else if ($(this).get(0).createTextRange) {
        var range = $(this).get(0).createTextRange();
        range.collapse(true);
        range.moveEnd('character', pos);
        range.moveStart('character', pos);
        range.select();
    }
};

