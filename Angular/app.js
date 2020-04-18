var myApp = angular.module('myApp', []);

myApp.controller('mainController', function($scope) {

});

var searchPeople = function (firstname, lastname, height, age, occupation) {
    return 'Joe doe';
}

console.log(searchPeople);

console.log(angular.injector().annotate(searchPeople));