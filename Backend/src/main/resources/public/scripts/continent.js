var continentApp = angular.module("continentApp", []);

continentApp.controller("continentCtrl", function ($scope, $http) {
    $http.get('http://localhost:8080/continent/infections_current/'+window.location.href.substring(window.location.href.lastIndexOf('/'))).then(function (response) {
        $scope.continent = response.data;
        if ($scope.continent.length > 0) {
            $scope.name = $scope.continent[0].country.name;
            $scope.chart_data = {
                "totalCases": [["Country", "totalCases"]],
                "newCases": [["Country", "newCases"]],
                "totalDeaths": [["Country", "totalDeaths"]],
                "newDeaths": [["Country", "newDeaths"]],
                "totalRecovered": [["Country", "totalRecovered"]],
                "activeCases": [["Country", "activeCases"]],
                "seriousCritical": [["Country", "seriousCritical"]],
                "totCases1Mpop": [["Country", "totCases1Mpop"]],
                "deaths1Mpop": [["Country", "deaths1Mpop"]],
                "totalTests": [["Country", "totalTests"]],
                "tests1Mpop": [["Country", "tests1Mpop"]]
            };
            for(var key in $scope.chart_data){
                var len = $scope.continent.length;
                for(var i = 0 ; i < len; i++){
                    $scope.chart_data[key].push([$scope.continent[i].country.name, $scope.continent[i][key]])
                }
            }
        }
    })
    $scope.edit = true;
    $scope.error = false;
    $scope.incomplete = false;
    $scope.hideform = true;
})