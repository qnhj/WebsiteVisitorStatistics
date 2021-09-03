<%--
  Created by IntelliJ IDEA.
  User: An
  Date: 2021/9/3
  Time: 10:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showOne</title>
</head>
<body>

<table border="1px solid #ccc" cellspacing="0" cellpadding="0">
    <tr>
        <td>uuid</td>
        <td>${msg.uuid}</td>
    </tr>
    <tr>
        <td>添加时间</td>
        <td>${msg.time}</td>
    </tr>
    <tr>
        <td>使用协议</td>
        <td>${msg.protocol}</td>
    </tr>
    <tr>
        <td>客户端IP</td>
        <td>${msg.remoteAddr}</td>
    </tr>
    <tr>
        <td>客户端端口</td>
        <td>${msg.remotePort}</td>
    </tr>
    <tr>
        <td>请求方式</td>
        <td>${msg.method}</td>
    </tr>
    <tr>
        <td>用户语言环境</td>
        <td>${msg.locale}</td>
    </tr>
    <tr>
        <td>远程用户</td>
        <td>${msg.remoteUser}</td>
    </tr>
    <tr>
        <td>查询字符串</td>
        <td>${msg.queryString}</td>
    </tr>
    <tr>
        <td>用户操作系统家族</td>
        <td>${msg.osFamily}</td>
    </tr>
    <tr>
        <td>用户操作系统信息</td>
        <td>${msg.osName}</td>
    </tr>
    <tr>
        <td>用户浏览器信息</td>
        <td>${msg.uaName}</td>
    </tr>
    <tr>
        <td>浏览器品牌</td>
        <td>${msg.uaFamily}</td>
    </tr>
    <tr>
        <td>用户浏览器版本</td>
        <td>${msg.browserVersion}</td>
    </tr>
    <tr>
        <td>用户设备类型</td>
        <td>${msg.deviceType}</td>
    </tr>
    <tr>
        <td>类型</td>
        <td>${msg.type}</td>
    </tr>
    <tr>
        <td>用户名</td>
        <td>${msg.admin}</td>
    </tr>
    <tr>
        <td>记录位置</td>
        <td>${msg.url}</td>
    </tr>
    <tr>
        <td>备注</td>
        <td>${msg.remarks}</td>
    </tr>
    <tr>
        <td>标星标记</td>
        <td>${msg.mark}<a href="mark?id=${msg.mark}&uuid=${msg.uuid}">变更标记</a></td>
    </tr>
    <tr>
        <td>标星备注</td>
        <td>(取消标记会删除标星备注)<br>${msg.markRemarks}</td>
    </tr>
    <tr>
        <form action="upDataOne" method="get">
            <td><input type="text" name="remarks" value="${msg.remarks}"></td>
            <td>
                <input type="hidden" value="${msg.uuid}" name="uuid">
                <input type="submit" value="添加备注">
            </td>
        </form>

    </tr>
    <c:if test="${msg.mark}">
        <tr>
            <form action="upDataOne" method="get">
                <td><input type="text" name="markRemarks" value="${msg.markRemarks}"></td>
                <td>
                    <input type="hidden" value="${msg.uuid}" name="uuid">
                    <input type="submit" value="添加标星备注">
                </td>
            </form>
        </tr>
    </c:if>
</table>


</body>
</html>
