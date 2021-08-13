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
        <form action="controller" method="POST">
            <input name="command" type="hidden" value="signUp" required>
            <label for="email"><f:message key="label.email"/></label>
            <input name="email"type="email" required>
            <label for="username"><f:message key="label.username"/></label>
            <input name="username"type="text" required>
            <label for="password"><f:message key="label.passwordRequiments"/></label>
            <label for="password"><f:message key="label.password"/></label>
            <input name="password"type="password" required minlength="6">
            <label for="repeatPassword"><f:message key="label.repeatPassword"/></label>
            <input name="repeatPassword"type="password" required minlength="6">
            <input type="submit">
        </form>
          <%@include file="common\footer.jsp"%>
    </body>

</html>