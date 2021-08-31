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
    <h1>
        <f:message key="label.adminMenu" />
    </h1>

    <c:if test="${failure != null}">
        <f:message key="label.failedLogInMessage" />
        <c:remove var="failure" />
    </c:if>

    <form action="controller" method="GET">
        <input type="hidden" name="command" value="findUser" required>
        <label for="username">
            <f:message key="label.username" />
        </label>
        <input type=text name="username" required>
        <input type="submit">
    </form>

    <c:if test="${user !=null}">
        <div>
            <p>${user.userId}</p>
            <p>${user.username}</p>
            <p>${userType}</p>
            <form action="controller" method="POST">
            <input name="command" type="hidden" value="updateRole">
            <input name="targetId" type="hidden" value="${user.userId}">
            <select name="userType">
                <c:forEach var="userTypeOption" items="${userTypeOptions}">
                    <option value="${userTypeOption}">${userTypeOption}</option>
                </c:forEach>
            </select>
            <button type="submit">
                <f:message key="label.updateRole" />
            </button>
            </form>
        </div>
    </c:if>

    <footer>
        <%@include file="common\footer.jsp" %>
    </footer>
</body>

</html>