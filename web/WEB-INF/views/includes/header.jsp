<%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-04
  Time: 오전 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="header" align="center"  style="margin: 0 auto">
    <a href="${pageContext.servletContext.contextPath }/main"><img height="200px" src="<%= request.getContextPath()%>/upload-images/logo.png"></a>
    <ul>
        <%--로그인 된 경우의 메뉴--%>
        <%--
        <li><a href="<%=request.getContextPath() %>/users?a=logout">로그아웃</a></li>
        <li>님 안녕하세요.</li>
        --%>
        <c:set var="authUser" scope="session" value="${sessionScope.authUser}"/>
        <c:choose>
            <c:when test="${not empty authUser}">
                <%--<c:otherwise>--%>
                <li><a href="${pageContext.servletContext.contextPath }/${authUser.id}">내 블로그</a></li>
                <li><a href="${pageContext.servletContext.contextPath }/users/logout">로그아웃</a></li>
                <%--</c:otherwise>--%>
            </c:when>

            <c:when test="${empty authUser}">
                <%--로그인 안 된 경우의 메뉴--%>
                <li><a href="${pageContext.servletContext.contextPath }/users/join">회원가입</a></li>
                <li><a href="${pageContext.servletContext.contextPath }/users/login">로그인</a></li>
            </c:when>
        </c:choose>
    </ul>
</div>
<!-- /header -->