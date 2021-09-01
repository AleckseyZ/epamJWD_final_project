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

            <h1>
                <f:message key="label.addCard" />
            </h1>

            <c:if test="${message != null}">
                <f:message key="label.failedMessage" />
                <c:remove var="message" scope="session" />
            </c:if>

            <form action="controller" method="POST">
                <input name="command" type="hidden" value="addCard" required>
                <label for="username">
                    <f:message key="label.username" />
                </label>
                <input name="username" type="text">
                <label for="holder">
                    <f:message key="label.holder" />
                </label>
                <input name="holder" type="text" required>
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