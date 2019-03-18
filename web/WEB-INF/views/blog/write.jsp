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
<div id="container">
    <%--HEADER영역--%>
    <jsp:include page="../includes/header_blog.jsp"/>
    <jsp:include page="../includes/naviagtion_admin.jsp"/>

    <div id="wrapper">
        <div id="content">
            <h1>글 작성</h1>
            <form method="post" action="${pageContext.servletContext.contextPath }/${authUser.id}/admin/write">
                <table>
                    <tr>
                        <td class="label">제목</td>
                        <td><input type="text" name="postTitle" value=""></td>
                        <td><select name="cateNo">
                            <c:forEach items="${cateList}" var="cateVo"items="${cateList}" var="cateVo">
                                <option value="${cateVo.cateNo}">${cateVo.cateName}</option>
                            </c:forEach>
                        </select></td>
                    </tr>
                    <tr>
                        <td class="label">내용</td>
                        <td>
                            <textarea id="contentarea" name="postContent"></textarea>
                        </td>
                    </tr>
                </table>
                <div class="bottom">
                    <input type="submit" value="포스트 하기">
                </div>
            </form>

        </div>
    </div>
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
