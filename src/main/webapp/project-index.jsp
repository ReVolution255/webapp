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
            <p>Note: The data will be saved only if you click "Save".</p>
        </div>
        <div class="modal-footer">
            <button class="btn btn-primary" ng-click="edit(currentEditedUser)">Save</button>
        </div>
    </script>
    <div class="page-header">
        <h1>Simple one-page web app with some functions <small>All functions presented below</small></h1>
    </div>
    <div class="row">
        <div class="col-md-4 panel well" ng-controller="usersListController" style="overflow: hidden;">
            <p>Users with groups and roles
                <span class="glyphicon glyphicon-plus" data-container="body" popover-trigger="mouseenter" popover-placement="left" popover="Create new" ng-click="open()" aria-hidden="true" style="display: inline;float: right;cursor:pointer;"></span>
            </p>
            <form class="form-search form-search-sm">
                <div class="input-append">
                    <input type="text" ng-model="search" class="search-query" style="width:100%;" placeholder="Search...">
                </div>
                <ul class="nav nav-pills nav-stacked list-group">
                    <li class="animation list-group-item" ng-repeat="user in users | filter:search">
                        <div data-container="body" popover-trigger="mouseenter" popover-placement="top" popover="User id: {{user.id}} User name: {{user.name}}">
                        <span class="badge">{{user.id}}</span> {{user.name}}
                            <div style="display: inline;">
                                <span class="glyphicon glyphicon-remove" ng-click="delete(user)" aria-hidden="true" style="float: right;"></span>
                            </div>
                            <div style="display: inline;">
                                <span class="glyphicon glyphicon-pencil" ng-click="editModal(user)" aria-hidden="true" style="float: right;margin-right: 20px;"></span>
                            </div>
                        </div>
                    </li>
                </ul>
            </form>
        </div>
        <div class="col-md-4 panel well"><p>Roles with permissions</p></div>
        <div class="col-md-4 panel well"><p>Groups list</p></div>
    </div>
</div>
</body>
</html>