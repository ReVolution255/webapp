<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete User</title>
</head>
<body>
<form name="deleteform" id="deleteform" action="delete-user">
    <label for="idfield">User id:</label>
    <input type="text" id="idfield" name="delete" placeholder="User id">
  <button form="deleteform" type="submit">Delete</button>
</form>
</body>
</html>
