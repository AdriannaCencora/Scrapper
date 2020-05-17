var countrysApp = angular.module("countrysApp", []);

countrysApp.controller("countrysCtrl", function ($scope, $http) {
    $http.get('http://localhost:8080/countries').then(function (response) {
        $scope.countries = response.data;
    })
    $scope.edit = true;
    $scope.error = false;
    $scope.incomplete = false;
    $scope.hideform = true;
})