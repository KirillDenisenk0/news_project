<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>

</head>
<body>

<ul>
        <c:forEach var="news" items="${requestScope.newsDtoList}">
            <li>
               <a href="http://localhost:8080/myProjectNewss/newsText?newsId=${news.id}">Новость:${news.name}</a>
            </li>
        </c:forEach>
</ul>

<ul>
    <c:if test="${not empty requestScope.error}">
        <div style="color: red">
            ${requestScope.error.getErrorText()}
        </div>
    </c:if>
</ul>
<button type="button">
    <a href="${pageContext.request.contextPath}/createNews">Создать новость</a></button><br>
</body>
</html>