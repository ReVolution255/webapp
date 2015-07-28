<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
</head>
<script src="<c:out value="${pageContext.request.contextPath}" />/scripts/common.js"></script>
<script src="<c:out value="${pageContext.request.contextPath}" />/scripts/jquery-2.1.4.min.js"></script>
<body onload="init()">
    <label for="namefield_createform">User name:</label>
    <input type="text" id="namefield_createform" name="name" placeholder="User name">
    <input value="Create" onclick="createUser()" type="button">
<br>
        <c:choose>
            <c:when test="${usersList.size() > 0}">
                    <label for="idfield_deleteform">User id:</label>
                    <input type="text" id="idfield_deleteform" name="delete" placeholder="User id">
                    <input onclick="deleteUser()" type="button" value="Delete">
                <br>
                    <label for="idfield_editform">User id:</label>
                    <input type="text" id="idfield_editform" name="id" placeholder="User id">
                    <label for="namefield_editform">User name:</label>
                    <input type="text" id="namefield_editform" name="name" placeholder="New name">
                    <input onclick="editUser()" type="button" value="Accept">
                <br>
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
                        <form action="edit-user.jsp">
                        <button name="edit" type="submit" value="<c:out value="${user.id}" />">Edit</button>
                        </form>
                    </td>
                    <td>
                        <form action="delete-user">
                        <button name="delete" type="submit" value="<c:out value="${user.id}" />">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
                </table>
            </c:when>
            <c:otherwise>
                <b>No users in database.</b>
            </c:otherwise>
        </c:choose>
</body>
</html>
