var continentsApp = angular.module("continentsApp", []);

continentsApp.controller("continentsCtrl", function ($scope, $http) {
    $http.get('http://localhost:8080/continent').then(function (response) {
        $scope.continents = response.data;
    })
    $scope.edit = true;
    $scope.error = false;
    $scope.incomplete = false;
    $scope.hideform = true;
})