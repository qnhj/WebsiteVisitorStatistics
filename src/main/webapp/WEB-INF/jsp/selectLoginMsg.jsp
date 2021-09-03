<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: An
  Date: 2021/9/3
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>selectLoginMsg</title>
</head>
<body>


<table border="1px solid #ccc" cellspacing="0" cellpadding="0">
    <tr>
        <td>序号</td>
        <td>登录时间</td>
        <td>客户端IP</td>
        <td>备注</td>
        <td>详情</td>
        <td>地理位置</td>
        <td>定位历史</td>
    </tr>

    <% int num = 1; %>

    <c:forEach var="loginmsg" items="${userLogins}">
        <tr>
            <td><%=num++ %>
            </td>
            <td>${loginmsg.loginTime}</td>
            <td>${loginmsg.loginIp}</td>
            <td>${loginmsg.loginRemarks}</td>
            <td><a href="<%=request.getContextPath()%>/msg/select/one?uuid=${loginmsg.msgUuid}">详细</a></td>
            <td><a href="https://www.ip138.com/iplookup.asp?ip=${loginmsg.loginIp}&action=2" target="_blank">定位</a></td>
            <td><a href="https://dingweilishi.com/${loginmsg.loginIp}" target="_blank">查看</a></td>
        </tr>
    </c:forEach>

</table>

<br>
第${loginPage}/${loginPages}页,共${loginAllNum}条记录
<%
    int nowPage = Integer.parseInt(request.getAttribute("loginPage").toString());
    int nowPages = Integer.parseInt(request.getAttribute("loginPages").toString());
    if (nowPage != 1){
%>

<a href="me?loginPage=${loginPage-1}">上一页</a>

<% }
    if (nowPages != nowPage){
%>
<a href="me?loginPage=${loginPage+1}">下一页</a>

<% } %>

<br>
每页显示行数：<a href="me?loginQuantity=10">10</a>
<a href="me?loginQuantity=20">20</a>
<a href="me?loginQuantity=30">30</a>行

</body>
</html>
