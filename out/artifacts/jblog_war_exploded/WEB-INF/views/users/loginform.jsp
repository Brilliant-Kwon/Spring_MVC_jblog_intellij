<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-14
  Time: 오후 1:24
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
<div id="container">
    <%--todo : 로그인 실패 결과 화면에 띄우기--%>
    <%--HEADER영역--%>
    <jsp:include page="../includes/header.jsp"/>
    <%--NAVIGATION영역--%>
    <%--<jsp:include page="includes/navigation.jsp"/>--%>
    <div id="wrapper">
        <div id="content">
            <div id="user">
                <%--Login form--%>
                <%--<form id="Login-form" name="Loginform" method="post"--%>
                <%--action="<%=request.getContextPath()%>/users/login">--%>
                <form:form modelAttribute="userVo" method="post"
                           action="${pageContext.servletContext.contextPath}/users/login">

                    <label class="block-label" for="id">아이디</label>
                    <%--<input type="text" name="id">--%>
                    <form:input path="id"/>
                    <label class="block-label" for="password">비밀번호</label>
                    <%--<input type="password" name="password">--%>
                    <form:input path="password"/>

                    <spring:hasBindErrors name="userVo">
                        <c:if test="${errors.hasFieldErrors('id') } || ${errors.hasFieldErrors('password') }">
                            <strong style="color: red">
                                로그인 실패<br>아이디/패스워드를 확인해 주세요.
                            </strong>
                        </c:if>
                    </spring:hasBindErrors>

                    <input type="submit" value="로그인">
                </form:form>
            </div>
        </div>
    </div>

    <%--FOOTER영역--%>
    <jsp:include page="../includes/footer.jsp"/>
    <%--<%@include file="includes/footer.jsp"%>--%>
</div>
</body>
</html>
