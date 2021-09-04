<%@ page import="xyz.baochao.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: An
  Date: 2021/9/3
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserSettings</title>
</head>
<body>

<% User user = (User) session.getAttribute("user"); %>

<br>
<hr>

<%--  修改用户名  --%>
新用户名：<input id="newUserName" type="text" value="<%=user.getUserName()%>">
<input id="setName" type="button" value="修改用户名"><br>
<div id="setNameValue"></div>

<hr>

<%--  修改密码  --%>
当前密码：<input id="userPw" type="text"><br>
新密码：<input id="newUserPw" type="text">
<input id="setPw" type="button" value="确定修改"><br>
<div id="setPwValue"></div>

<br>
<hr>

<%--  管理员显示解封账户和新建账户  --%>
<% if (user.getAdmin() != 0) { //判断是否是管理员  %>
<a href="<%=request.getContextPath()%>/user/admin">管理员解封</a>
<% } %>

<script type="text/javascript">

    window.onload = function () { //当页面加载完成之后


        //当用户点击修改用户名之后
        document.getElementById("setName").onclick = function () {
            // 获取用户输入的值
            var userName = document.getElementById("newUserName").value;

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
                            document.getElementById("setNameValue").innerHTML = "<p style='color: green;'>修改成功</p>";
                        } else {
                            document.getElementById("setNameValue").innerHTML = "<p style='color: red;'>修改失败</p>";
                        }
                    } else {
                        document.getElementById("setNameValue").innerHTML = "<p style='color: red;'>错误代码：" + xmlhttp.status + "</p>";
                    }
                } else {
                    document.getElementById("setNameValue").innerHTML = "<p style='color: red;'>正在更改……</p>";
                }
            }
            xmlhttp.open("POST", "<%=request.getContextPath()%>/user/upData/userName", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("newUserName=" + userName);
        }

        //当用户点击修改密码之后
        document.getElementById("setPw").onclick = function () {
            // 获取用户输入的值
            var userPw = document.getElementById("userPw").value;
            var newUserPw = document.getElementById("newUserPw").value;

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
                            document.getElementById("setPwValue").innerHTML = "<p style='color: green;'>修改成功</p>";
                        } else {
                            document.getElementById("setPwValue").innerHTML = "<p style='color: red;'>修改失败</p>";
                        }
                    } else {
                        document.getElementById("setPwValue").innerHTML = "<p style='color: red;'>错误代码：" + xmlhttp.status + "</p>";
                    }
                } else {
                    document.getElementById("setPwValue").innerHTML = "<p style='color: red;'>正在更改……</p>";
                }
            }
            xmlhttp.open("POST", "<%=request.getContextPath()%>/user/upData/userPw", true);
            xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            xmlhttp.send("newUserPw=" + newUserPw + "&userPw=" +userPw);
        }

    }
</script>


</body>
</html>
