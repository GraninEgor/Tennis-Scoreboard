<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="get" action="/matches">
        <input type="text" name="name">
        <input type="submit" >
    </form>
    <ul>
        <c:forEach var="match" items="${requestScope.matches}">
            <li>${match}</li>
        </c:forEach>
    </ul>
    <c:forEach var="i" begin="1" end="${requestScope.pages}">
        <a href="matches?page=${i}&name=${requestScope.name}">${i}</a>
    </c:forEach>
</body>
</html>
