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
                <f:message key="label.failedLogInMessage" />
                <c:remove var="failure" />
            </c:if>

            <form action="controller" method="POST">
                <input name="command" type="hidden" value="logIn" required>
                <label for="username">
                    <f:message key="label.username" />
                </label>
                <input name="username" type="text">
                <label for="password">
                    <f:message key="label.password" />
                </label>
                <input name="password" type="password" required>
                <button type="submit">
                    <f:message key="label.login" />
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