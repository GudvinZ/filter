<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.09.2019
  Time: 3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>noAccess</title>
</head>
<body>
<c:out value="you have no access"/>
<a href="<c:url value="/"/>">Login</a>
</body>
</html>
