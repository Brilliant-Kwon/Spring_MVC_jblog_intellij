<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-04
  Time: 오전 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="authUser" scope="session" value="${sessionScope.authUser}"/>
<c:set var="cateList" scope="session" value="${sessionScope.cateList}"/>
<c:set var="blogVo" scope="session" value="${sessionScope.blogVo}"/>
<div id="navigation">
    <ul>
        <li><a href="<%= request.getContextPath() %>/${authUser.id}">
            <img src="<%= request.getContextPath()%>/${blogVo.logoFile}" alt="logo" style="width: 150px"/>
        </a></li>
        <li><a href="<%= request.getContextPath() %>/${authUser.id}">카테고리</a></li>
        <c:forEach items="${cateList}" var="cateVo">
            <li><a href="<%= request.getContextPath() %>/${authUser.id}">${cateVo.cateName}</a></li>
        </c:forEach>
    </ul>
</div>