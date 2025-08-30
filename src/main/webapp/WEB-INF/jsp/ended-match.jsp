<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.08.2025
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
  </head>
  <body>
    <h1><%= request.getAttribute("firstPlayerName")%></h1>
    <h1><%= request.getAttribute("firstPlayerScore")%></h1>
    <h1><%= request.getAttribute("secondPlayerName")%></h1>
    <h1><%= request.getAttribute("secondPlayerScore")%></h1>
    <h1><%= request.getAttribute("winnerName")%></h1>
  </body>
</html>
