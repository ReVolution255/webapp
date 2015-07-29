<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About User</title>
    <meta name="http-equiv" content="Content-type: text/html; charset=UTF-8">
</head>
<link rel="stylesheet" href="<c:out value="${pageContext.request.contextPath}" />/css/style.css">
<body>
<table id="users" border="1">
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
