<%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-14
  Time: 오후 1:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jBlog</title>
    <link type="text/css"
          rel="stylesheet"
          href="<%= request.getContextPath() %>/css/header_footer.css"/>
</head>
<body>
<div id="container">
    <%--HEADER영역--%>
    <jsp:include page="../includes/header.jsp"/>
    <%--NAVIGATION영역--%>
    <%--<jsp:include page="includes/navigation.jsp"/>--%>
    <div id="wrapper">
        <div id="content">
<h1>로그인 성공</h1>
        </div>
    </div>

        <%--FOOTER영역--%>
        <jsp:include page="../includes/footer.jsp"/>
        <%--<%@include file="includes/footer.jsp"%>--%>
</div>
</body>
</html>
