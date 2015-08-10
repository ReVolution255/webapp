<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Users</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script charset="utf-8" src="<c:out value="${pageContext.request.contextPath}" />/scripts/angular.min.js"></script>
    <script src="https://code.angularjs.org/1.4.3/angular-route.js"></script>
    <script src="https://code.angularjs.org/1.4.3/angular-resource.js"></script>
    <script src="<c:out value="${pageContext.request.contextPath}" />/scripts/ui-bootstrap-tpls-0.13.2.min.js"></script>
    <script charset="utf-8" src="<c:out value="${pageContext.request.contextPath}" />/scripts/common-ng.js"></script>
    <link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/css/style.css">
</head>
<body>
    <div ng-app="userstable">
        <div class="entity_table" ng-controller="usersTableController">
            <div id="manage_panel">
                <div id="buttons">
                    <div id="add_form" collapse="add_form_collapse">
                        <input id="add_field" type="text" ng-model="newUserName" value="{{newUserName}}" placeholder="User name">
                    </div>
                    <input id="add_button" type="button" value="Add user" ng-click="create()">
                    <div id="edit_form" collapse="edit_form_collapse">
                        <label for="idfield_editform">User id:</label>
                        <input type="text" id="idfield_editform" ng-model="currentEditedUser.id" name="id" placeholder="User id" value="{{currentEditedUser.id}}" readonly>
                        <br>
                        <label for="namefield_editform">User name:</label>
                        <input type="text" id="namefield_editform" name="name" ng-model="currentEditedUser.name" value="{{currentEditedUser.name}}" placeholder="New name">
                        <br>
                        <input ng-click="edit(currentEditedUser)" type="button" value="Accept">
                    </div>
                </div>
            </div>
            <table id="users" border="1">
                <thead>
                <tr>
                    <th>User ID</th>
                    <th>User Name</th>
                    <th>Edit User</th>
                    <th>Delete User</th>
                </tr>
                </thead>
                <tbody id="users_list">
                    <tr class="entity" ng-animate="animate" ng-repeat="user in users track by user.id">
                        <td ng-click="updateEditPanel(user)">
                            <div data-container="body" popover-trigger="mouseenter" popover-placement="right" popover="User id: {{user.id}} User name: {{user.name}}">
                            {{user.id}}
                            </div>
                        </td>
                        <td ng-click="updateEditPanel(user)">
                            <div data-container="body" popover-trigger="mouseenter" popover-placement="right" popover="User id: {{user.id}} User name: {{user.name}}">
                                {{user.name}}
                            </div>
                        </td>
                        <td><input type="button" value="Edit" name="{{user.id}}" ng-click="edit(user)"/></td>
                        <td><input type="button" value="Delete" name="{{user.id}}" ng-click="delete($event)"/></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>