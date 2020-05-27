
var app = angular.module('homepg', []);

app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);

// var serverUrl = "https://timeforstudyetu.herokuapp.com";
var serverUrl = "http://localhost:8080";
var config = {
    headers: {
        'Content-Type': 'application/json'
    }
};


function sendNotification(title, options) {
// Проверим, поддерживает ли браузер HTML5 Notifications
    if (!("Notification" in window)) {
        alert('Ваш браузер не поддерживает HTML Notifications, его необходимо обновить.');
    } else if (Notification.permission === "granted") {
        var notification = new Notification(title, options);

        function clickFunc() {
            alert('Пользователь кликнул на уведомление');
        }

        notification.onclick = clickFunc;
    } else if (Notification.permission === 'default' || Notification.permission === 'denied') {
        Notification.requestPermission(function (permission) {
            if (permission === "granted") {
                var notification = new Notification(title, options);
            } else {
                alert('Вы запретили показывать уведомления'); // Юзер отклонил наш запрос на показ уведомлений
            }
        });
    }
}

app.controller('control', function ($scope, $http, $window) {
    $scope.user = {
        id: $window.localStorage.getItem("userId"),
        name: $window.localStorage.getItem("userName"),
        role: $window.localStorage.getItem("userRole"),
    };
    $scope.dispetcherPages = false;
    $scope.studPages = false;
    $scope.profPages = false;
    if ($scope.user.role === "1") {
        $scope.dispetcherPages = true;
        document.querySelector("#lessonAdd").style.display = "block";
    }
    if ($scope.user.role === "3" || $scope.user.role === "4" || $scope.user.role === "2"){
        document.querySelector("#timetable").style.display = "block";
        $scope.studPages = true;
        if ($scope.user.role === "2")
            $scope.profPages = true;
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
    $http.get(serverUrl + '/notification/' +  $window.localStorage.getItem("userId"), config).then(function (response) {
        let notifications = response.data;
        for (var i = 0; i < notifications.length; i++){
            sendNotification(notifications[i].title, {
                body: notifications[i].message,
                icon: '../icon/icon.png'
            });
        }
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
            someProp: data.someProp
        });
    });
    $scope.$on('myCustomEvent_update', function (event, data) {
        $scope.entityToViewInTimeTable.id = data.someProp.id;
        $scope.$broadcast('myCustomEvent2_update', {
            someProp: (data.type === "search" ? data.someProp : data.someProp),
            type: data.type
        });
    });
    $scope.$on('groupsSettEvent', function (event, data) {
        $scope.$broadcast('groupsSettEvent2', {
            prop: data.someProp,
            comm: data.comment
        });
    });
    $scope.$on('sendToUpdateForm', function (event, data) {
        $scope.$broadcast('sendToUpdateForm2', {
            prop: data.lessonId
        });
    });
    $scope.$on('stopLessonUpdateGrid', function (event, data) {
        $scope.$broadcast('stopLessonUpdateGrid_timetable', {
            prop: -1
        });
    });
    $scope.lessonToUpdate;

});


