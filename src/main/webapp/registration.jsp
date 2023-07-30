<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Страница регистрации</title>
</head>
<body>
<form method="post">
    Name:<label for="idName">
    <input type="text" name="name" id="idName">
</label><br>

    Birthday<label for="birthId">
    <input type="date" name="birthday" id="birthId">
</label><br>

    Country:<label for="idCountry">
    <input type="text" name="country" id="idCountry">
</label><br>

    Email:<label for="idEmail">
    <input type="text" name="email" id="idEmail" required>
</label><br>

    Password:<label for="idPassword">
    <input type="password" name="password" id="idPassword" required>
</label><br>

    Role:<label>
    <select name="role">
        <option value="USER">USER</option>
        <option value="ADMIN">ADMIN</option>
    </select>
</label><br>

    Sex:<label>
    <select name="gender">
        <option value="MALE">MALE</option>
        <option value="FEMALE">FEMALE</option>
    </select>
</label><br>

    <button type="submit">Registration</button>
</form>

<c:if test="${not empty requestScope.errors}">
    <c:forEach var="error" items="${requestScope.errors}">
        <div style="color: red">${error.text}</div>
    </c:forEach>
</c:if>

<c:if test="${not empty requestScope.duplicate}">
    <div style="color: red">${requestScope.duplicate.getERROR_TEXT()}</div>
</c:if>

</body>
</html>
