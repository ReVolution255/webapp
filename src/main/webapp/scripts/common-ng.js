var mainModule = angular.module('main', ['ngAnimate', 'ngRoute', 'ngResource', 'ui.bootstrap']);

mainModule.config(['$resourceProvider', function($resourceProvider) {
    $resourceProvider.defaults.stripTrailingSlashes = false;
}]);

mainModule.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/user/:userId', {
            templateUrl: 'userPermissions',
            controller: 'userPermissionsController'
        })
        .when('/group/:groupId', {
            templateUrl: 'groupUsers',
            controller: 'groupsController'
        })
        .when('/', {
            templateUrl: 'main'
        });
}]);

mainModule.filter('getById', function() {
    return function(input, id) {
        if (id == null) return {name: 'None'};
        var i=0, len=input.length;
        for (; i<len; i++) {
            if (+input[i].id == +id) {
                return input[i];
            }
        }
        return null;
    }
});

mainModule.filter('unique', function() {
    return function(collection, keyname) {
        var output = [],
            keys = [];

        angular.forEach(collection, function(item) {
            var key = item[keyname];
            if(keys.indexOf(key) === -1) {
                keys.push(key);
                output.push(item);
            }
        });

        return output;
    };
});

mainModule.controller('userPermissionsController', ['$scope', '$rootScope', '$http', '$routeParams', function ($scope, $rootScope, $http, $routeParams) {
    $scope.userId = $routeParams.userId;
    $scope.currentEditedUserPermissions = [];
    $scope.updateUserPermissions = function () {
        $http({method: 'GET', url: '/appmain/rest/report/userpermissions/' + $scope.userId, headers: {"Content-Type": "application/json"}})
            .success(function (data, status, headers, config) {
                $scope.currentEditedUserPermissions = angular.fromJson(data);
            })
            .error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    $scope.updateUserPermissions();
}]);

mainModule.controller('groupsController', ['$scope', '$rootScope', '$http', '$routeParams', function ($scope, $rootScope, $http, $routeParams) {
    $scope.groupId = $routeParams.groupId;
    $scope.allUsers = [];
    $scope.findUsers = function (){
        $http({method: 'GET', url: '/appmain/rest/report/groupusers/' + $scope.groupId, headers: {"Content-Type": "application/json"}})
            .success(function (data, status, headers, config) {
                $scope.allUsers = angular.fromJson(data);
            })
            .error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    $scope.findUsers();
}]);

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
        $rootScope.currentEditedUser = {id: '', name: 'Undefined'};
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
    $rootScope.userRoles = [];
    $rootScope.userGroups = [];
    $scope.updateUserRoles = function () {
        $http({method: 'GET', url: '/appmain/rest/userroles/', headers: {"Content-Type": "application/json"}})
            .success(function (data, status, headers, config) {
                var list = angular.fromJson(data);
                //Insert item, if inserted in server
                list.forEach(function (item, i, arr) {
                    var found = false;
                    $rootScope.userRoles.forEach(function (role, i, arr) {
                        if (item.id == role.id)
                            found = true;
                    });
                    if (!found && $scope.userRoles.indexOf(item) == -1) $rootScope.userRoles.push(item);
                });
                //Delete item, if deleted from server
                $rootScope.userRoles.forEach(function(item, i, arr) {
                    var found = true;
                    list.forEach(function (role, i, arr) {
                        if (item.id == role.id)
                            found = false;
                    });
                    if (found && $scope.userRoles.indexOf(item) == -1) $rootScope.userRoles.splice(i, 1);
                });
            })
            .error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    $scope.updateUserGroups = function () {
        $http({method: 'GET', url: '/appmain/rest/usergroups/', headers: {"Content-Type": "application/json"}})
            .success(function (data, status, headers, config) {
                var list = angular.fromJson(data);
                //Insert item, if inserted in server
                list.forEach(function (item, i, arr) {
                    var found = false;
                    $rootScope.userGroups.forEach(function (group, i, arr) {
                        if (item.id == group.id)
                            found = true;
                    });
                    if (!found && $scope.userGroups.indexOf(item) == -1) $rootScope.userGroups.push(item);
                });
                //Delete item, if deleted from server
                $rootScope.userGroups.forEach(function(item, i, arr) {
                    var found = true;
                    list.forEach(function (group, i, arr) {
                        if (item.id == group.id)
                            found = false;
                    });
                    if (found && $scope.userGroups.indexOf(item) == -1) $rootScope.userGroups.splice(i, 1);
                });
            })
            .error(function (data, status, headers, config) {
                console.log(status);
            });
    };

    $scope.updateUserRoles();
    $scope.updateUserGroups();

    $scope.update();
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
        $rootScope.currentEditedRole = {id: '', name: 'Undefined'};
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

    $rootScope.rolePermissions = [];
    $scope.updateRolePermissions = function () {
        $http({method: 'GET', url: '/appmain/rest/rolepermissions/', headers: {"Content-Type": "application/json"}})
            .success(function (data, status, headers, config) {
                var list = angular.fromJson(data);
                //Insert item, if inserted in server
                list.forEach(function (item, i, arr) {
                    var found = false;
                    $rootScope.rolePermissions.forEach(function (permission, i, arr) {
                        if (item.id == permission.id)
                            found = true;
                    });
                    if (!found && $scope.rolePermissions.indexOf(item) == -1) $rootScope.rolePermissions.push(item);
                });
                //Delete item, if deleted from server
                $rootScope.rolePermissions.forEach(function(item, i, arr) {
                    var found = true;
                    list.forEach(function (permission, i, arr) {
                        if (item.id == permission.id)
                            found = false;
                    });
                    if (found && $scope.rolePermissions.indexOf(item) == -1) $rootScope.rolePermissions.splice(i, 1);
                });
            })
            .error(function (data, status, headers, config) {
                console.log(status);
            });
    };

    $scope.updateRolePermissions();

    $rootScope.roles = [];
    $scope.update();
}]);

mainModule.controller('groupsListController', ['$scope', '$rootScope', '$http', '$modal', function ($scope, $rootScope, $http, $modal) {
    $scope.search = '';
    $scope.animationsEnabled = true;

    $scope.allGroups = [];

    $scope.findSubGroups = function (groupId){
        $rootScope.groups.forEach(function (group, i, arr) {
            if (group.parent_id == groupId && $scope.isUnique(group)) {
                $scope.allGroups.push(group);
                $scope.findSubGroups(group.id);
            }
        });
    };

    $scope.isUnique = function (item){
        var found = true;
        $scope.allGroups.forEach(function (elem, i, arr) {
            if (item.id == elem.id) found = false;
        });
        return found;
    };

    //CRUD: Delete
    $scope.delete = function (group) {
        var id = group.id;
        $http({method: 'DELETE', headers: {"Content-Type": "application/json"}, url: '/appmain/rest/groups/', data: {id: id}}).success(function (data, status, headers, config) {
            $rootScope.groups.splice($rootScope.groups.indexOf(group), 1);
            $scope.findSubGroups(group.id);
            $scope.allGroups.forEach(function (item) {
                $rootScope.groups.splice($rootScope.groups.indexOf(item), 1);
            });
            $scope.allGroups = [];
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