<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: An
  Date: 2021/9/4
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>解封</title>
</head>
<body>

<table border="1px solid #ccc" cellspacing="0" cellpadding="0">
    <tr>
        <td>序号</td>
        <td>用户名</td>
        <td>备注</td>
        <td>状态</td>
        <td>清零</td>
    </tr>

    <% int num = 1; %>

    <c:forEach var="login" items="${loginsUser}">
    <tr>
        <td><%=num++ %></td>
        <td>${login.userName}</td>
        <td>${login.userRemarks}</td>
        <td>${login.userIndex}</td>
        <td><a href="<%=request.getContextPath()%>/user/admin/do?uuid=${login.userUuid}">清零</a></td>
    </tr>
    </c:forEach>
    <td colspan="5">暂无其他被封禁用户</td>

</body>
</html>
