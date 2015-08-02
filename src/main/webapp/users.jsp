<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Users</title>
</head>
<script charset="utf-8" src="<c:out value="${pageContext.request.contextPath}" />/scripts/common.js"></script>
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/css/style.css">
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<body onload="init()">
<div id="manage_panel">
    <div id="buttons">
        <input id="add_button" type="button" value="Add user" onclick="showAddForm()">
    </div>
    <div id="add_form">
        <label for="namefield_createform">User name:</label>
        <input type="text" id="namefield_createform" name="name" placeholder="User name">
        <input value="Create" onclick="createUser()" type="button">
    </div>
    <div id="edit_form">
        <label for="idfield_editform">User id:</label>
        <input type="text" id="idfield_editform" name="id" placeholder="User id" value="" readonly>
        <br>
        <label for="namefield_editform">User name:</label>
        <input type="text" id="namefield_editform" name="name" placeholder="New name">
        <br>
        <input onclick="editUser()" type="button" value="Accept">
    </div>
</div>
    <div id="users_table">
        <table id="users" border="1">
            <thead>
            <tr>
                <th>User ID</th>
                <th>User Name</th>
                <th>Edit User</th>
                <th>Delete User</th>
            </tr>
            </thead>
            <tbody id="users_list"></tbody>
        </table>
    </div>
    <div id="no_users"></div>
</body>
</html>
