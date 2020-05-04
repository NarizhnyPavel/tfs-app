app.directive('searchBlock', function () {
    return {
        controller: function ($scope, $attrs, $http) {
            $scope.search = function () {
            }
            var config = {
                headers: {
                    'Content-Type': 'application/json'
                }
            };
            // $('#searchInput').autocomplete({
            //     source: function (request, response) {
            //         $http.post('/search', request.term, config).then(function (response2) {
            //             response(response2.data);
            //         });
            //     },
            //     minLength: 2,
            //     select: function displayItem(event, ui) {
            //         alert(ui.item.id + " " + ui.item.label + " " + ui.item.type);
            //         // selectedTeacher = ui.item.id;
            //     }
            // });
            $('#searchInput').autocomplete({
                source: function (request, response) {
                    $http.post('/classrooms', request.term, config).then(function (response2) {
                        response(response2.data);
                    });
                },
                minLength: 1,
                select: function displayItem(event, ui) {
                    // selectedClassroom = ui.item.id;
                }
            });
        }
        , restrict: "E"
        , templateUrl: "../templates/searchPage.html"
    }
});