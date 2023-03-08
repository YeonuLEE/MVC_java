<%--
  Created by IntelliJ IDEA.
  User: yeonu
  Date: 2023/03/06
  Time: 4:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HelloServlet.jsp</title>
</head>
<body>
<h2>1. web.xml 에서 매핑 후 JSP에서 출력</h2>
<p>
    <B><%=request.getAttribute("message")%></B>
    <a href="./Hello11.do">HelloServlet 호출</a>
</p>
</body>
</html>
