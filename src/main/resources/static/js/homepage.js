function showHide(element_id) {
    var timetable = document.getElementById('timetable');
    timetable.style.display = "none";
    var search = document.getElementById('search');
    search.style.display = "none";
    var settings = document.getElementById('settings');
    settings.style.display = "none";
    var obj = document.getElementById(element_id);
    if (obj.style.display != "block") {
        obj.style.display = "block"; 
    }
}

let university;
let user;
app.controller('control', function ($scope, $http) {
    $scope.logoShow = true;
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
        // document.getElementById('menu').style.borderTop = '18px solid ' + color2;
        // alert('18px solid ' + color2);
        // document.styleSheets[0].addRule('.menu-main:after', 'border-top: 18px solid ' + color2, 0);
        // ('.menu-main:after').css('border-top','18px solid ' + color2);
        // ('.menu-main:before').css('border-top','18px solid ' + color2);
    });

});