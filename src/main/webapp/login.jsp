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

        <a href="${pageContext.request.contextPath}/registration">
            <button type="button">Регистрация</button>
        </a>
    </form>

<a href="${pageContext.request.contextPath}/news"> Войти без авторизации</a>

</body>
</html>