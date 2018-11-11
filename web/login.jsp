<%--
  Created by IntelliJ IDEA.
  User: asus
  Date: 2018/11/11
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 align="center" style="color: red;">欢迎您登录系统后台</h1><hr/>
<%--the form start--%>
<div align="center">
    <form method="post" action="/login">
        Username:<input type="text" name="username"/><br/><br/>
        Password:<input type="password" name="password"/><br/><br/>
        <input type="submit" value="登录"/>
    </form>
</div>

</body>
</html>
