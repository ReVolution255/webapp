var mainModule = angular.module('main', ['ngAnimate', 'ngRoute', 'ngResource', 'ui.bootstrap']);

mainModule.config(['$resourceProvider', function($resourceProvider) {
    $resourceProvider.defaults.stripTrailingSlashes = false;
}]);

mainModule.controller('usersListController', ['$scope', '$rootScope', '$http', '$modal', '$interval', function ($scope, $rootScope, $http, $modal, $interval) {
    $scope.search = '';
    $scope.animationsEnabled = true;
    $scope.$addModal = {};
    $scope.$editModal = {};
    //CRUD: Delete
    $scope.delete = function (user) {
        var id = user.id;
        $http({method: 'DELETE', headers: {"Content-Type": "application/json"}, url: '/appmain/users/', data: {id: id}}).success(function (data, status, headers, config) {
            $rootScope.users.slice($rootScope.users.indexOf(user, 1));
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    //CRUD: Read Scope: Main
    $scope.update = function () {
        $http({method: 'GET',headers: {"Content-Type": "application/json"}, url: '/appmain/users/'}).success(function (data, status, headers, config) {
            $rootScope.users = angular.fromJson(data);
/*            var list = angular.fromJson(data);
            //Insert item, if inserted in server
            list.forEach(function (item, i, arr) {
                if ($rootScope.users.lastIndexOf(item) == -1) {
                    $rootScope.users.push(item);
                }
            });
            //Delete item, if deleted from server
            $rootScope.users.forEach(function(item, i, arr) {
                if (list.lastIndexOf(item) == -1) {
                    $rootScope.users.splice($rootScope.users.indexOf(item), 1);
                }
            });*/
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };

    $scope.open = function () {
        $scope.$addModal = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'addUserModal',
            scope: $rootScope,
            controller: 'modalController'
        });
    };
    $scope.editModal = function (user) {
        $rootScope.currentEditedUser = user;
        $scope.$editModal = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'editUserModal',
            scope: $rootScope,
            controller: 'modalController'
        });
    };

    $rootScope.users = [];
    $scope.update();
}]);

mainModule.controller('modalController', ['$scope', '$rootScope', '$http', '$modal', '$interval', function ($scope, $rootScope, $http, $modal, $interval) {
    $scope.animationsEnabled = true;

    $scope.newUserName = '';
    //CRUD: Update
    $scope.edit = function (user){
        $scope.$close();
        $rootScope.currentEditedUser = {id: 'Undefined', name: ''};
        $http({method: 'PUT', url: '/appmain/users/', data: angular.toJson(user)}).success(function (data, status, headers, config) {
            $rootScope.users[$rootScope.users.indexOf(user)] = user;
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    //CRUD: Create
    $scope.create = function (){
        var user = {name: $scope.newUserName};
        $scope.$close();
        $http({method: 'POST', url: '/appmain/users/', headers: {"Content-Type": "application/json"}, data: angular.toJson(user)}).success(function (data, status, headers, config) {
            data = angular.fromJson(data);
            for (var i = data.length - 1; i >= 0; i--) {
                if (data[i].name == user.name) {
                    user.id = data[i].id;
                    break;
                }
            }
            $rootScope.users.push(user);
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
        $scope.newUserName = '';
    };
    //CRUD: Read Scope: Modal
    $scope.update = function () {
        $http({method: 'GET',headers: {"Content-Type": "application/json"}, url: '/appmain/users/'}).success(function (data, status, headers, config) {
            //$rootScope.users = angular.fromJson(data);
            var list = angular.fromJson(data);
            //Insert item, if inserted in server
            list.forEach(function (item, i, arr) {
                if ($rootScope.users.lastIndexOf(item) == -1) {
                    $rootScope.users.push(item);
                }
            });
            //Delete item, if deleted from server
            $rootScope.users.forEach(function(item, i, arr) {
                if (list.lastIndexOf(item) == -1) {
                    $rootScope.users.splice($rootScope.users.indexOf(item), 1);
                }
            });
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
}]);