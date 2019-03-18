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
    <style type="text/css">
        div#user {
            padding: 0 auto;
        }
        div#user form {
            border:1px solid #666;
            border-radius: 10px;
            padding-left: 120px;
        }
    </style>
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath }/javascript/jquery/jquery-1.9.0.js"></script>
    <script type="text/javascript">
        function checked(obj) {
            var userName = obj.userName.value;
            var password = obj.password.value;
            var id = obj.id.value;
            var check = obj.check;
            // var checkResult = check.getAttribute("checked");
            var checkResult = $(check).prop("checked");

            if (userName.trim().length == 0) {
                alert("이름을 입력해주세요.");
            } else if (id.trim().length == 0) {
                alert("아이디를 입력해주세요.");
            } else if (password.trim().length == 0) {
                alert("비밀번호을 입력해주세요.");
            } else if (checkResult == false) {
                alert("약관에 동의해주세요.");
            } else {
                return true;
            }
            return false;
        }

        function checkId(btn) {
            //   아이디 필드 체크
            if (btn.form.id.value.trim().length == 0) { // 만약 btn이 소속된 form에서 email이란 이름을 가진 객체의 value 값이 공백을 제거하고 난 뒤 길이가
                alert("아이디를 입력해주세요.");// 0과 같다면 입력이 되지 않은 상태이므로 alert를 띄운다
                return;
            }
            //   JQuery Ajax 수행
            $.ajax({
                url: "${pageContext.servletContext.contextPath }/users/idcheck", // 사용할 컨트롤러 매핑 url
                type: "get", // 메소드 수행 방식
                dataType: "json", // 넘겨받을 데이터 타입
                data: {
                    id: btn.form.id.value
                },
                success: function (response) { // 응답값: response HashMap 형태로 넘긴 그것
                    if (response.data == "exist") {
                        alert("이미 가입된 아이디입니다.");
                    } else {
                        alert("사용할 수 있는 아이디입니다.");
                    }
                },
                error: function (xhr, status, err) { // ajax 요청 객체 : xhr, 상태값: status, 에러값: err
                    alert(status);
                    alert(err);
                }
            });
        }
    </script>
</head>
<body>
<div id="container">
    <%--HEADER영역--%>
    <jsp:include page="../includes/header.jsp"/>
    <%--NAVIGATION영역--%>
    <%--<jsp:include page="includes/navigation.jsp"/>--%>
    <div id="wrapper" align="center">
        <div id="content" align="left" >
            <div id="user">
            <form:form modelAttribute="userVo" method="post"
                       action="${pageContext.servletContext.contextPath}/users/join" onsubmit="checked(this)">

                <label class="block-label" for="userName">이름</label>
                <br>
                <form:input path="userName"/>
                <br>
                <%--<spring:hasBindErrors name="userVo">--%>
                    <%--<c:if test="${errors.hasFieldErrors('userName')}" var="e">--%>
                        <%--<strong>${errors.getFieldErrors('userName')[0].defaultMessage}</strong>--%>
                    <%--</c:if>--%>
                <%--</spring:hasBindErrors>--%>

                <label class="block-label" for="id">아이디</label>
                <br>
                <form:input path="id"/>
                <input type="button" value="id 중복 체크" onclick="checkId(this)">
                <br>
                <spring:hasBindErrors name="userVo">
                    <c:if test="${errors.hasFieldErrors('id') }">
                        <strong style="color: red"> <spring:message
                                code="${errors.getFieldError( 'id' ).codes[0] }"
                                text="${errors.getFieldError( 'id' ).defaultMessage }"/>
                        </strong>
                    </c:if>
                </spring:hasBindErrors>

                <label class="block-label" for="password">비밀번호</label>
                <br>
                <input type="password" name="password">
                <br>

                <fieldset>
                    <legend>약관동의</legend>
                    <input type="checkbox" name="check">서비스 약관에 동의합니다.
                </fieldset>

                <input type="submit" value="회원가입">

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
