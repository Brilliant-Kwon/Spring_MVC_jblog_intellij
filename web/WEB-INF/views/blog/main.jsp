<%--
  Created by IntelliJ IDEA.
  User: k1212
  Date: 2019-03-15
  Time: 오전 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>jBlog</title>
    <link type="text/css"
          rel="stylesheet"
          href="<%= request.getContextPath() %>/css/header_footer.css"/>
</head>
<body>
<c:set var="wholePost" scope="session" value="${sessionScope.wholePost}"/>
<c:set var="cateList" scope="session" value="${sessionScope.cateList}"/>
<c:set var="postList" scope="session" value="${sessionScope.postList}"/>
<c:set var="postContent" scope="session" value="${sessionScope.postContent}"/>
<div id="container">
    <%--HEADER영역--%>
    <jsp:include page="../includes/header_blog.jsp"/>
    <%--NAVIGATION영역--%>
    <jsp:include page="../includes/navigation.jsp"/>
    <div id="wrapper">
        <div id="content">
            <div id="post-content">
                <c:choose>
                    <c:when test="${not empty postList}">
                        <c:when test="${not empty postContent}">
                            <h1>${postContent.postTitle}</h1>
                            <p>${postContent.postContent}</p>
                        </c:when>
                        <c:otherwise>
                            <h1>게시글에 글이 없습니다..</h1>
                            <p>내용자리</p>
                        </c:otherwise>
                    </c:when>
                    <c:otherwise>
                        <h1>카테고리에 글이 없습니다.</h1>
                        <p>내용자리</p>
                    </c:otherwise>
                </c:choose>
            </div>
            <div id="post-list">
                <table id="posts">
                    <c:choose>
                        <c:when test="${not empty postList}">
                            <c:forEach items="${postList} " var="postVo">
                                <tr>
                                    <td>${postVo.postTitle}</td>
                                    <td>${postVo.regDate}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>포스트</td>
                                <td>없음</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    <%--<tr>
                        <th>번호</th>
                        <th>카테고리</th>
                        <th>제목</th>
                        <th>내용</th>
                        <th>작성일</th>
                    </tr>

                    <c:choose>
                        <c:when test="${not empty wholePost}">
                            <c:forEach items="${wholePost}" var="postVo">
                                <tr>
                                    <td>${postVo.postNo}</td>
                                    <c:forEach items="${cateList}" var="cateVo">
                                        <c:if test="${cateVo.cateNo eq postVo.cateNo}">
                                            <td>${cateVo.cateName}</td>
                                        </c:if>
                                    </c:forEach>
                                    <td>${postVo.postTitle}</td>
                                    <td>${postVo.postContent}</td>
                                    <td>${postVo.regDate}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="5">등록된 글이 없습니다.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>--%>
                </table>
            </div>
        </div>
    </div>
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
