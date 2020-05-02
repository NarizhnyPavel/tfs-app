let selectedTeacher;
let selectedClassroom;
let selectedGroups;
var professors;

$(function () {
    'use strict';
    var professors2 = [
        {
            label: "Титов"
            , id: "3"
        }, {
            label: "Глущенко"
            , id: "2"
        }
    ];
    var classrooms2 = [
        {
            label: "1238"
            , id: "4"
        }, {
            label: "Глущенко"
            , id: "2"
        }
    ];
//     $('#teacher').autocomplete({
//         source: professors2
//         ,
//         select: function(event, ui) {
// //            $('#autocomplete-ajax').val(ui.item.value);
//             alert(ui.item.id);
//         }
//     })

    // $('#teacher').autocomplete({
    //     source: function(request, response, http){
    //         // angular.
    //         $.ajax({
    //             url: "/professors/" + request.term,
    //             dataType: "jsonp",
    //             // параметры запроса, передаваемые на сервер (последний - подстрока для поиска):
    //             data:{
    //             },
    //             // обработка успешного выполнения запроса
    //             success: function(data){
    //                 response = data;
    //                 // // приведем полученные данные к необходимому формату и передадим в предоставленную функцию response
    //                 // response($.map(data.geonames, function(item){
    //                 //     return{
    //                 //         label: item.name + (item.adminName1 ? ", " + item.adminName1 : "") + ", " + item.countryName,
    //                 //         value: item.name
    //                 //     }
    //                 // }));
    //             }
    //         });
    //     },
    //     minLength: 2
    // });
    // $( "#tags" ).autocomplete({
    //     source: function(request, response){
    //         // организуем кроссдоменный запрос
    //         $.ajax({
    //             url: "http://ws.geonames.org/searchJSON",
    //             dataType: "jsonp",
    //             // параметры запроса, передаваемые на сервер (последний - подстрока для поиска):
    //             data:{
    //                 featureClass: "P",
    //                 style: "full",
    //                 maxRows: 12,
    //                 name_startsWith: request.term
    //             },
    //             // обработка успешного выполнения запроса
    //             success: function(data){
    //                 // приведем полученные данные к необходимому формату и передадим в предоставленную функцию response
    //                 response($.map(data.geonames, function(item){
    //                     return{
    //                         label: item.name + (item.adminName1 ? ", " + item.adminName1 : "") + ", " + item.countryName,
    //                         value: item.name
    //                     }
    //                 }));
    //             }
    //         });
    //     },
    //     minLength: 2
    // });

    function displayItem(event, ui) {
        $('#autocomplete-ajax').text(ui.item.label);
        selectedTeacher = ui.item.value;
        alert(selectedTeacher + ' hi!');
    }
});

var app = angular.module('pgApp', []);
app.controller('control', function ($scope, $http) {
    $scope.show = function () {
        alert('teacherId: ' + selectedTeacher + '\n'+
            'classroomId: ' + selectedClassroom + '\n' +
            'groupsId: ' + $scope.groups2.length)
    } ;
    $scope.groups = [{label:'7371', id: 1}, {label:'7372', id: "2"}, {label:'8473', id: "3"}];
    $scope.groups2 = [];
    $scope.groupsLabel = "";
    var config = {
        headers: {
            'Content-Type': 'application/json'
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
            if(index === -1)
                $scope.groups2.push(ui.item);
            $scope.$apply();
        }
    });

    $('#subject').autocomplete({
        source: function (request, response) {
            $http.post('/subjects', request.term, config).then(function (response2) {
                response(response2.data);
            });
        },
        minLength: 2,
        select: function displayItem(event, ui) {
            let index = $scope.groups2.findIndex(group => group.id === ui.item.id);
            if(index === -1)
                $scope.groups2.push(ui.item);
            $scope.$apply();
        }
    });
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