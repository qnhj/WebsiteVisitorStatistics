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
    <title>selectMe</title>
</head>
<body>


<table border="1px solid #ccc" cellspacing="0" cellpadding="0">
    <tr>
        <td>序号</td>
        <td>添加时间</td>
        <td>客户端IP</td>
        <td>客户端端口</td>
        <td>用户操作系统信息</td>
        <td>浏览器品牌</td>
        <td>记录位置</td>
        <td>备注</td>
        <td>标星标记</td>
        <td>详情</td>
        <td>地理位置</td>
        <td>定位历史</td>
    </tr>

    <% int num = 1; %>

    <c:forEach var="msg" items="${msgs}">
        <tr>
            <td><%=num++ %>
            </td>
            <td>${msg.time}</td>
            <td>${msg.remoteAddr}</td>
            <td>${msg.remotePort}</td>
            <td>${msg.osFamily}<br>${msg.osName}</td>
            <td>${msg.uaFamily}</td>
            <td>${msg.url}</td>
            <td>${msg.remarks}</td>
            <td>${msg.mark}</td>
            <td><a href="one?uuid=${msg.uuid}">详细</a></td>
            <td><a href="https://www.ip138.com/iplookup.asp?ip=${msg.remoteAddr}&action=2" target="_blank">定位</a></td>
            <td><a href="https://dingweilishi.com/${msg.remoteAddr}" target="_blank">查看</a></td>
        </tr>
    </c:forEach>

</table>

<br>
第${page}/${pages}页,共${allNum}条记录
<%
    int nowPage = Integer.parseInt(request.getAttribute("page").toString());
    int nowPages = Integer.parseInt(request.getAttribute("pages").toString());
    if (nowPage != 1){
%>

<a href="me?pageNum=${page-1}">上一页</a>

<% }
    if (nowPages != nowPage){
%>
<a href="me?pageNum=${page+1}">下一页</a>

<% } %>

<br>
每页显示行数：<a href="me?quantity=10">10</a>
<a href="me?quantity=20">20</a>
<a href="me?quantity=30">30</a>行

</body>
</html>
