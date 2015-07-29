<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
</head>
<script src="<c:out value="${pageContext.request.contextPath}" />/scripts/common.js"></script>
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<body onload="init()">
    <label for="namefield_createform">User name:</label>
    <input type="text" id="namefield_createform" name="name" placeholder="User name">
    <input value="Create" onclick="createUser()" type="button">
<br>
        <c:choose>
            <c:when test="${usersList.size() > 0}">
                <div id="buttons">
                    <label for="idfield_deleteform">User id:</label>
                    <input type="text" id="idfield_deleteform" name="delete" placeholder="User id">
                    <input onclick="deleteUser(-1)" type="button" value="Delete">
                <br>
                    <div id="edit_form">
                    </div>
                </div>
                <div id="users_table">
                <table id="users" border=1>
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
                <div id="buttons"></div>
                <div id="users_table"></div>
                <div id="no_users">No users in database.</div>
            </c:otherwise>
        </c:choose>
</body>
</html>
