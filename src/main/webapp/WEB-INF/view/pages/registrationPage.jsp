<%--
  Created by IntelliJ IDEA.
  User: Глеб
  Date: 08.12.2022
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
  <style><%@include file="../css/registrationPageStyle.css"%></style>
</head>

<body>
<meta charset="UTF-8">
<div id="registration-form-wrap">
  <h2>Registration</h2>
  <form id="registration-form" method="post" action="${pageContext.request.contextPath}/registration">

    <% if (request.getParameter("username") != null) { %>
    <p>
      <label for="username"><b>Username</b></label><br>
      <input type="text" required placeholder="Username" name="username" id="username" value=<%= request.getParameter("username")%>>
    </p>
    <% } else { %>
    <p>
      <label for="username_second"><b>Username</b></label><br>
      <input type="text" required placeholder="Username" name="username" id="username_second">
    </p>
    <% } %>

    <p>
    <label for="psw"><b>Password</b></label><br>
    <input type="password" required placeholder="Password" name="password" id="psw">
    </p>
    <p>
    <label for="psw-repeat"><b>Repeat Password</b></label><br>
    <input type="password" required placeholder="Repeat Password" name="psw-repeat" id="psw-repeat">
    </p>
    <div class='container'>
      <h6 style='color: red;'><%= request.getAttribute("wrongRegistrationData")%></h6>
    </div>
    <p>
      <input class="button" type="submit" value="Registration" id="login">
    </p>
  </form>
  <div id="create-account-wrap">
    <p>Already have an account? <a href="${pageContext.request.contextPath}/">Sign in</a></p><p>
  </p></div>
</div>

</body>
</html>
