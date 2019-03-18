<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<c:set var="authUser" scope="session" value="${sessionScope.authUser}"/>
<c:set var="blogVo" scope="session" value="${sessionScope.blogVo}"/>
<div id="container">
    <%--HEADER영역--%>
    <jsp:include page="../includes/header_blog.jsp"/>
    <jsp:include page="../includes/naviagtion_admin.jsp"/>

    <div id="wrapper">
        <div id="content">
            <h1>기본 설정</h1>
            <form method="post" enctype="multipart/form-data" action="${pageContext.servletContext.contextPath }/${authUser.id}/admin/upload">
                <table>
                    <tr>
                        <td>블로그 제목</td>
                        <td><input type="text" name="title"></td>
                    </tr>
                    <tr>
                        <td>로고 이미지</td>
                        <td><img src="${pageContext.servletContext.contextPath }/${blogVo.logoFile }" alt="현재 로고" style="width: 150px"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="file" name="logo_File"></td>
                    </tr>
                </table>
                <input type="submit" value="기본 설정 변경" style="width: 150px">
            </form>
        </div>
    </div>
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
