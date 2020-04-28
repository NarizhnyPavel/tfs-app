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