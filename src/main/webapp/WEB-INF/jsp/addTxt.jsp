<%@ page import="xyz.baochao.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: An
  Date: 2021/9/3
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addTxt</title>
</head>
<body>

使用说明：<br>
在下放输入框输入一个标记，点击生成获取html代码，放入需要统计浏览量的页面，在查询页面就能找到对应记录！<br>

输入你的页面标记<input id="url" type="text">
<input type="button" value="生成" onclick="todo();"/>

<br>
<span id="dos"></span>


<script type="text/javascript">
    <%
     User user = (User) request.getSession().getAttribute("user");
     String url = request.getRequestURL().toString();
     int i = url.indexOf(request.getContextPath());
     String substring = url.substring(0,i) + request.getContextPath();
     %>
    var str = '&lt;div style="width: 0;height: 0;"&gt;&lt;iframe src="<%=substring%>/msg/add?who=<%=user.getUserName()%>&url=';
    var str2 = '" frameborder="0" style="width: 0; height: 0;"&gt;&lt;/iframe&gt;&lt;/div&gt;';

    function todo() {
        var urls = document.getElementById("url").value;
        st = str + urls + str2;
        document.getElementById("dos").innerHTML = st;
    }
</script>

</body>
</html>
