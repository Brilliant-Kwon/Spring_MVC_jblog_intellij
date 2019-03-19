<%@ page import="jblog.vo.PostVo" %>
<%@ page import="java.util.List" %>
<%@ page import="javafx.geometry.Pos" %><%--
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
<%--<c:set var="wholePost" scope="session" value="${sessionScope.wholePost}"/>--%>
<%--<c:set var="cateList" scope="session" value="${sessionScope.cateList}"/>--%>
<%--c:set var="postList" scope="session" value="${sessionScope.postList}"/--%>
<%--<c:set var="postContent" scope="session" value="${sessionScope.postContent}"/>--%>

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
                        <%--<c:when test="${not empty postContent}">--%>
                        <h1>${postContent.postTitle}</h1>
                        <p>${postContent.postContent}</p>
                        <%--</c:when>--%>
                        <%--<c:otherwise>--%>
                        <%--<h1>게시글에 글이 없습니다..</h1>--%>
                        <%--<p>내용자리</p>--%>
                        <%--</c:otherwise>--%>
                    </c:when>
                    <c:otherwise>
                        <h1>카테고리에 글이 없습니다.</h1>
                        <p>내용자리</p>
                    </c:otherwise>
                </c:choose>
            </div>


            <div id="comment-write">
                <%--댓글작성--%>
                <c:choose>
                    <c:when test="${not empty authUser and not empty postContent.postNo}">
                        <form method="post"
                              action="<%= request.getContextPath() %>/${blogUser.id}/comment/${postContent.postNo}/${authUser.userNo}">
                            <input type="hidden" name="postNo" value="${postContent.postNo}">
                            <input type="hidden" name="userNo" value="${authUser.userNo}">
                            <table>
                                <tr>
                                    <td>${authUser.userName}</td>
                                    <td><input type="text" name="cmtContent"></td>
                                    <td><input type="submit" value="저장"></td>
                                </tr>
                            </table>
                        </form>
                    </c:when>
                </c:choose>
            </div>

            <div id="comment-list">
                <c:if test="${not empty postContent and not empty commentList}">
                    <table>
                        <c:forEach items="${commentList}" var="commentVo">
                            <tr>
                                <td>${commentVo.userName}</td>
                                <td>${commentVo.cmtContent}</td>
                                <td>${commentVo.regDate}</td>
                                <%--<c:if test="${authUser.userNo eq }"--%>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>

            <div id="post-list">
                <table id="posts">
                    <c:choose>
                        <c:when test="${not empty postList}">
                            <%
                                List<PostVo> list = (List<PostVo>) request.getAttribute("postList");
                                for (int i = 0; i < list.size(); i++) {%>
                            <tr>
                                <%
                                    PostVo postContent = (PostVo) request.getAttribute("postContent");
                                    if (((List<PostVo>) request.getAttribute("postList")).get(i).getPostNo().equals(postContent.getPostNo())) {
                                %>
                                <th>
                                    <a href="<%= request.getContextPath() %>/${blogUser.id}/post/<%=list.get(i).getPostNo()%>"><%=list.get(i).getPostTitle()%>
                                </th>
                                <%
                                } else {
                                %>
                                <td>
                                    <a href="<%= request.getContextPath() %>/${blogUser.id}/post/<%=list.get(i).getPostNo()%>"><%=list.get(i).getPostTitle()%>
                                </td>
                                <%
                                    }
                                %>
                                <td>
                                    <%=list.get(i).getRegDate()%>
                                </td>
                            </tr>
                            <%
                                }
                            %>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td>포스트</td>
                                <td>없음</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </table>
            </div>
        </div>
    </div>
    <jsp:include page="../includes/footer.jsp"/>
</div>
</body>
</html>
