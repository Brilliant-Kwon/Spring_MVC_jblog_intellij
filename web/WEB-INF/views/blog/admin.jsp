<%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-18
  Time: 오전 10:22
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
    <jsp:include page="../includes/header_blog.jsp"/>
        <jsp:include page="../includes/naviagtion_admin.jsp"/>

    <div id="wrapper">
        <div id="content">
            <h1>기본 설정</h1>
        </div>
    </div>
        <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
