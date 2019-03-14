<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Homepage</title>
    <!-- TODO: 현재 페이지에 적절한 CSS를 임포트하십시오. -->
    <link type="text/css" rel="stylesheet"
          href="<c:url value="/css/header_footer.css" />" />
</head>
<body>
<div id="container">

    <!-- HEADER영역 -->
    <jsp:include page="/WEB-INF/views/includes/header.jsp" />

    <div id="wrapper">
        <div id="content">
            <!-- Content 영역 -->
            <div id="site-introduction">
                <h1>Error</h1>
                <h2>${name }</h2>
                <h3>${message }</h3>
            </div>
        </div>
    </div>
    <!-- <!— FOOTER영역 —> -->
    <jsp:include page="/WEB-INF/views/includes/footer.jsp"/>
<%--<!— jsp:include 특정기능들 사용시—> &ndash;%&gt;--%>
    <%--<%@ include file="/WEB-INF/views/includes/footer.jsp"%>--%>
    <!-- <!—    공통부분들 , 공통소스들 사용 시 —> -->
</div>
</body>
</html>