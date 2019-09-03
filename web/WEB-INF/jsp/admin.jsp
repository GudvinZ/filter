<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="service.UserService" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.08.2019
  Time: 3:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>
</head>
<body>
<c:if test="${users.size()<1}">
    <p>
        You have no users, add user?
    </p>
</c:if>
<jsp:include page="/WEB-INF/jsp/add.jsp"/>
<c:if test="${users.size()>0}">
    <p>
    <form action="<c:url value="/admin/delete"/>" method="get" style="display: inline">
        Users:
        <button type="submit">Delete all users</button>
    </form>
    </p>
    <jsp:include page="/WEB-INF/jsp/dynamicTableUsers.jsp"/>
</c:if>
</body>
</html>
