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
        var color = university.color;
        document.getElementById('mainBlockId').style.backgroundColor = color;
        // alert(university.logotype);
        document.getElementById('logo').src = university.logotype;
        // alert(university.color);
    });

});