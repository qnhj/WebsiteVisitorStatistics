<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <td>
            <span>${msg.mark}</span>
            <input id="uuiduuid" value="${msg.uuid}" style="display:none" type="text">
            <input id="msgMark" value="${msg.mark}" style="display:none" type="text">
            <span><input type="button" value="变更标记" id="changeMsgMark" onclick="changeMark();"></span>
        </td>
    </tr>
    <tr>
        <td>标星备注</td>
        <td id="msgMarkRemarks">(取消标记会删除标星备注)<br>${msg.markRemarks}</td>
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

<div id="message"></div>

<br>
<input type="button" value="返回上一级" onclick="pageExit();">


<script type="text/javascript">

    //返回上一页
    function pageExit() {
        window.history.back();
    }

    //标星操作
    function changeMark() {

        var uuid = document.getElementById("uuiduuid").value;
        var msgMark = document.getElementById("msgMark").value;

        //验证用户名和密码
        var xmlhttp;
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        } else {// code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4) {
                if (xmlhttp.status == 200) {
                    var zhuangTai = xmlhttp.responseText;
                    if (zhuangTai == 'true') {
                        //刷新页面
                        window.location.reload();
                    } else {
                        document.getElementById("message").innerHTML = "<p style='color: red;'>修改标记失败</p>";
                    }
                } else {
                    document.getElementById("message").innerHTML = "<p style='color: red;'>错误代码：" + xmlhttp.status + "</p>";
                }
            } else {
                document.getElementById("message").innerHTML = "<p style='color: red;'>正在修改</p>";
            }
        }
        xmlhttp.open("POST", "<%=request.getContextPath() %>/msg/updata/changeMark", true);
        xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
        xmlhttp.send("msgUuid=" + uuid +"&msgMark=" + msgMark);
    }

</script>


</body>
</html>
