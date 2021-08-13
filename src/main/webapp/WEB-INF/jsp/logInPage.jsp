<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <%@include file="common\language.jsp"%>
        <title><f:message key="label.pageTitle"/></title>
    </head>
    <body>
        <header>
            <%@include file="common\nav.jsp"%>
        </header>
        <c:if test = "${failed != null}">
            <f:message key="label.failedLogInMessage"/>
        </c:if>
        <form action="controller" method="POST">
            <input name="command" type="hidden" value="logIn" required>
            <label for="username"><f:message key="label.username"/></label>
            <input name="username"type="text">
            <label for="password"><f:message key="label.password"/></label>
            <input name="password"type="password" required>
            <input type="submit">
        </form>
          <%@include file="common\footer.jsp"%>
    </body>

</html>