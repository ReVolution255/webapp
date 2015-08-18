mainModule.controller('modalController', ['$scope', '$rootScope', '$http', function ($scope, $rootScope, $http) {
    $scope.animationsEnabled = true;

    //Initialization
    $scope.newUserName = '';
    $scope.currentEditedUserRoles = [];
    $scope.selectedRole = '';
    $scope.allowedRoles = [];

    $scope.getRoleName = function (id){
        var input = $scope.roles;
        var i=0, len=input.length;
        for (; i<len; i++) {
            if (+input[i].id == +id) {
                return input[i].name;
            }
        }
        return 'Error';
    };

    $scope.currentEditedUserGroups = [];
    $scope.selectedGroup = '';
    $scope.allowedGroups = [];

    $scope.getGroupName = function (id){
        var input = $scope.groups;
        var i=0, len=input.length;
        for (; i<len; i++) {
            if (+input[i].id == +id) {
                return input[i].name;
            }
        }
        return 'Error';
    };

    //User roles CRUD: Read
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
                $scope.updateCurrentEditedUserRoles();
                $scope.updateAllowedUserRoles();
            })
            .error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    $scope.updateAllowedUserRoles = function () {
        $scope.allowedRoles = [];

        $rootScope.roles.forEach(function (item, i, arr) {
            var found = false;
            $scope.currentEditedUserRoles.forEach(function(role, i, arr){
                if (item.id == role.role_id) found = true;
            });
            if (!found && $scope.allowedRoles.indexOf(item) == -1) $scope.allowedRoles.push(item);
        });
    };
    $scope.updateCurrentEditedUserRoles = function () {
        $scope.currentEditedUserRoles = [];
        $rootScope.userRoles.forEach(function (item, i, arr) {
            if (item.user_id == $scope.currentEditedUser.id) $scope.currentEditedUserRoles.push(item);
        });
    };
    //User roles CRUD: Delete
    $scope.deleteUserRole = function (role) {
        var id = role.id;
        $http({method: 'DELETE', headers: {"Content-Type": "application/json"}, url: '/appmain/rest/userroles/', data: {id: id}}).success(function (data, status, headers, config) {
            $scope.currentEditedUserRoles.splice($scope.currentEditedUserRoles.indexOf(role), 1);
            $rootScope.userRoles.splice($scope.userRoles.indexOf(role), 1);
            $scope.updateAllowedUserRoles();
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    //User roles CRUD: Create
    $scope.addUserRole = function () {
        var role_id = $scope.selectedRole;
        var user_id = $scope.currentEditedUser.id;
        var temp = {role_id: role_id,
            user_id: user_id};
        $http({method: 'POST', url: '/appmain/rest/userroles/', headers: {"Content-Type": "application/json"}, data: angular.toJson(temp)}).success(function (data, status, headers, config) {
            $scope.updateUserRoles();
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };

    //User groups CRUD: Read
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
                $scope.updateCurrentEditedUserGroups();
                $scope.updateAllowedUserGroups();
            })
            .error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    $scope.updateAllowedUserGroups = function () {
        $scope.allowedGroups = [];

        $rootScope.groups.forEach(function (item, i, arr) {
            var found = false;
            $scope.currentEditedUserGroups.forEach(function(group, i, arr){
                if (item.id == group.group_id) found = true;
            });
            if (!found && $scope.allowedGroups.indexOf(item) == -1) $scope.allowedGroups.push(item);
        });
    };
    $scope.updateCurrentEditedUserGroups = function () {
        $scope.currentEditedUserGroups = [];
        $rootScope.userGroups.forEach(function (item, i, arr) {
            if (item.user_id == $scope.currentEditedUser.id) $scope.currentEditedUserGroups.push(item);
        });
    };
    //User groups CRUD: Delete
    $scope.deleteUserGroup = function (group) {
        var id = group.id;
        $http({method: 'DELETE', headers: {"Content-Type": "application/json"}, url: '/appmain/rest/usergroups/', data: {id: id}}).success(function (data, status, headers, config) {
            $scope.currentEditedUserGroups.splice($scope.currentEditedUserGroups.indexOf(group), 1);
            $rootScope.userGroups.splice($scope.userGroups.indexOf(group), 1);
            $scope.updateAllowedUserGroups();
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    //User groups CRUD: Create
    $scope.addUserGroup = function () {
        var group_id = $scope.selectedGroup;
        var user_id = $scope.currentEditedUser.id;
        var temp = {group_id: group_id,
            user_id: user_id};
        $http({method: 'POST', url: '/appmain/rest/usergroups/', headers: {"Content-Type": "application/json"}, data: angular.toJson(temp)}).success(function (data, status, headers, config) {
            $scope.updateUserGroups();
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };

    //CRUD: Update
    $scope.edit = function (user){
        $scope.$close();
        $rootScope.currentEditedUser = {};
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

    $scope.updateUserRoles();
    $scope.updateUserGroups();
}]);

mainModule.controller('modalRoleController', ['$scope', '$rootScope', '$http', function ($scope, $rootScope, $http) {
    $scope.animationsEnabled = true;

    $scope.newRoleName = '';
    $scope.currentEditedRolePermissions = [];
    $scope.selectedPermission = '';
    $scope.allowedPermissions = [];

    $scope.getPermissionName = function (id){
        var input = $scope.permissions;
        var i=0, len=input.length;
        for (; i<len; i++) {
            if (+input[i].id == +id) {
                return input[i].name;
            }
        }
        return 'Error';
    };

    //Role permissions CRUD: Read
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
                $scope.updateCurrentEditedRolePermissions();
                $scope.updateAllowedRolePermissions();
            })
            .error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    $scope.updateAllowedRolePermissions = function () {
        $scope.allowedPermissions = [];

        $rootScope.permissions.forEach(function (item, i, arr) {
            var found = false;
            $scope.currentEditedRolePermissions.forEach(function(permission, i, arr){
                if (item.id == permission.permission_id) found = true;
            });
            if (!found && $scope.allowedPermissions.indexOf(item) == -1) $scope.allowedPermissions.push(item);
        });
    };
    $scope.updateCurrentEditedRolePermissions = function () {
        $scope.currentEditedRolePermissions = [];
        $rootScope.rolePermissions.forEach(function (item, i, arr) {
            if (item.role_id == $scope.currentEditedRole.id) $scope.currentEditedRolePermissions.push(item);
        });
    };
    //Role permissions CRUD: Delete
    $scope.deleteRolePermission = function (permission) {
        var id = permission.id;
        $http({method: 'DELETE', headers: {"Content-Type": "application/json"}, url: '/appmain/rest/rolepermissions/', data: {id: id}}).success(function (data, status, headers, config) {
            $scope.currentEditedRolePermissions.splice($scope.currentEditedRolePermissions.indexOf(permission), 1);
            $rootScope.rolePermissions.splice($scope.rolePermissions.indexOf(permission), 1);
            $scope.updateAllowedRolePermissions();
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };
    //Role permissions CRUD: Create
    $scope.addRolePermission = function () {
        var permission_id = $scope.selectedPermission;
        var role_id = $scope.currentEditedRole.id;
        var temp = {role_id: role_id,
            permission_id: permission_id};
        $http({method: 'POST', url: '/appmain/rest/rolepermissions/', headers: {"Content-Type": "application/json"}, data: angular.toJson(temp)}).success(function (data, status, headers, config) {
            $scope.updateRolePermissions();
        }).
            error(function (data, status, headers, config) {
                console.log(status);
            });
    };

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

    $scope.updateRolePermissions();
}]);

mainModule.controller('modalGroupController', ['$scope', '$rootScope', '$http', function ($scope, $rootScope, $http) {
    $scope.animationsEnabled = true;

    $scope.newGroupName = '';
    $scope.parentId = '';
    $scope.noParentId = null;
    $scope.getParentName = function(id) {
        var input = $scope.groups;
        if (id == null || id == undefined || id == "") return 'None';
        var i=0, len=input.length;
        for (; i<len; i++) {
            if (+input[i].id == +id) {
                return input[i].name;
            }
        }
        return null;
    };
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

mainModule.controller('modalPermissionsController', ['$scope', '$rootScope', '$http', function ($scope, $rootScope, $http) {
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
