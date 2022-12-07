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
    <title>Login</title>

</head>
<body>

<div class="form">

    <h1>Вход в систему</h1><br>
    <form method="post" action="${pageContext.request.contextPath}/">
        <input type="text" required placeholder="username" name="username"><br>
        <input type="password" required placeholder="password" name="password"><br><br>
        <input class="button" type="submit" value="Sign In">

    </form>
</div>
</body>
</html>
