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
<div id="navigation">
    <ul>
        <li><a href="<%= request.getContextPath() %>/${authUser.id}">로고가 들어갈 곳</a></li>
        <li><a href="<%= request.getContextPath() %>/guestbook">카테고리</a></li>
        <li><a href="<%= request.getContextPath() %>/board">게시판</a></li>
    </ul>
</div>