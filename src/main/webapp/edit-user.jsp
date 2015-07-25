<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<form name="editform" id="editform" action="edit-user">
  <input type="text" name="id" value="<%= request.getParameter("edit")%>" readonly>
  <input type="text" name="name" placeholder="New name">
  <button form="editform" type="submit">Accept</button>
</form>
</body>
</html>