app.directive('searchBlockMain', function () {
    return {
        controller: function ($scope, $http) {
            var filter = {
                professor: true,
                group: true,
                classroom: false
            };
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

app.directive('searchBlockDispatcher', function () {
    return {
        controller: function ($scope, $http) {
            var filter = {
                professor: true,
                group: true,
                classroom: false
            };
            $scope.searchShow = false;
            $scope.upfatingLessonId =-1;
            angular.element(document.querySelector('#profUpdate')).css('backgroundColor', '#adadad');
            angular.element(document.querySelector('#groupUpdate')).css('backgroundColor', '#adadad');
            $scope.filterProf = function () {
                if (filter.professor === false){
                    filter.professor = true;
                    angular.element(document.querySelector('#profUpdate')).css('backgroundColor', '#adadad');
                } else {
                    filter.professor = false;
                    angular.element(document.querySelector('#profUpdate')).css('backgroundColor', 'white');
                }
            };
            $scope.filterGroup = function () {
                filter.group = !filter.group;
                filter.classroom = !filter.group;
                if (filter.classroom === true && filter.group === false){
                    angular.element(document.querySelector('#groupUpdate')).css('backgroundColor', 'white');
                    angular.element(document.querySelector('#roomUpdate')).css('backgroundColor', '#adadad');
                }else{

                    angular.element(document.querySelector('#groupUpdate')).css('backgroundColor', '#adadad');
                    angular.element(document.querySelector('#roomUpdate')).css('backgroundColor', 'white');
                }
            };
            $scope.filterRoom = function () {
                filter.group = !filter.group;
                filter.classroom = !filter.group;
                if (filter.group === true && filter.classroom === false){
                    angular.element(document.querySelector('#roomUpdate')).css('backgroundColor', 'white');
                    angular.element(document.querySelector('#groupUpdate')).css('backgroundColor', '#adadad');
                }else{
                    angular.element(document.querySelector('#roomUpdate')).css('backgroundColor', '#adadad');
                    angular.element(document.querySelector('#groupUpdate')).css('backgroundColor', 'white');
                }
            };
            $('#searchDead2').autocomplete({
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
                    if (document.querySelector('#lessonUpdate').style.display === 'block') {
                        $scope.$emit('myCustomEvent_update', {
                            someProp: $scope.entityToViewInTimeTable,
                            type: "search"
                        });
                        $scope.updatingLessonId = $scope.entityToViewInTimeTable;
                    } else{
                        $scope.$emit('myCustomEvent', {
                            someProp: $scope.entityToViewInTimeTable
                        });
                    }
                }
            });
        }
        , restrict: "E"
        , templateUrl: "../templates/searchPage-2.html"
        , transclude: true
    }
});

app.directive('gridMain', function () {
    return {
        scope:{},
        controller: function ($scope, $attrs, $http, $window) {
            $scope.timetableShow = false;
            $scope.minWeekNum = 1;
            $scope.days2 = [];
            $
            // $scope.week;
            // $scope.weeknum;
            $http.get(serverUrl + '/university/weeks', config).then(function (response) {
                $scope.maxWeekNum = response.data;
            });
            var data = {
                userId: $window.localStorage.getItem("userId")
                , weekNum: 0
            };
            $http.get(serverUrl + '/week/now/' + 1, config).then(function (response) {
                $scope.week = response.data.numberWeek;
                data.weekNum = response.data.numberWeek;
                refresh_timetable();
            });
            $scope.inc_week = function () {
                if ($scope.week < $scope.maxWeekNum) {
                    $scope.week++;
                    data.weekNum++;
                } else{
                    $scope.week = $scope.minWeekNum;
                    data.weekNum= $scope.minWeekNum;
                }
                refresh_timetable();
            }
            $scope.dec_week = function () {
                if ($scope.week > $scope.minWeekNum) {
                    $scope.week--;
                    data.weekNum--;
                } else {
                    $scope.week = $scope.maxWeekNum;
                    data.weekNum = $scope.maxWeekNum;
                }
                refresh_timetable();
            }

            $scope.show = function (lesson) {
                $scope.lessonToView = lesson;
                $window.localStorage.setItem("lessonToViewStatus", $scope.lessonToView.status);
                $window.localStorage.setItem("lessonToViewId", $scope.lessonToView.id);
                $window.localStorage.setItem("lessonToViewWeek", $scope.week);
                $window.localStorage.setItem("lessonToViewProfId", $scope.lessonToView.professorId);
                $scope.statusShow = true;
                $scope.groupShow = true;
                $scope.profShow = true;
                $scope.infoShow = true;
            }
            function refresh_timetable() {
                $http.post(serverUrl + '/lesson/info', data, config).then(function (response) {
                    document.querySelector('.timetableStyle').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
                    $scope.timetableShow = true;
                    $scope.days2 = response.data;
                });
            }
            $scope.$on('stopLessonUpdateGrid_timetable', function (event, data) {
                $scope.week = 1;
                // $scope.entityFromSearch.weekNum = 1;
                refresh_timetable();
                document.querySelector('.timetableStyle').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
            });
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
            // $http.get(serverUrl + '/week/now/' + 1, config).then(function (response) {
            //     $scope.week = response.data.numberWeek;
            // });
            function changeWeek(){
                $scope.week = $scope.entityFromSearch.weekNum;
            }
            $scope.$watch('entityFromSearch.weekNum', changeWeek());
            $scope.inc_week = function () {
                if ($scope.entityFromSearch.weekNum < $scope.maxWeekNum) {
                    $scope.week++;
                    $scope.entityFromSearch.weekNum++;

                } else{
                    $scope.week = $scope.minWeekNum;
                    $scope.entityFromSearch.weekNum = $scope.minWeekNum;
                }
                refresh_timetable();
            }
            $scope.dec_week = function () {
                if ($scope.entityFromSearch.weekNum > $scope.minWeekNum) {
                    $scope.week--;
                    $scope.entityFromSearch.weekNum--;
                } else{
                    $scope.week = $scope.maxWeekNum;
                    $scope.entityFromSearch.weekNum = $scope.maxWeekNum;
                }
                refresh_timetable();
            }
            $scope.show = function (lesson) {
                $scope.lessonToView = lesson;
                $scope.statusShow = false;
                $scope.groupShow = true;
                $scope.profShow = true;
                $scope.infoShow = true;
                $window.localStorage.setItem("lessonToViewProfId", -1);
                $window.localStorage.setItem("lessonToViewStatus", -1);
                $window.localStorage.setItem("lessonToViewId", lesson.id);
                $window.localStorage.setItem("lessonToUpdateId", lesson.id);
                $window.localStorage.setItem("lessonToUpdateProfId", lesson.professorId);
                $window.localStorage.setItem("lessonToViewWeek", -1);
                $window.localStorage.setItem("lessonToViewProfId", -1);
            }
            function refresh_timetable() {
                $http.post(serverUrl + '/lesson/by', $scope.entityFromSearch, config).then(function (response) {
                    if(response.data.length !== 0) {
                        $scope.timetableShow = true;
                    }
                    $scope.days2 = response.data;

                });
            }
            var savedId = -1;
            $scope.$on('myCustomEvent2', function (event, data) {
                if (data.someProp !== -1) {
                    $scope.entityFromSearch = data.someProp;
                    savedId = data.someProp.id;
                } else
                    $scope.entityFromSearch.id = savedId;
                $scope.week = 1;
                $scope.entityFromSearch.weekNum = 1;
                refresh_timetable();
                document.querySelector('#table2').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
                // document.querySelector('.day').style.backgroundColor = '#' + $window.localStorage.getItem("color3");

            });
        }
        , restrict: "E"
        , require: "^searchBlock"
        , templateUrl: "../templates/timetable2.html",
        transclude: true
    }
});

app.directive('gridUpdate', function () {
    return {
        scope:{
        },
        controller: function ($scope, $attrs, $http, $window) {
            $scope.timetableShow = false;
            $scope.tableId = "table3";
            $scope.updatingLessonId = -1;
            $scope.updatingLessonId_new = -1;
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
                if ($scope.entityFromSearch.weekNum < $scope.maxWeekNum) {
                    $scope.week++;
                    $scope.entityFromSearch.weekNum++;

                } else{
                    $scope.week = $scope.minWeekNum;
                    $scope.entityFromSearch.weekNum = $scope.minWeekNum;
                }
                refresh_timetable();
            }
            $scope.dec_week = function () {
                if ($scope.entityFromSearch.weekNum > $scope.minWeekNum) {
                    $scope.week--;
                    $scope.entityFromSearch.weekNum--;
                } else{
                    $scope.week = $scope.maxWeekNum;
                    $scope.entityFromSearch.weekNum = $scope.maxWeekNum;
                }
                refresh_timetable();
            }
            $scope.show = function (lesson) {
                $scope.lessonToView = lesson;
                $scope.statusShow = false;
                $scope.groupShow = true;
                $scope.profShow = true;
                $scope.infoShow = true;
                $window.localStorage.setItem("lessonToViewProfId", -1);
                $window.localStorage.setItem("lessonToViewStatus", -1);
                $window.localStorage.setItem("lessonToViewId", -1);
                $window.localStorage.setItem("lessonToUpdateId", lesson.id);
                $window.localStorage.setItem("lessonToUpdateProfId", lesson.professorId);
                $window.localStorage.setItem("lessonToViewWeek", -1);
                $window.localStorage.setItem("lessonToViewProfId", -1);
            }
            function refresh_timetable() {
                $http.post(serverUrl + '/lesson/by', $scope.entityFromSearch, config).then(function (response) {
                    if(response.data.length !== 0) {
                        $scope.timetableShow = true;
                    }
                    $scope.days2 = response.data;
                });
            }
            var savedId = -1;
            $scope.$on('myCustomEvent2_update', function (event, data) {
                if (data.type === "search") {
                    savedId = data.someProp.id;
                    $scope.entityFromSearch = data.someProp;
                }
                if (data.type === "update") {
                    $scope.entityFromSearch.id = savedId;
                    $scope.updatingLessonId = data.someProp;
                } else{
                    $scope.entityFromSearch.id = savedId;
                    // $scope.updatingLessonId = data.someProp;
                }
                // $scope.week = 1;
                // $scope.entityFromSearch.weekNum = 1;
                refresh_timetable();
                document.querySelector('#table3').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
            });
        }
        , restrict: "E"
        , require: "^searchBlock"
        , templateUrl: "../templates/timetable2.html",
        transclude: true
    }
});

app.directive('infoBox', function () {
    return {
        controller: function ($scope, $window, $http) {
            var cancelShow = (($window.localStorage.getItem("userRole") === '2'));
            var updateShow = (($window.localStorage.getItem("userRole") === '2'));
            var deleteShow = (($window.localStorage.getItem("userRole") === '1'));
            $scope.closeInfo = function () {
                $scope.infoShow = false;
            }
            $scope.cancelLesson = function () {
                if (confirm("Отменить выбранное занятие?\n отмена снимается спустя неделю после изменения статуса")) {
                    let data = {
                        id: $window.localStorage.getItem("lessonToViewId"),
                        weeks: $window.localStorage.getItem("lessonToViewWeek")
                    };
                    $http.post(serverUrl + '/lesson/stop', data , config).then(function (response) {
                        document.querySelector('.timetableStyle').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
                        $scope.days2 = response.data;
                    });
                }
                $scope.infoShow = false;
                $scope.$emit('stopLessonUpdateGrid', {
                    someProp: -1
                });
            }
            $scope.checkLess = function () {
                if ($window.localStorage.getItem("lessonToViewStatus") === "true" && $window.localStorage.getItem("userRole") === '2'
                && ''+$window.localStorage.getItem("userId") === ''+$window.localStorage.getItem("lessonToViewProfId"))
                    return true;
                else
                    return false;
            }
            var updatingLessonId = -1;
            $scope.$on('myCustomEvent2_update', function (event, data) {
                if (data.type === "deleteBorder") {
                    updatingLessonId = -1;
                }
            });
            $scope.checkLessToUpdate = function () {
                if (updateShow && ''+$window.localStorage.getItem("userId") === ''+ $window.localStorage.getItem("lessonToUpdateProfId")
                && (document.querySelector('#lessonUpdate').style.display === 'block') &&
                    $window.localStorage.getItem("lessonToUpdateId") !== updatingLessonId &&
                    updatingLessonId === -1){
                    return true;
                } else {
                    return false;
                }
            }
            $scope.checkLessToDelete = function () {
                if (deleteShow && (document.querySelector('#lessonAdd').style.display === 'block')){
                    return true;
                } else {
                    return false;
                }
            }
            $scope.updateLesson = function () {
                $scope.$emit('sendToUpdateForm', {
                    lessonId: $window.localStorage.getItem("lessonToUpdateId")
                });
                updatingLessonId = $window.localStorage.getItem("lessonToUpdateId")
                $scope.$emit('myCustomEvent_update', {
                    someProp: $window.localStorage.getItem("lessonToUpdateId"),
                    type: "update"
                });
                $scope.infoShow = false;
            }
            $scope.deleteLesson = function () {
                $scope.$emit('sendToUpdateForm', {
                    lessonId: $window.localStorage.getItem("lessonToUpdateId")
                });

                if (confirm("Удалить выбранное занятие?")) {
                    let data = {
                        id: $window.localStorage.getItem("lessonToViewId"),
                        weeks: $window.localStorage.getItem("lessonToViewWeek")
                    };
                    $http.delete(serverUrl + '/lesson/delete/'+ $window.localStorage.getItem("lessonToViewId"), {headers: {'Accept': 'text/plain'}}).then(function (response) {
                        if (response.data === "success")
                            $scope.$emit('myCustomEvent', {
                                someProp: -1
                            });
                    });
                }
                $scope.infoShow = false;
                $scope.$emit('myCustomEvent', {
                    someProp: -1
                });
                updatingLessonId = $window.localStorage.getItem("lessonToUpdateId")
                $scope.infoShow = false;
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
                $scope.groups2 = data.prop;
                ableToDelAll = data.comm;
            });
            $scope.delete = function (id) {
                if (ableToDelAll || ($scope.groups2.length > 1)) {
                    let index = $scope.groups2.findIndex(group => group.id === id);
                    $scope.groups2.splice(index, 1);
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

let selectedTeacher = -1;
let selectedClassroom = -1;
let selectedSubject = -1;

app.directive('addLessonForm', function () {
    return{
        scope: {},
        controller: function ($scope, $http, $window) {
            $scope.groups2 = [];
            $scope.positions = [];
            $scope.showDispFields = true;
            $scope.messageInfo = "";
            $scope.messageShow = false;
            if ($window.localStorage.getItem("userRole") === '2'){
                $scope.showDispFields = false;
                angular.element(document.querySelector('#classroom')).css('width', "50px");
            }
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
            $scope.checkPositions = function () {
                if (checkFields() && checkFieldsPos()) {
                    var data = {
                        position: $scope.positions,
                        groups:  $scope.groups2,
                        subject: selectedSubject,
                        classroom: selectedClassroom,
                        professor: selectedTeacher,
                        lessonType: document.getElementById("type").value
                    };
                    $http.post(serverUrl + '/lesson/check', data, config).then(function (response) {
                        let answer = response.data;
                        for (let i = 0; i < answer.length; i++) {
                            let posIndex = $scope.positions.findIndex(pos => pos.num === answer[i].position);
                            if (posIndex !== -1){
                                let position = $scope.positions[posIndex];
                                position.status = 1;
                                let group = true;
                                for (let j = 0; j < answer[i].groups.length; j++) {
                                    if($scope.groups2[j].number)
                                        $scope.groups2[j].number = answer[i].groups[j].number;
                                    if(!answer[i].groups[j].number)
                                        group = false;
                                }
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
                                        let errGroups = $scope.groups2.filter(group => group.number === 0);
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
                                } else {
                                    $scope.messageInfo = "позиции корректны для добавления!";
                                    $scope.messageShow = true;
                                    document.querySelector('#addLesBut').disabled = false;
                                }
                            }
                        }
                    });
                }
            };
            $scope.addLesson = function(){
                let data = {
                    classroom: selectedClassroom,
                    groups: $scope.groups2,
                    lessonType: document.getElementById("type").value,
                    position: $scope.positions,
                    professor: selectedTeacher,
                    subject: selectedSubject
                }
                $http.post(serverUrl + '/lesson/add', data, {headers: {'Accept': 'text/plain'}}).then(function (response) {
                    if (response.data === "success"){
                        $scope.messageInfo = "занятие добавлено";
                        $scope.messageShow = true;
                        $scope.$emit('myCustomEvent', {
                            someProp: -1
                        });
                    }
                });
            }
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
                    let index = $scope.positions.findIndex(position => position.num === ''+pos_num);
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
                    $scope.$apply();
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
                    $scope.$emit('groupsSettEvent', {
                        someProp: $scope.groups2,
                        comment: true
                    });
                },
                close: function () {
                    document.getElementById('groups').value = "";
                    $scope.$apply();
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
            document.querySelector('#addPosBut').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
            document.querySelector('#checkLesBut').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
            document.querySelector('#addLesBut').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
            document.querySelector('#addLesBut').disabled = true;
        }, restrict: "E"
        , templateUrl: "../templates/addLessonForm.html"
        , transclude: true
    }
});

app.directive('updateLessonForm', function () {
    return{
        scope: {},
        controller: function ($scope, $http, $window) {
            $scope.messageShow = false;
            $scope.messageInfo = "";
            let newClassroomId;
            $scope.groups2 = [];

            function checkFields() {
                if (document.querySelector('#classroom').value === "" || newClassroomId === -1)
                    angular.element(document.querySelector('#classroom')).css('border-color', "red");
                else
                    angular.element(document.querySelector('#classroom')).css('border', "2px solid #cecece");
                if (''+document.querySelector('#week').value === "")
                    angular.element(document.querySelector('#weer')).css('border-color', "red");
                else
                    angular.element(document.querySelector('#weer')).css('border', "2px solid #cecece");
                if (document.querySelector('#classroom').value === "")
                    return false;
                else
                    return true;
            }
            let data;
            function getFields() {
                 return $http.get(serverUrl + '/lesson/edit/' + $window.localStorage.getItem("lessonToUpdateId"), config).then(function (response) {
                    return {
                        position: [{
                            num: "",
                            text: ""
                        }],
                        groups: response.data.groups,
                        subject: response.data.subjectId,
                        classroom: response.data.classroomId,
                        professor: response.data.professorId,
                        lessonType: ''+1
                    };

                });
            }
            $scope.checkPositions = function () {
                let pos_num = document.getElementById("week").value * 100 +
                    document.getElementById("workday").value * 10+
                    + document.getElementById("time").value;
                $scope.messageShow = false;
                if (pos_num < 100)
                    pos_num = "0" + pos_num;
                data.position[0].num = pos_num
                if (checkFields() && $window.localStorage.getItem("lessonToUpdateId") > 0) {
                    $http.post(serverUrl + '/lesson/check', data, config).then(function (response) {
                        let answer = response.data[0];
                                let group = true;
                                for (let j = 0; j < answer.groups.length; j++) {
                                    if(data.groups[j].number)
                                        data.groups[j].number = answer.groups[j].number;
                                    if(!answer.groups[j].number)
                                        group = false;
                                }
                                if (answer.professor === 0 || answer.classroom === 0 || !group){
                                    let message = "";
                                    if (answer.professor === 0)
                                        message = message + "в это время Вы уже заняты \n";
                                    if (answer.classroom === 0)
                                        message = message + "аудитория уже занята \n";
                                    if (!group){
                                        let errGroups = data.groups.filter(group => group.number === 0);
                                        if (errGroups.length > 1)
                                            message = message + "у групп ";
                                        else
                                            message = message + "у группы ";
                                        for (let j = 0; j < errGroups.length; j++) {
                                            message = message + errGroups[j].label + " ";
                                        }
                                        message = message + "уже есть пары";
                                    }
                                    $scope.messageInfo = message;
                                    $scope.messageShow = true;
                                    document.querySelector('#addLesBut').disabled = true;
                                } else {
                                    $scope.messageInfo = "корректно для переноса";
                                    $scope.messageShow = true;
                                    document.querySelector('#addLesBut').disabled = false;
                                }
                            });
                        }
            }
            $scope.updateLesson = function() {
                var pos_num = document.getElementById("week").value * 100 +
                    document.getElementById("workday").value * 10+
                    + document.getElementById("time").value;
                if (pos_num < 100)
                    pos_num = "0" + pos_num;
                let data = {
                    oldPositionId: $window.localStorage.getItem("lessonToUpdateId"),
                    newPositionNum: pos_num,
                    newClassroomId: newClassroomId
                }
                $http.post(serverUrl + '/lesson/update', data, {headers: {'Accept': 'text/plain'}}).then(function (response) {
                    if (response.data === "success"){
                        $scope.messageInfo = "занятие перенесено";
                        document.querySelector('#addLesBut').disabled = true;
                        $scope.messageShow = true;
                        $scope.$emit('myCustomEvent_update', {
                            someProp: -1,
                            type: "update"
                        });
                        $scope.$emit('myCustomEvent_update', {
                            someProp: -1,
                            type: "deleteBorder"
                        });
                        $scope.$emit('stopLessonUpdateGrid', {
                            prop: -1
                        });
                    }
                });
            }
            $http.get(serverUrl + '/university/weeks', config).then(function (response2) {
                $scope.weeks =  response2.data;
            });
            $http.post(serverUrl + '/workdays', config).then(function (response2) {
                $scope.workdays = response2.data;
            });
            $http.post(serverUrl + '/times', config).then(function (response2) {
                $scope.times = response2.data;
            });

            $scope.addPosition = function(){
                if (checkFieldsPos()){
                    var pos_num = document.getElementById("week").value * 100 +
                        document.getElementById("workday").value * 10+
                        + document.getElementById("time").value;
                    if (pos_num < 100)
                        pos_num = "0" + pos_num;
                    let index = $scope.positions.findIndex(position => position.num === ''+pos_num);
                    if(index === -1) {
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
                    }
                }
            };

            $('#classroom').click(function(){
                document.querySelector('#classroom').value = "";
                $(this).setCursorPosition(0);
            }).autocomplete({
                source: function (request, response) {
                    $http.post(serverUrl + '/classrooms', request.term, config).then(function (response2) {
                        response(response2.data);
                    });
                },
                minLength: 1,
                select: function displayItem(event, ui) {
                    angular.element(document.querySelector('#classroom')).css('border', "2px solid #cecece");
                    newClassroomId = ui.item.id;
                },
                open: function () {
                    newClassroomId = -1;
                    document.querySelector('#classroom').value = "";
                },
                close: function () {
                    if (newClassroomId === -1)
                        document.querySelector('#classroom').value = "";
                }
            });

            $scope.$on('sendToUpdateForm2', function (event, data2) {
                var position;
                $http.get(serverUrl + '/lesson/edit/' + data2.prop, config).then(function (response) {
                    position = response.data.lessonPosition;
                    document.querySelector('#time').value = position % 10;
                    console.log('time: ' + position % 10)
                    document.querySelector('#workday').value = (((position % 100) / 10)).toFixed(0);
                    console.log('workday: ' + (((position % 100) / 10)).toFixed(0))
                    document.querySelector('#week').value = ((position / 100).toFixed(0) - 1).toFixed(0);
                    console.log('week: ' + ((position / 100).toFixed(0) - 1))
                    newClassroomId = response.data.classroomId;
                    document.querySelector('#classroom').value = response.data.classroom;
                    let wait = $http.get(serverUrl + '/lesson/edit/' + $window.localStorage.getItem("lessonToUpdateId"), config).then(function (response) {
                        data =  {
                            position: [{
                                num: "",
                                text: ""
                            }],
                            groups: response.data.groups,
                            subject: response.data.subjectId,
                            classroom: response.data.classroomId,
                            professor: response.data.professorId,
                            lessonType: ''+1
                        };
                        return true;
                    });
                });


            });

            document.querySelector('#checkLesBut').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
            document.querySelector('#addLesBut').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
            document.querySelector('#addLesBut').disabled = true;
        }, restrict: "E"
        , templateUrl: "../templates/updateLessonForm.html"
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
            $scope.messageInfo = "";
            $scope.messageShow = false;
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

                if (!(($scope.uniData.workDays.monday)||($scope.uniData.workDays.tuesday)
                    ||($scope.uniData.workDays.wednesday)||($scope.uniData.workDays.thursday)
                    ||($scope.uniData.workDays.friday)||($scope.uniData.workDays.saturday)
                    ||($scope.uniData.workDays.sunday))) {
                    angular.element("#days").css('border', "2px solid red");
                } else
                    angular.element(document.querySelector('#days')).css('border', "0");
            }
            $.mask.definitions['q'] = '[A-Fa-f0-9]';
            $("#weeksUni").mask("9");
            $("#color1").mask("qqqqqq", {placeholder:"______"});
            $("#color2").mask("qqqqqq", {placeholder:"______"});
            $("#color3").mask("qqqqqq", {placeholder:"______"});
            $("#durationUni").mask("99");
            $(".lesson-input").mask("99:99");
            function restore_main($scope){
                document.querySelector('.register-fields').style.heigth = '420px';
                $scope.buttonLabel = "Далее";
                $scope.viewPage1 = true;
                $scope.viewPage2 = false;
                $scope.returnShow = false;
                $scope.messageShow = false;
            }

            function send($scope, $http){
                var lessons = {
                    position1: document.querySelector('#posLes1').value,
                    position2: document.querySelector('#posLes2').value,
                    position3: document.querySelector('#posLes3').value,
                    position4: document.querySelector('#posLes4').value,
                    position5: document.querySelector('#posLes5').value,
                    position6: document.querySelector('#posLes6').value,
                    position7: document.querySelector('#posLes7').value
                };
                var data = {
                    name: $scope.uniData.name,
                    weeks: document.querySelector('#weeksUni').value,
                    workDays: $scope.uniData.workDays,
                    lessonDuration: $scope.uniData.lessonDuration,
                    color1: document.querySelector('#color1').value,
                    color2: document.querySelector('#color2').value,
                    color3: document.querySelector('#color3').value,
                    logo : "",
                    lessonGridPosition: lessons
                };
                var url = serverUrl + "/university/update";
                $http.post(url, data, config).then(function (response) {
                    $scope.messageInfo = "Изменения сохранены! Обновите страницу.";
                    $scope.messageShow = true;
                }, function error(response) {
                    $scope.messageInfo = "Изменения сохранены! Обновите страницу.";
                    $scope.messageShow = true;
                });
                // jQuery(function ($) {
                //     $.mask.definitions['q'] = '[A-Fa-f0-9]';
                //     $("#color1").click(function() {
                //         $(this).setCursorPosition(1);
                //     }).mask("#qqqqqq");;
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
                    case "update":
                        template = '<grid-update></grid-update>';
                        break;
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
        restrict: 'E',
        replace: true,
        link: function(scope, element, attrs){
            var render = function(){
                var template = "";
                switch(attrs.type){
                    case "main":
                        template = '<search-block-main ></search-block-main>';
                        break;
                    case "dispatcher":
                        template = '<search-block-dispatcher></search-block-dispatcher>';
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
                        $scope.$apply();
                    }
                    $scope.$emit('groupsSettEvent', {
                        someProp: $scope.groups2,
                        comment: false
                    });
                    angular.element(document.querySelector('#groupsset')).css('border', "2px solid #cecece");
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
                groupsInitLength = $scope.groups2.length;
                $window.localStorage.setItem("groupsLength", $scope.groups2.length);
            });
            $scope.$emit('groupsSettEvent', {
                someProp: $scope.groups2,
                comment: false
            });
            $scope.codeSended = false;
            $scope.errorShowSet = false;
            $scope.errorMess = "";
            $scope.saveButEnable = true;
            $scope.codeButLabel = "выслать код";
            let phoneEdited = false;
            $scope.savesettings = function() {
                if ((document.querySelector('#telInputSet').value === $scope.user.tel) &&
                    (phoneEdited || document.querySelector('#nameInput').value !== $scope.user.name || $window.localStorage.getItem("groupsLength") !== (''+$scope.groups2.length))){
                    var data = {
                        groups: $scope.groups2,
                        id: $scope.user.id,
                        name: document.querySelector('#nameInput').value,
                        phone: document.querySelector('#telInputSet').value
                    };
                    $http.post(serverUrl + "/user/update", data , {headers: {'Accept': 'text/plain'}}).then(function (response) {
                        $scope.$emit('stopLessonUpdateGrid', {
                            prop: -1
                        });
                        $window.localStorage.setItem("userTel", document.querySelector('#telInputSet').value);
                        $window.localStorage.setItem("userName", document.querySelector('#nameInput').value);
                        $scope.messShow = true;
                        if (phoneEdited) {
                            if ($scope.user.role === '2') {
                                document.querySelector('#settingsPage').style.height = "250px";
                            }
                        }
                        $window.localStorage.setItem("groupsLength", $scope.groups2.length);
                        $scope.user.name = document.querySelector('#nameInput').value;
                    });
                }else{
                    if (document.querySelector('#telInputSet').value !== $scope.user.tel){
                        $scope.errorMess = "требуется подтвердить телефон смс кодом";
                        $scope.errorShowSet = true;
                        // document.querySelector('#settingsPage').style.height = "320px";
                        if ($scope.user.role === '2')
                            document.querySelector('#settingsPage').style.height = "210px";
                    }
                }
            };
            $scope.varifytel = function() {
                if (document.querySelector('#telInputSet').value !== $scope.user.tel) {
                    $http.post(serverUrl + "/login/checkPhone", document.getElementById('telInputSet').value, {headers: {'Accept': 'text/plain'}}).then(function (response) {
                        if (response.data === "registered") {
                            $scope.errorMess = "этот телефон уже зарегистрирован"
                            $scope.errorShowSet = true;
                            // document.querySelector('#settingsPage').style.height = "320px";
                            if ($scope.user.role === '2')
                                document.querySelector('#settingsPage').style.height = "210px";
                        } else {
                            $scope.saveButEnable = false;
                            document.querySelector('#saveSett').disabled = true;
                            if (!$scope.codeSended || $scope.errorMess === "неверный код подтверждения") {
                                $http.post(serverUrl + "/user/edit/phone", document.getElementById('telInputSet').value, {headers: {'Accept': 'text/plain'}}).then(function (response) {
                                    if (response.data === "codeSent") {
                                        $scope.errorShowSet = false;
                                        $scope.errorMess = "";
                                        // document.querySelector('#settingsPage').style.height = "290px";
                                        if ($scope.user.role === '2')
                                            document.querySelector('#settingsPage').style.height = "190px";
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
                                    if (response.data === "success") {
                                        phoneEdited = true;
                                        $scope.saveButEnable = true;
                                        $scope.errorMess = "новый телефон подтверждён"
                                        angular.element(document.querySelector('#telInputSet')).attr('readOnly', 'true');
                                        document.querySelector('#saveSett').disabled = false;
                                        $scope.errorShowSet = true;
                                        $scope.user.tel = document.querySelector('#telInputSet').value;
                                        // document.querySelector('#settingsPage').style.height = "320px";
                                        if ($scope.user.role === '2')
                                            document.querySelector('#settingsPage').style.height = "210px";
                                        $scope.codeButLabel = "выслать код";
                                        $scope.codeSended = false;
                                        angular.element(document.querySelector('#codeInput')).attr('readOnly', 'false');
                                    }
                                    if (response.data === "error") {
                                        $scope.errorMess = "неверный код подтверждения";
                                        $scope.codeButLabel = "выслать код";
                                        $scope.errorShowSet = true;
                                        // document.querySelector('#settingsPage').style.height = "320px";
                                        if ($scope.user.role === '2')
                                            document.querySelector('#settingsPage').style.height = "210px";
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
                document.querySelector('#settingsPage').style.height = "190px";
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

app.directive('parser', function () {
    return {
        scope: {},
        controller: function ($scope, $window, $http) {
            $scope.buttonPatserLabel = "";
            $scope.studentsTableShow = true;
            $scope.buttonParserLabel = "Отправить";
            $scope.messageParserShow = false;
            $scope.messageParser = "";
            $scope.sendToParse = function () {
                document.querySelector('#parserButtonId').disable = true;
                $scope.messageParserShow = true;
                $scope.messageParser = "Обработка...";
                $http.post(serverUrl + "/parser/url", document.querySelector('#parserUrl').value
                    , config).then(function (response) {
                    if (response.data.status === "Успешно") {
                        $scope.messageParserShow = true;
                        let message = "";
                        // $scope.messageParser = "Успешно!";
                        if (response.data.profnum !== 0)
                            message += "Добавлено " + response.data.profnum + " преподавателей<br>";
                        if (response.data.subjectnum !== 0)
                            message += "Добавлено " + response.data.subjectnum + " предметов\n";
                        if (response.data.roomnum !== 0)
                            response.data.message += "Добавлено " + response.data.roomnum + " аудиторий\n";
                        if (response.data.groupnum !== 0)
                            message += "Добавлено " + response.data.groupnum + " группа\n";
                        if (message === "")
                            message = "новых данных не обнаружено";
                        $scope.messageParser = message;
                    } else{
                        $scope.messageParserShow = true;
                        $scope.messageParser = response.data.status;
                    }
                });
            };
            $.mask.definitions['a'] = false;
            $.mask.definitions['+']='[A-Za-z0-9_/]';
            $("#parserUrl").mask("https://yadi.sk/?++++++++++++++++");
            document.querySelector('#parserDivId').style.backgroundColor = '#' + $window.localStorage.getItem("color2");
            document.querySelector('#parserButtonId').style.backgroundColor = '#' + $window.localStorage.getItem("color3");
        },
        restrict: "E",
        templateUrl: "../templates/parser.html"
        , transclude: true
    };
});

app.directive('groupList', function () {
    return {
        scope: {},
        controller: function ($scope, $window, $http) {
            $scope.students = [];
            let groupId = 1;
            $scope.studShow = false;
            $scope.studentsTableShow = true;
            $('#searchDead3').autocomplete({
                source: function (request, response) {
                    var data = {
                        request: request.term,
                        type: {
                            professor: false,
                            group: true,
                            classroom: false
                        }
                    };
                    $http.post(serverUrl + '/search', data, config).then(function (response2) {
                        response(response2.data);
                    });
                },
                minLength: 1,
                select: function displayItem(event, ui) {
                    groupId = ui.item.id;
                },
                close: function refresh(event, ui) {
                    $http.get(serverUrl + '/group/students/' + groupId, config).then(function (response) {
                        $scope.students = response.data;
                    });
                    $scope.studShow = true;
                    document.getElementById('searchDead').value = "";
                }
            });
            $("#studTableId").on("load",function(){
                $(".content").mCustomScrollbar();
            });
        },
        restrict: "E",
        templateUrl: "../templates/groupList.html"
        , transclude: true


    };
});

function showHide(element_id) {
    var timetable = document.getElementById('timetable');
    timetable.style.display = "none";
    var search = document.getElementById('search');
    search.style.display = "none";
    var settings = document.getElementById('settings');
    settings.style.display = "none";
    var lesson = document.getElementById('lessonAdd');
    if (lesson !== null)
        lesson.style.display = "none";
    lesson = document.getElementById('lessonUpdate');
    if (lesson !== null)
        lesson.style.display = "none";
    var group = document.getElementById('groupInfo');
    if (group !== null)
        group.style.display = "none";
    var parser = document.getElementById('parser');
    if (parser !== null)
        parser.style.display = "none";
    var obj = document.getElementById(element_id);
    if (obj.style.display != "block") {
        obj.style.display = "block";
    }
}

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

