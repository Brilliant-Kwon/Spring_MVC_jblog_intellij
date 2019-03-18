<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-18
  Time: 오전 10:38
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
<c:set var="cateList" scope="session" value="${sessionScope.cateList}"/>
<c:set var="postCount" scope="session" value="${sessionScope.postCount}"/>
<div id="container">
    <%--HEADER영역--%>
    <jsp:include page="../includes/header_blog.jsp"/>
    <jsp:include page="../includes/naviagtion_admin.jsp"/>

    <div id="wrapper">
        <div id="content">
            <h1>카테고리 관리</h1>
            <table>
                <tr>
                    <td>번호</td>
                    <td>카테고리 명</td>
                    <td>포스트 수</td>
                    <td>설명</td>
                    <td>삭제</td>
                </tr>
                <c:forEach items="${cateList}" var="cateVo">
                    <tr>
                        <td>${cateVo.cateNo}</td>
                        <td>${cateVo.cateName}</td>
                        <td>${postCount.get(cateVo.cateNo)}</td>
                        <td>${cateVo.description}</td>
                        <td><a href="">삭제</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
