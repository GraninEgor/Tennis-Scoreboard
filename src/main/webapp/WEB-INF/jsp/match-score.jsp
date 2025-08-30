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
<form method="post" action="/match-score?uuid=<%=request.getParameter("uuid")%>">
    <button type="submit" name="action" value="firstPlayerGotPoint">firstPlayer</button>
    <button type="submit" name="action" value="secondPlayerGotPoint">secondPlayer</button>
</form>
</body>
</html>
