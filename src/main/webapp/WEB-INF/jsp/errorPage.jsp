<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询用户信息</title>
</head>
<body>
<h2>查询用户信息</h2>

<form action="${pageContext.request.contextPath}/readUserInfoById" method="get">
    userId:<input type="text" name="userId">
    <p>
        <input type="submit" value="查询">
</form>

<p>无此用户，请重新输入！</p>

</body>
</html>
