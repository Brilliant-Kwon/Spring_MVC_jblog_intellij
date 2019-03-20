<%@ page import="jblog.vo.BlogVo" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-04
  Time: 오전 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<c:set var="authUser" scope="session" value="${sessionScope.authUser}"/>--%>
<%--<c:set var="cateList" scope="session" value="${sessionScope.cateList}"/>--%>
<%--<c:set var="blogVo" scope="session" value="${sessionScope.blogVo}"/>--%>
<%--<c:set var="blogUser" scope="session" value="${sessionScope.blogUser}"/>--%>
<div id="navigation" style="float: right;" align="center">
    <%
        BlogVo blogVo = (BlogVo) request.getAttribute("blogVo");
    %>
    <ul style=" background: #e2ff23; padding-left: 0">
        <li><a href="<%= request.getContextPath() %>/${blogUser.id}">
            <img src="<%= request.getContextPath()%>/<%=blogVo.getLogoFile()%>" alt="logo" style="width: 150px"/>
        </a></li>
        <li><a style="color: #1a1a1a;
    font-weight: bold;
    text-decoration: none;" href="<%= request.getContextPath() %>/${blogUser.id}/whole">카테고리</a></li>
        <br>
        <c:forEach items="${cateList}" var="cateVo">
            <li><a style="color: #23bf5c;
    font-weight: bold;
    text-decoration: none;" href="<%= request.getContextPath() %>/${blogUser.id}/category/${cateVo.cateNo}">${cateVo.cateName}</a></li>
        </c:forEach>
    </ul>
</div>