<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询用户信息</title>
</head>
<body>
<h2>用户信息</h2>

<form action="${pageContext.request.contextPath}/readUserInfoById" method="post">
    userId:<input type="text" name="userId"><p>
    <input type="submit" value="查询">
</form>

<table width="100%" border=1>
    <tr>
        <td>用户ID</td>
        <td>姓名</td>
        <td>年龄</td>
        <td>性别</td>
        <td>邮箱</td>
        <td>注册时间</td>
        <td>最后修改时间</td>
    </tr>
    <c:forEach items="${userInfoList}" var="userInfo">
        <tr>
            <td>${userInfo.userId }</td>
            <td>${userInfo.name }</td>
            <td>${userInfo.age }</td>
            <td>${userInfo.gender }</td>
            <td>${userInfo.email }</td>
            <td><fmt:formatDate value="${userInfo.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            <td><fmt:formatDate value="${userInfo.lastUpdateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
