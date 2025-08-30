<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="get" action="matches?filter_by_player_name=${NAME}">
        <input type="text">
        <input type="submit" >
    </form>
    <ul>
        <c:forEach var="match" items="${requestScope.matches}">
            <li>${match}</li>
        </c:forEach>
    </ul>
</body>
</html>
