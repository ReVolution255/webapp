<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About User</title>
</head>
<body>
<table border="1">
    <tr>
        <td>User id</td>
        <td>User name</td>
    </tr>
    <tr>
        <td><c:out value="${user.id}" /></td>
        <td><c:out value="${user.name}" /></td>
    </tr>
</table>
</body>
</html>
