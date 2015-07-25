<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
<form name="deleteform" id="deleteform" action="delete-user">
  <input type="text" name="id" placeholder="User id">
  <input type="text" name="name" placeholder="User name">
  <button form="deleteform" type="submit">Delete</button>
</form>
</body>
</html>
