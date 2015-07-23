<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Users</title>
</head>
<body>
<table border=1>
    <c:forEach var="user" items="${usersList}">
        <tr>
            <td><c:out value="${user.id}" /></td>
            <td><c:out value="${user.name}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
