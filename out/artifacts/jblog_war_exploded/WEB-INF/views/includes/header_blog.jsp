<%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-15
  Time: 오전 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="blogVo" scope="session" value="${sessionScope.blogVo}"/>
<c:set var="authUser" scope="session" value="${sessionScope.authUser}"/>
<div id="header_blog" align="center">
    <h1>${blogVo.blogTitle}</h1>
    <ul>
        <%--로그인 된 경우의 메뉴--%>
        <%--
        <li><a href="<%=request.getContextPath() %>/users?a=logout">로그아웃</a></li>
        <li>님 안녕하세요.</li>
        --%>

        <c:choose>
            <c:when test="${authUser.userNo eq blogVo.userNo}">
                <%--<c:otherwise>--%>
                <li><a href="${pageContext.servletContext.contextPath }/${authUser.id}/admin/basic">내블로그 관리</a></li>
                <li><a href="${pageContext.servletContext.contextPath }/users/logout">로그아웃</a></li>
                <%--</c:otherwise>--%>
            </c:when>

            <c:when test="${authUser.userNo ne blogVo.userNo && authUser ne null}">
                <li><a href="${pageContext.servletContext.contextPath }/users/logout">로그아웃</a></li>
            </c:when>

            <c:when test="${empty authUser || authUser eq null}">
                <%--로그인 안 된 경우의 메뉴--%>
                <li><a href="${pageContext.servletContext.contextPath }/users/join">회원가입</a></li>
                <li><a href="${pageContext.servletContext.contextPath }/users/login">로그인</a></li>
            </c:when>
        </c:choose>
    </ul>
</div>