var mainModule = angular.module('main', ['ngAnimate', 'ngRoute', 'ngResource', 'ui.bootstrap']);

mainModule.config(['$resourceProvider', function($resourceProvider) {
    $resourceProvider.defaults.stripTrailingSlashes = false;
}]);

mainModule.filter('getById', function() {
    return function(input, id) {
        var i=0, len=input.length;
        for (; i<len; i++) {
            if (+input[i].id == +id) {
                return input[i];
            }
        }
        return null;
    }
});

mainModule.controller('usersListController', ['$scope', '$rootScope', '$http', '$modal', function ($scope, $rootScope, $http, $modal) {
    $scope.search = '';
    $scope.animationsEnabled = true;
    $scope.$addModal = {};
    $scope.$editModal = {};
    //CRUD: Delete
    $scope.delete = function (user) {
        var id = user.id;
        $http({method: 'DELETE', headers: {"Content-Type": "application/json"}, url: '/appmain/rest/users/', data: {id: id}}).success(function (data, status, headers, config) {
            $rootScope.users.splice($rootScope.users.indexOf(user), 1);
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    //CRUD: Read Scope: Main
    $scope.update = function () {
        $http({method: 'GET',headers: {"Content-Type": "application/json"}, url: '/appmain/rest/users/'}).success(function (data, status, headers, config) {
            $rootScope.users = angular.fromJson(data);
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

mainModule.controller('modalController', ['$scope', '$rootScope', '$http', '$modal', function ($scope, $rootScope, $http) {
    $scope.animationsEnabled = true;

    $scope.newUserName = '';
    //CRUD: Update
    $scope.edit = function (user){
        $scope.$close();
        $rootScope.currentEditedUser = {id: 'Undefined', name: ''};
        $http({method: 'PUT', url: '/appmain/rest/users/', data: angular.toJson(user)}).success(function (data, status, headers, config) {
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
        $http({method: 'POST', url: '/appmain/rest/users/', headers: {"Content-Type": "application/json"}, data: angular.toJson(user)}).success(function (data, status, headers, config) {
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
        $http({method: 'GET',headers: {"Content-Type": "application/json"}, url: '/appmain/rest/users/'}).success(function (data, status, headers, config) {
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

mainModule.controller('rolesListController', ['$scope', '$rootScope', '$http', '$modal', function ($scope, $rootScope, $http, $modal) {
    $scope.search = '';
    $scope.animationsEnabled = true;

    //CRUD: Delete
    $scope.delete = function (role) {
        var id = role.id;
        $http({method: 'DELETE', headers: {"Content-Type": "application/json"}, url: '/appmain/rest/roles/', data: {id: id}}).success(function (data, status, headers, config) {
            $rootScope.roles.splice($rootScope.roles.indexOf(role), 1);
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    //CRUD: Read Scope: Main
    $scope.update = function () {
        $http({method: 'GET',headers: {"Content-Type": "application/json"}, url: '/appmain/rest/roles/'}).success(function (data, status, headers, config) {
            $rootScope.roles = angular.fromJson(data);
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };

    $scope.addRole = function () {
        $scope.$addModal = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'addRoleModal',
            scope: $rootScope,
            controller: 'modalRoleController'
        });
    };
    $scope.editRole = function (role) {
        $rootScope.currentEditedRole = role;
        $scope.$editModal = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'editRoleModal',
            scope: $rootScope,
            controller: 'modalRoleController'
        });
    };

    $rootScope.roles = [];
    $scope.update();
}]);

mainModule.controller('modalRoleController', ['$scope', '$rootScope', '$http', '$modal', function ($scope, $rootScope, $http) {
    $scope.animationsEnabled = true;

    $scope.newRoleName = '';
    //CRUD: Update
    $scope.edit = function (role){
        $scope.$close();
        $rootScope.currentEditedRole = {id: 'Undefined', name: ''};
        $http({method: 'PUT', url: '/appmain/rest/roles/', data: angular.toJson(role)}).success(function (data, status, headers, config) {
            $rootScope.roles[$rootScope.roles.indexOf(role)] = role;
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    //CRUD: Create
    $scope.create = function (){
        var role = {name: $scope.newRoleName};
        $scope.$close();
        $http({method: 'POST', url: '/appmain/rest/roles/', headers: {"Content-Type": "application/json"}, data: angular.toJson(role)}).success(function (data, status, headers, config) {
            data = angular.fromJson(data);
            for (var i = data.length - 1; i >= 0; i--) {
                if (data[i].name == role.name) {
                    role.id = data[i].id;
                    break;
                }
            }
            $rootScope.roles.push(role);
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
        $scope.newRoleName = '';
    };
    //CRUD: Read Scope: Modal
    $scope.update = function () {
        $http({method: 'GET',headers: {"Content-Type": "application/json"}, url: '/appmain/rest/roles/'}).success(function (data, status, headers, config) {
            var list = angular.fromJson(data);
            //Insert item, if inserted in server
            list.forEach(function (item, i, arr) {
                if ($rootScope.roles.lastIndexOf(item) == -1) {
                    $rootScope.roles.push(item);
                }
            });
            //Delete item, if deleted from server
            $rootScope.roles.forEach(function(item, i, arr) {
                if (list.lastIndexOf(item) == -1) {
                    $rootScope.roles.splice($rootScope.roles.indexOf(item), 1);
                }
            });
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
}]);

mainModule.controller('groupsListController', ['$scope', '$rootScope', '$http', '$modal', function ($scope, $rootScope, $http, $modal) {
    $scope.search = '';
    $scope.animationsEnabled = true;

    //CRUD: Delete
    $scope.delete = function (group) {
        var id = group.id;
        $http({method: 'DELETE', headers: {"Content-Type": "application/json"}, url: '/appmain/rest/groups/', data: {id: id}}).success(function (data, status, headers, config) {
            $rootScope.groups.splice($rootScope.groups.indexOf(group), 1);
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    //CRUD: Read Scope: Main
    $scope.update = function () {
        $http({method: 'GET',headers: {"Content-Type": "application/json"}, url: '/appmain/rest/groups/'}).success(function (data, status, headers, config) {
            $rootScope.groups = angular.fromJson(data);
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };

    $scope.addGroup = function () {
        $scope.$addModal = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'addGroupModal',
            scope: $rootScope,
            controller: 'modalGroupController'
        });
    };
    $scope.editGroup = function (group) {
        $rootScope.currentEditedGroup = group;
        $scope.$editModal = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'editGroupModal',
            scope: $rootScope,
            controller: 'modalGroupController'
        });
    };

    $rootScope.groups = [];
    $scope.update();
}]);

mainModule.controller('modalGroupController', ['$scope', '$rootScope', '$http', '$filter', function ($scope, $rootScope, $http, $filter) {
    $scope.animationsEnabled = true;

    $scope.newGroupName = '';
    $scope.parentId = '';
    $scope.parent = $filter('getById')($rootScope.groups, $rootScope.currentEditedGroup.parent_id);
    //CRUD: Update
    $scope.edit = function (group){
        $scope.$close();
        $rootScope.currentEditedGroup = {id: 'Undefined', name: ''};
        $http({method: 'PUT', url: '/appmain/rest/groups/', data: angular.toJson(group)}).success(function (data, status, headers, config) {
            $rootScope.groups[$rootScope.groups.indexOf(group)] = group;
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    //CRUD: Create
    $scope.create = function (){
        var group = {name: $scope.newGroupName, parent_id: $scope.parentId};
        $scope.$close();
        $http({method: 'POST', url: '/appmain/rest/groups/', headers: {"Content-Type": "application/json"}, data: angular.toJson(group)}).success(function (data, status, headers, config) {
            data = angular.fromJson(data);
            for (var i = data.length - 1; i >= 0; i--) {
                if (data[i].name == group.name) {
                    group.id = data[i].id;
                    break;
                }
            }
            $rootScope.groups.push(group);
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
        $scope.newGroupName = '';
        $scope.parentId = null;
    };
    //CRUD: Read Scope: Modal
    $scope.update = function () {
        $http({method: 'GET',headers: {"Content-Type": "application/json"}, url: '/appmain/rest/roles/'}).success(function (data, status, headers, config) {
            var list = angular.fromJson(data);
            //Insert item, if inserted in server
            list.forEach(function (item, i, arr) {
                if ($rootScope.roles.lastIndexOf(item) == -1) {
                    $rootScope.roles.push(item);
                }
            });
            //Delete item, if deleted from server
            $rootScope.roles.forEach(function(item, i, arr) {
                if (list.lastIndexOf(item) == -1) {
                    $rootScope.roles.splice($rootScope.roles.indexOf(item), 1);
                }
            });
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
}]);

mainModule.controller('permissionsListController', ['$scope', '$rootScope', '$http', '$modal', function ($scope, $rootScope, $http, $modal) {
    $scope.search = '';
    $scope.animationsEnabled = true;

    //CRUD: Delete
    $scope.delete = function (permission) {
        var id = permission.id;
        $http({method: 'DELETE', headers: {"Content-Type": "application/json"}, url: '/appmain/rest/permissions/', data: {id: id}}).success(function (data, status, headers, config) {
            $rootScope.permissions.splice($rootScope.permissions.indexOf(permission), 1);
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    //CRUD: Read Scope: Main
    $scope.update = function () {
        $http({method: 'GET',headers: {"Content-Type": "application/json"}, url: '/appmain/rest/permissions/'}).success(function (data, status, headers, config) {
            $rootScope.permissions = angular.fromJson(data);
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };

    $scope.addPermission = function () {
        $scope.$addModal = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'addPermissionModal',
            scope: $rootScope,
            controller: 'modalPermissionsController'
        });
    };
    $scope.editPermission = function (permission) {
        $rootScope.currentEditedPermission = permission;
        $scope.$editModal = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'editPermissionModal',
            scope: $rootScope,
            controller: 'modalPermissionsController'
        });
    };

    $rootScope.permissions = [];
    $scope.update();
}]);

mainModule.controller('modalPermissionsController', ['$scope', '$rootScope', '$http', '$modal', function ($scope, $rootScope, $http) {
    $scope.animationsEnabled = true;

    $scope.newPermissionName = '';
    //CRUD: Update
    $scope.edit = function (permission){
        $scope.$close();
        $rootScope.currentEditedPermission = {id: 'Undefined', name: ''};
        $http({method: 'PUT', url: '/appmain/rest/permissions/', data: angular.toJson(permission)}).success(function (data, status, headers, config) {
            $rootScope.permissions[$rootScope.permissions.indexOf(permission)] = permission;
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    //CRUD: Create
    $scope.create = function (){
        var permission = {name: $scope.newPermissionName};
        $scope.$close();
        $http({method: 'POST', url: '/appmain/rest/permissions/', headers: {"Content-Type": "application/json"}, data: angular.toJson(permission)}).success(function (data, status, headers, config) {
            data = angular.fromJson(data);
            for (var i = data.length - 1; i >= 0; i--) {
                if (data[i].name == permission.name) {
                    permission.id = data[i].id;
                    break;
                }
            }
            $rootScope.permissions.push(permission);
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
        $scope.newPermissionName = '';
    };
    //CRUD: Read Scope: Modal
    $scope.update = function () {
        $http({method: 'GET',headers: {"Content-Type": "application/json"}, url: '/appmain/rest/permissions/'}).success(function (data, status, headers, config) {
            var list = angular.fromJson(data);
            //Insert item, if inserted in server
            list.forEach(function (item, i, arr) {
                if ($rootScope.permissions.lastIndexOf(item) == -1) {
                    $rootScope.permissions.push(item);
                }
            });
            //Delete item, if deleted from server
            $rootScope.permissions.forEach(function(item, i, arr) {
                if (list.lastIndexOf(item) == -1) {
                    $rootScope.permissions.splice($rootScope.permissions.indexOf(item), 1);
                }
            });
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
}]);