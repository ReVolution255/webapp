<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="http-equiv" content="Content-type: text/html; charset=UTF-8">
    <title>Users</title>
</head>
<script charset="utf-8" src="<c:out value="${pageContext.request.contextPath}" />/scripts/common.js"></script>
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/css/style.css">
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<body onload="init()">

        <c:choose>
            <c:when test="${usersList.size() > 0}">
                <div id="buttons">
                    <div id="add_form">
                    <input id="add_button" type="button" value="Add user" onclick="showAddForm()">
                    </div>
                    <div id="edit_form">
                    </div>
                </div>
                <div id="users_table">
                <table id="users" border="1">
                    <tr>
                        <th>User ID</th>
                        <th>User Name</th>
                        <th>Edit User</th>
                        <th>Delete User</th>
                    </tr>
            <c:forEach var="user" items="${usersList}">
                <tr>
                    <td>
                        <a href="about-user?id=<c:out value="${user.id}" />"><c:out value="${user.id}" /></a>
                    </td>
                    <td class="username" id="<c:out value="${user.id}" />">
                        <a href="about-user?id=<c:out value="${user.id}" />"><c:out value="${user.name}" /></a>
                    </td>
                    <td>
                        <input name="edit" type="button" value="Edit" onclick="showEditForm(${user.id})" />
                    </td>
                    <td>
                        <input name="delete" type="button" value="Delete" onclick="deleteUser(${user.id})" />
                    </td>
                </tr>
            </c:forEach>
                </table>
                </div>
                <div id="no_users"></div>
            </c:when>
            <c:otherwise>
                <div id="buttons">
                    <div id="add_form">
                        <input type="button" value="Add user" onclick="showAddForm()">
                    </div>
                    <div id="edit_form">
                    </div>
                </div>
                <div id="users_table"></div>
                <div id="no_users">No users in database.</div>
            </c:otherwise>
        </c:choose>
</body>
</html>
