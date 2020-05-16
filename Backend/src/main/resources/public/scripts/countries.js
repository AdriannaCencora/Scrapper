var countryApp = angular.module("countryApp", []);

countryApp.controller("countryCtrl", function ($scope, $http) {
    $http.get('http://localhost:8080/countries').then(function (response) {
        $scope.countries = response.data;
        console.log($scope.countries)
    })
    $scope.edit = true;
    $scope.error = false;
    $scope.incomplete = false;
    $scope.hideform = true;
})