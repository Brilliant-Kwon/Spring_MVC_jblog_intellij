<%@ page import="java.util.Map" %>
<%@ page import="jblog.vo.CategoryVo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
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
<%--<c:set var="cateList" scope="session" value="${sessionScope.cateList}"/>--%>
<c:set var="postCount" scope="session" value="${sessionScope.postCount}"/>
<div id="container" align="center">
    <%--HEADER영역--%>
    <jsp:include page="../includes/header_blog.jsp"/>
    <jsp:include page="../includes/naviagtion_admin.jsp"/>

    <%
        Map pCount = (Map) session.getAttribute("postCount");
        System.out.println("pCount : " + pCount.toString());
    %>

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
                <%
                    List<CategoryVo> list = (List<CategoryVo>) request.getAttribute("cateList");
                    Iterator iterator = list.iterator();
                    System.out.println(iterator.toString());
                %>
                <c:forEach items="${cateList}" var="cateVo">
                    <%
                        CategoryVo vo = (CategoryVo) iterator.next();
                        Long caNo = vo.getCateNo();
                        System.out.println(caNo);
                    %>
                    <tr>
                        <td>${cateVo.cateNo}</td>
                        <td>${cateVo.cateName}</td>
                        <td><%=pCount.get(caNo)%>
                        </td>
                        <td>${cateVo.description}</td>
                        <td><a href="${pageContext.servletContext.contextPath }/${authUser.id}/admin/delete/${cateVo.cateNo}">삭제</a></td>
                    </tr>
                </c:forEach>
            </table>

            <br>
            <br>

            <div>
                <h3>새로운 카테고리 추가</h3>
                <form method="post" action="${pageContext.servletContext.contextPath }/${authUser.id}/admin/category">
                    <table>
                        <tr>
                            <td>카테고리명</td>
                            <td><input type="text" name="cateName"></td>
                        </tr>
                        <tr>
                            <td>설명</td>
                            <td><input type="text" name="description"></td>
                        </tr>
                    </table>
                    <input type="submit" value="카테고리 추가" style="width: 150px">
                </form>
            </div>
        </div>
    </div>
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
