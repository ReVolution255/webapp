<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<form name="createform" id="createform" action="add-user">
    <label for="namefield">User name:</label>
  <input type="text" id="namefield" name="name" placeholder="User name">
  <button form="createform" type="submit">Create</button>
</form>
</body>
</html>
