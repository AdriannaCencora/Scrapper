var topBarApp = angular.module('topBarApp', []);

topBarApp.controller('topBarCtrl', function ($scope, $http) {
        $scope.changeView = function (path) {
            window.location = path;
        }

        $scope.UpdateData = function () {
            $http.get('http://localhost:8080/infections/new_data').then(function (response) {})
        }
    }
);
