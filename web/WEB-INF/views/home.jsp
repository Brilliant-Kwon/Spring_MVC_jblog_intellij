<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-14
  Time: 오전 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>jBlog</title>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.servletContext.contextPath }/css/header_footer.css"/>
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.servletContext.contextPath }/css/home.css"/>
</head>
<body>
<div id="container">
    <%--HEADER영역--%>
    <jsp:include page="includes/header.jsp"/>
    <%--NAVIGATION영역--%>
    <%--<jsp:include page="includes/navigation.jsp"/>--%>
    <div id="wrapper">
        <div id="content">
            <!-- Content 영역 -->
            <div id="site-introduction">
                <form action="${pageContext.servletContext.contextPath }/main" method="post">
                    <input type="text" style="width: 500px" name="search_input">
                    <input type="submit" value="전송">
                    <br>
                    <input type="radio" name="search_choose" value="blogtitle" checked="checked">블로그 제목
                    <input type="radio" name="search_choose" value="bloguser">블로거
                </form>
            </div>
        </div>
    </div>

    <%--FOOTER영역--%>
    <jsp:include page="includes/footer.jsp"/>
    <%--<%@include file="includes/footer.jsp"%>--%>
</div>
</body>
</html>
