<%--
  Created by IntelliJ IDEA.
  User: Глеб
  Date: 05.12.2022
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style><%@include file="../css/loginPageStyle.css"%></style>
</head>

<body>
<div id="login-form-wrap">
    <h2>Login</h2>
    <form id="login-form" method="post" action="${pageContext.request.contextPath}/">
        <p>
            <input type="text" required placeholder="Username" name="username">
        </p>
        <p>
            <input type="password" required placeholder="Password" name="password">
        </p>

        <div class='container'>
            <h6 style='color: red;'><%= request.getAttribute("wrongLoginData")%></h6>
        </div>

        <p>
            <input class="button" type="submit" value="Login" id="login">
        </p>
    </form>
    <div id="create-account-wrap">
        <p>Not a member? <a href="${pageContext.request.contextPath}/registration">Create Account</a></p><p>
    </p></div>
</div>
</body>
</html>
