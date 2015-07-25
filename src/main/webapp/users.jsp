<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
</head>
<body>
<form action="add-user.jsp">
    <button>
        Add user
    </button>
</form>
<table border=1>
    <c:forEach var="user" items="${usersList}">
        <tr>
            <td><a href="about-user?id=<c:out value="${user.id}" />"><c:out value="${user.id}" /></a></td>
            <td><a href="about-user?id=<c:out value="${user.id}" />"><c:out value="${user.name}" /></a></td>
            <td><form action="edit-user.jsp">
                <button name="edit" type="submit" value="<c:out value="${user.id}" />">Edit</button>
            </form></td>
            <td><form action="delete-user">
                <button name="delete" type="submit" value="<c:out value="${user.id}" />">Delete</button>
            </form></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
