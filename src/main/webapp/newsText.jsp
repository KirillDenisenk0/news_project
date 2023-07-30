<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>

</head>
<body>

<ul>
   <c:if test="${not empty requestScope.news}">
       <div>
           ${requestScope.news.text}
       </div>
   </c:if>
</ul>


<%--<button type="button"><a href="${pageContext.request.contextPath}/updateNews?newsId=${requestScope.news.id}">Edit news</a></button> --%>

<form method="post" action="${pageContext.request.contextPath}/createComments?newsId=${requestScope.news.id}">
    Комментарий:<label for="idText">
        <input name="comment" type="text" id="idText">
    </label><br>
    <button type="submit">Добавить комментарий</button>
</form>

<ul>
    <c:if test="${not empty requestScope.error}">
        <div style="color: red">
                ${requestScope.error.getErrorText()}
        </div>
    </c:if>
</ul>


</body>
</html>