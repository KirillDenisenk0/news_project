<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Login page</title>
</head>
<body>
    <form method="post">
        Email: <label for="emailId">
            <input type="text" name="Email" id="emailId" required>
        </label><br>
        <br>
        Password: <label for="passwordId">
            <input name="Password" type="password" id="passwordId" required>
        </label><br>

        <button type="submit" style="color: cornflowerblue">Войти</button>

        <a href="http://localhost:8080/myProjectNewss/registration">
            <button type="button">Регистрация</button>
        </a>
    </form>

<a href="http://localhost:8080/myProjectNewss/news"> Войти без авторизации</a>

<c:if test="${param.error != null}" >
    <span style="color: red">Your email or Password is not correct</span>
</c:if>
</body>
</html>
