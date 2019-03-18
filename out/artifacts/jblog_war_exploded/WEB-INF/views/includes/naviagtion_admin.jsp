<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-18
  Time: 오전 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="authUser" scope="session" value="${sessionScope.authUser}"/>
<div id="navigation_admin">
    <ul >
        <li style="display: inline"><a href="<%= request.getContextPath() %>/${authUser.id}/admin/basic">기본설정</a></li>
        <li style="display: inline"><a href="<%= request.getContextPath() %>/${authUser.id}/admin/category">카테고리</a></li>
        <li style="display: inline"><a href="<%= request.getContextPath() %>/${authUser.id}/admin/write">글 작성</a></li>
    </ul>
</div>