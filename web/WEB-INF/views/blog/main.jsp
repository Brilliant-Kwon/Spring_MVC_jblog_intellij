<%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-15
  Time: 오전 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <%--NAVIGATION영역--%>
    <jsp:include page="../includes/navigation.jsp"/>
    <div id="wrapper">
        <div id="content">

        </div>
    </div>
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
