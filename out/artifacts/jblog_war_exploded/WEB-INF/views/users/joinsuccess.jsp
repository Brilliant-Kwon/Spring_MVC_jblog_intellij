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
    <style type="text/css">
        div#content {
            text-align: center;
        }

        div#content a {
            font-weight: bold;
        }
    </style>
</head>
<body>
<div id="container">
    <%--HEADER영역--%>
    <jsp:include page="../includes/header.jsp"/>
    <%--NAVIGATION영역--%>
    <%--<jsp:include page="includes/navigation.jsp"/>--%>
    <div id="wrapper">
        <div id="content">
            <h3>"감사합니다. 회원 가입 및 블로그가 성공적으로 만들어 졌습니다."</h3>
            <a href="${pageContext.servletContext.contextPath}/users/login">로그인 하기</a>
            <br><br>
        </div>
    </div>

    <%--FOOTER영역--%>
    <jsp:include page="../includes/footer.jsp"/>
    <%--<%@include file="includes/footer.jsp"%>--%>
</div>
</body>
</html>
