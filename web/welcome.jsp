<%@ page import="java.util.Date" %>
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
<h1 align="center" style="color: red">Welcome:</h1>
<%
    System.out.println(session.getAttribute("user"));
%>
<hr/>
<span style="align:center; color:yellow">
        Time:<%System.out.println(new Date());%>
    </span>

</body>
</html>
