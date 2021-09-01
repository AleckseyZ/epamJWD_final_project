<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <%@include file="common\head.jsp" %>
</head>

<body>
    <header>
        <%@include file="common\nav.jsp" %>
    </header>

    <div class="wrapper">

        <div class="side">
        </div>
        <div class="content">

            <c:if test="${failure != null}">
                <f:message key="label.genericFailureMsg" />
                <c:remove var="failure" />
            </c:if>

            <form action="controller" method="POST">
                <input name="command" type="hidden" value="addNotice">
                <label for="title">
                    <f:message key="label.noticeTitle" />
                </label>
                <input name="title" type="text" required>
                <label for="body">
                    <f:message key="label.noticeBody" />
                </label>
                <textarea name="body"></textarea>
                <button type="submit">
                    <f:message key="label.add" />
                </button>
            </form>
        </div>
        <div class="side">
        </div>
    </div>
    <footer>
        <%@include file="common\footer.jsp" %>
    </footer>
</body>

</html>