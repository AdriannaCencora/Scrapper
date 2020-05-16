var topBarApp = angular.module('topBarApp', []);

topBarApp.controller('topBarCtrl', function ($scope, $location) {
        $scope.changeView = function (path) {
            window.location = path;
        }
    }
);
