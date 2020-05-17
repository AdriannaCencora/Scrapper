var countryApp = angular.module("countryApp", []);

countryApp.controller("countryCtrl", function ($scope, $http) {
    $http.get('http://localhost:8080/countries/infections_current/'+window.location.href.substring(window.location.href.lastIndexOf('/'))).then(function (response) {
        $scope.country = response.data;
        $scope.chart_data = {
            "totalCases": [
                ["Country", "totalCases"],
                ["totalCases",$scope.country.totalCases],
                ["healthy", $scope.country.population - $scope.country.totalCases]
            ],
            "patientsResults": [
                ["Country", "patientsResults"],
                ["totalDeaths", $scope.country.totalDeaths],
                ["totalRecovered", $scope.country.totalRecovered],
                ["activeCases", $scope.country.activeCases]
            ],
            "patientsStates": [
                ["Country", "patientsStates"],
                ["seriousCritical", $scope.country.seriousCritical],
                ["other", $scope.country.activeCases - $scope.country.seriousCritical]
            ]
        };
    })
    $scope.edit = true;
    $scope.error = false;
    $scope.incomplete = false;
    $scope.hideform = true;
})