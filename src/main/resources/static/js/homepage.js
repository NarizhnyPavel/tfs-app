function showHide(element_id) {
    var timetable = document.getElementById('timetable');
    timetable.style.display = "none";
    var search = document.getElementById('search');
    search.style.display = "none";
    var settings = document.getElementById('settings');
    settings.style.display = "none";
    var obj = document.getElementById(element_id);
    if (obj.style.display != "block") {
        obj.style.display = "block"; //Показываем элемент
    }
}

let university;
app.controller('control', function ($scope, $http) {
    $scope.logoShow = true;
    var config = {
        headers: {
            'Content-Type': 'application/json'
        }
    };
    $http.get('/university/1', config).then(function (response) {
        university = response.data;
        var color1 = "#" + university.color;
        var color2 = "" + ( Number(parseInt(university.color, 16) - 35).toString(16) );
        let regexp = /[a-f0-9]{6}/gi;
        while (color2.match(regexp) === null) {
            color2 = "0" + color2;
        }
        color2 = "#" + color2;
        document.getElementById('mainBlockId').style.backgroundColor = color1;
        document.getElementById('menu').style.backgroundColor = color2;
        // document.getElementById('menu').style.borderTop = '18px solid ' + color2;
        // alert('18px solid ' + color2);
        // document.styleSheets[0].addRule('.menu-main:after', 'border-top: 18px solid ' + color2, 0);
        // ('.menu-main:after').css('border-top','18px solid ' + color2);
        // ('.menu-main:before').css('border-top','18px solid ' + color2);
    });

});