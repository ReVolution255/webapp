<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Users</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/css/style.css">
    <script charset="utf-8" src="<c:out value="${pageContext.request.contextPath}" />/scripts/angular.min.js"></script>
    <script src="https://code.angularjs.org/1.4.3/angular-route.js"></script>
    <script src="https://code.angularjs.org/1.4.3/angular-resource.js"></script>
    <script src="<c:out value="${pageContext.request.contextPath}" />/scripts/ui-bootstrap-tpls-0.13.2.min.js"></script>
    <script src="https://code.angularjs.org/1.4.3/angular-animate.min.js"></script>
    <script charset="utf-8" src="<c:out value="${pageContext.request.contextPath}" />/scripts/common-ng.js"></script>
    <script charset="utf-8" src="<c:out value="${pageContext.request.contextPath}" />/scripts/modal-controllers.js"></script>
</head>
<body>
<div class="container" ng-app="main">
    <script type="text/ng-template" id="addUserModal">
        <div class="modal-header">
            <h3 class="modal-title">Add new user</h3>
        </div>
        <div class="modal-body">
            <p>User id is not allowed here. Enter new username:</p>
            <input type="text" placeholder="New username" ng-model="newUserName" value="{{newUserName}}" />
            <p>User roles:
                <span class="glyphicon glyphicon-plus" data-container="body" popover-trigger="mouseenter" popover-placement="top" popover="Create new" ng-click="addUserRole()" aria-hidden="true"></span>
                <select class="allowed-roles-list" ng-model="selectedRole">
                    <option ng-repeat="role in allowedRoles track by role.id" value="{{role.id}}">{{role.name}}</option>
                </select>
            </p>
            <ul class="nav nav-pills nav-stacked list-group">
                <li class="animation list-group-item" ng-repeat="role in currentEditedUserRoles track by role.id">{{getRoleName(role.role_id)}}
                    <div class="inline-icon">
                        <span class="glyphicon glyphicon-remove" ng-click="deleteUserRole(role)" aria-hidden="true"></span>
                    </div>
                </li>
            </ul>

            <p>User groups:
                <span class="glyphicon glyphicon-plus" data-container="body" popover-trigger="mouseenter" popover-placement="top" popover="Create new" ng-click="addUserGroup()" aria-hidden="true"></span>
                <select class="allowed-roles-list" ng-model="selectedGroup">
                    <option ng-repeat="group in allowedGroups track by group.id" value="{{group.id}}">{{group.name}}</option>
                </select>
            </p>
            <ul class="nav nav-pills nav-stacked list-group">
                <li class="animation list-group-item" ng-repeat="group in currentEditedUserGroups track by group.id">{{getGroupName(group.group_id)}}
                    <div class="inline-icon">
                        <span class="glyphicon glyphicon-remove" ng-click="deleteUserGroup(group)" aria-hidden="true"></span>
                    </div>
                </li>
            </ul>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="create()">Create</button>
        </div>
    </script>
    <script type="text/ng-template" id="editUserModal">
        <div class="modal-header">
            <h3 class="modal-title">Edit user</h3>
        </div>
        <div class="modal-body">
            <p>User id is not allowed here. Enter new username:</p>
            <input type="text" placeholder="Current id" ng-model="currentEditedUser.id" value="{{currentEditedUser.id}}" readonly/>
            <input type="text" placeholder="New username" ng-model="currentEditedUser.name" value="{{currentEditedUser.name}}" />
            <p>User roles:
                <span class="glyphicon glyphicon-plus" data-container="body" popover-trigger="mouseenter" popover-placement="top" popover="Create new" ng-click="addUserRole()" aria-hidden="true"></span>
                <select class="allowed-roles-list" ng-model="selectedRole">
                    <option ng-repeat="role in allowedRoles track by role.id" value="{{role.id}}">{{role.name}}</option>
                </select>
            </p>
            <ul class="nav nav-pills nav-stacked list-group">
                <li class="animation list-group-item" ng-repeat="role in currentEditedUserRoles track by role.id">{{getRoleName(role.role_id)}}
                    <div class="inline-icon">
                        <span class="glyphicon glyphicon-remove" ng-click="deleteUserRole(role)" aria-hidden="true"></span>
                    </div>
                </li>
            </ul>

            <p>User groups:
                <span class="glyphicon glyphicon-plus" data-container="body" popover-trigger="mouseenter" popover-placement="top" popover="Create new" ng-click="addUserGroup()" aria-hidden="true"></span>
                <select class="allowed-roles-list" ng-model="selectedGroup">
                    <option ng-repeat="group in allowedGroups track by group.id" value="{{group.id}}">{{group.name}}</option>
                </select>
            </p>
            <ul class="nav nav-pills nav-stacked list-group">
                <li class="animation list-group-item" ng-repeat="group in currentEditedUserGroups track by group.id">{{getGroupName(group.group_id)}}
                    <div class="inline-icon">
                        <span class="glyphicon glyphicon-remove" ng-click="deleteUserGroup(group)" aria-hidden="true"></span>
                    </div>
                </li>
            </ul>
            <p>Note: The name will be saved only if you click "Save".</p>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="edit(currentEditedUser)">Save</button>
        </div>
    </script>
    <script type="text/ng-template" id="addRoleModal">
        <div class="modal-header">
            <h3 class="modal-title">Add new role</h3>
        </div>
        <div class="modal-body">
            <p>Role id is not allowed here. Enter new role name:</p>
            <input type="text" placeholder="New role name" ng-model="newRoleName" value="{{newRoleName}}" />
            <p>Role permissions:
                <span class="glyphicon glyphicon-plus" data-container="body" popover-trigger="mouseenter" popover-placement="top" popover="Create new" ng-click="addRolePermission()" aria-hidden="true"></span>
                <select class="allowed-roles-list" ng-model="selectedPermission">
                    <option ng-repeat="permission in allowedPermissions track by permission.id" value="{{permission.id}}">{{permission.name}}</option>
                </select>
            </p>
            <ul class="nav nav-pills nav-stacked list-group">
                <li class="animation list-group-item" ng-repeat="permission in currentEditedRolePermissions track by permission.id">{{getPermissionName(permission.permission_id)}}
                    <div class="inline-icon">
                        <span class="glyphicon glyphicon-remove" ng-click="deleteRolePermission(permission)" aria-hidden="true"></span>
                    </div>
                </li>
            </ul>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="create()">Create</button>
        </div>
    </script>
    <script type="text/ng-template" id="editRoleModal">
        <div class="modal-header">
            <h3 class="modal-title">Edit role</h3>
        </div>
        <div class="modal-body">
            <p>Role id is not allowed here. Enter new role name:</p>
            <input type="text" placeholder="Current id" ng-model="currentEditedRole.id" value="{{currentEditedRole.id}}" readonly/>
            <input type="text" placeholder="New role name" ng-model="currentEditedRole.name" value="{{currentEditedRole.name}}" />
            <p>Role permissions:
                <span class="glyphicon glyphicon-plus" data-container="body" popover-trigger="mouseenter" popover-placement="top" popover="Create new" ng-click="addRolePermission()" aria-hidden="true"></span>
                <select class="allowed-roles-list" ng-model="selectedPermission">
                    <option ng-repeat="permission in allowedPermissions track by permission.id" value="{{permission.id}}">{{permission.name}}</option>
                </select>
            </p>
            <ul class="nav nav-pills nav-stacked list-group">
                <li class="animation list-group-item" ng-repeat="permission in currentEditedRolePermissions track by permission.id">{{getPermissionName(permission.permission_id)}}
                    <div class="inline-icon">
                        <span class="glyphicon glyphicon-remove" ng-click="deleteRolePermission(permission)" aria-hidden="true"></span>
                    </div>
                </li>
            </ul>
            <p>Note: The name will be saved only if you click "Save".</p>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="edit(currentEditedRole)">Save</button>
        </div>
    </script>
    <script type="text/ng-template" id="addGroupModal">
        <div class="modal-header">
            <h3 class="modal-title">Add new group</h3>
        </div>
        <div class="modal-body">
            <p>Group id is not allowed here. Enter new group name:</p>
            <input type="text" placeholder="New group name" ng-model="newGroupName" value="{{newGroupName}}" />
            <p>Select parent group:</p>
            <select ng-model="parentId">
                <option value="{{noParentId}}">No parent</option>
                <option ng-repeat="group in groups" value="{{group.id}}">{{group.name}}</option>
            </select>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="create()">Create</button>
        </div>
    </script>
    <script type="text/ng-template" id="editGroupModal">
        <div class="modal-header">
            <h3 class="modal-title">Edit group</h3>
        </div>
        <div class="modal-body">
            <p>Group id is not allowed here. Enter new group name:</p>
            <input type="text" placeholder="Current id" ng-model="currentEditedGroup.id" value="{{currentEditedGroup.id}}" readonly/>
            <input type="text" placeholder="New group name" ng-model="currentEditedGroup.name" value="{{currentEditedGroup.name}}" />
            <p>Current parent group: {{getParentName(currentEditedGroup.parent_id)}}</p>
            <p>Select other parent group:</p>
            <select ng-model="currentEditedGroup.parent_id">
                <option value="{{noParentId}}">No parent</option>
                <option ng-repeat="group in groups" ng-if="group.id != currentEditedGroup.id" value="{{group.id}}">{{group.name}}</option>
            </select>
            <br>
            <p>Note: The name and parent group will be saved only if you click "Save".</p>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="edit(currentEditedGroup)">Save</button>
        </div>
    </script>
    <script type="text/ng-template" id="addPermissionModal">
        <div class="modal-header">
            <h3 class="modal-title">Add new permission</h3>
        </div>
        <div class="modal-body">
            <p>Permission id is not allowed here. Enter new permission name:</p>
            <input type="text" placeholder="New permission name" ng-model="newPermissionName" value="{{newPermissionName}}" />
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="create()">Create</button>
        </div>
    </script>
    <script type="text/ng-template" id="editPermissionModal">
        <div class="modal-header">
            <h3 class="modal-title">Edit permission</h3>
        </div>
        <div class="modal-body">
            <p>Permission id is not allowed here. Enter new permission name:</p>
            <input type="text" placeholder="Current id" ng-model="currentEditedPermission.id" value="{{currentEditedPermission.id}}" readonly/>
            <input type="text" placeholder="New permission name" ng-model="currentEditedPermission.name" value="{{currentEditedPermission.name}}" />
            <p>Note: The name will be saved only if you click "Save".</p>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="edit(currentEditedGroup)">Save</button>
        </div>
    </script>
    <script type="text/ng-template" id="main">
        <div class="row">
            <div class="col-md-4 panel well" ng-controller="usersListController">
                <div style="display:none"><div ng-repeat="i in userRoles track by i.id"></div></div>
                <p>Users with groups and roles
                    <span class="glyphicon glyphicon-plus" data-container="body" popover-trigger="mouseenter" popover-placement="left" popover="Create new" ng-click="open()" aria-hidden="true"></span>
                </p>
                <form class="form-search form-search-sm">
                    <div class="input-append">
                        <input type="text" ng-model="search" class="search-query" placeholder="Search...">
                    </div>
                    <ul class="nav nav-pills nav-stacked list-group">
                        <li class="animation list-group-item" ng-repeat="user in users | filter:search">
                            <div data-container="body" popover-trigger="mouseenter" popover-placement="top" popover="User id: {{user.id}} User name: {{user.name}}">
                                <span class="badge">{{user.id}}</span> <a href="#/user/{{user.id}}">{{user.name}}</a>
                                <div class="inline-icon">
                                    <span class="glyphicon glyphicon-remove" ng-click="delete(user)" aria-hidden="true"></span>
                                </div>
                                <div class="inline-icon">
                                    <span class="glyphicon glyphicon-pencil" ng-click="editModal(user)" aria-hidden="true"></span>
                                </div>
                            </div>
                        </li>
                    </ul>
                </form>
            </div>
            <div class="col-md-4 panel well" ng-controller="rolesListController">
                <p>Roles with permissions
                    <span class="glyphicon glyphicon-plus" data-container="body" popover-trigger="mouseenter" popover-placement="left" popover="Create new" ng-click="addRole()" aria-hidden="true"></span>
                </p>
                <form class="form-search form-search-sm">
                    <div class="input-append">
                        <input type="text" ng-model="search" class="search-query" placeholder="Search...">
                    </div>
                    <ul class="nav nav-pills nav-stacked list-group">
                        <li class="animation list-group-item" ng-repeat="role in roles | filter:search">
                            <div data-container="body" popover-trigger="mouseenter" popover-placement="top" popover="Role id: {{role.id}} Role name: {{role.name}}">
                                <span class="badge">{{role.id}}</span> {{role.name}}
                                <div class="inline-icon">
                                    <span class="glyphicon glyphicon-remove" ng-click="delete(role)" aria-hidden="true"></span>
                                </div>
                                <div class="inline-icon">
                                    <span class="glyphicon glyphicon-pencil" ng-click="editRole(role)" aria-hidden="true"></span>
                                </div>
                            </div>
                        </li>
                    </ul>
                </form>
                <div ng-controller="permissionsListController">
                    <p>Permissions
                        <span class="glyphicon glyphicon-plus" data-container="body" popover-trigger="mouseenter" popover-placement="left" popover="Create new" ng-click="addPermission()" aria-hidden="true"></span>
                    </p>
                    <form class="form-search form-search-sm">
                        <div class="input-append">
                            <input type="text" ng-model="search" class="search-query" placeholder="Search...">
                        </div>
                        <ul class="nav nav-pills nav-stacked list-group">
                            <li class="animation list-group-item" ng-repeat="permission in permissions | filter:search">
                                <div data-container="body" popover-trigger="mouseenter" popover-placement="top" popover="Permission id: {{permission.id}} Permission name: {{permission.name}}">
                                    <span class="badge">{{permission.id}}</span> {{permission.name}}
                                    <div class="inline-icon">
                                        <span class="glyphicon glyphicon-remove" ng-click="delete(permission)" aria-hidden="true"></span>
                                    </div>
                                    <div class="inline-icon">
                                        <span class="glyphicon glyphicon-pencil" ng-click="editPermission(permission)" aria-hidden="true"></span>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </form>
                </div>
            </div>
            <div class="col-md-4 panel well" ng-controller="groupsListController">
                <p>Groups list
                    <span class="glyphicon glyphicon-plus" data-container="body" popover-trigger="mouseenter" popover-placement="left" popover="Create new" ng-click="addGroup()" aria-hidden="true"></span>
                </p>
                <form class="form-search form-search-sm">
                    <div class="input-append">
                        <input type="text" ng-model="search" class="search-query" placeholder="Search...">
                    </div>
                    <ul class="nav nav-pills nav-stacked list-group">
                        <li class="animation list-group-item" ng-repeat="group in groups | filter:search">
                            <div data-container="body" popover-trigger="mouseenter" popover-placement="top" popover="Group id: {{group.id}} Group name: {{group.name}}">
                                <span class="badge">{{group.id}}</span> <a href="#/group/{{group.id}}">{{group.name}}</a>
                                <div class="inline-icon">
                                    <span class="glyphicon glyphicon-remove" ng-click="delete(group)" aria-hidden="true"></span>
                                </div>
                                <div class="inline-icon">
                                    <span class="glyphicon glyphicon-pencil" ng-click="editGroup(group)" aria-hidden="true"></span>
                                </div>
                            </div>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </script>
    <script type="text/ng-template" id="userPermissions">
        <div>
            <p>Permissions. User id: {{userId}}</p>
            <ul class="nav nav-pills nav-stacked list-group">
                <li class="animation list-group-item" ng-repeat="permission in currentEditedUserPermissions | unique: 'id'">{{permission.name}}
                </li>
            </ul>
        </div>
    </script>
    <script type="text/ng-template" id="groupUsers">
        <div>
            <p>Users in group. Group id: {{groupId}}</p>
            <ul class="nav nav-pills nav-stacked list-group">
                <li class="animation list-group-item" ng-repeat="user in allUsers | unique: 'id'">{{user.name}}
                </li>
            </ul>
        </div>
    </script>
    <div class="page-header">
        <h1>Simple one-page web app with some functions <small>All functions are presented below</small></h1>
    </div>
    <div ng-view></div>
</div>
</body>
</html>