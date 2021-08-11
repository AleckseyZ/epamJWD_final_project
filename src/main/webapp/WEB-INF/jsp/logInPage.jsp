<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <%@include file="common\language.jsp"%>
    </head>
    <body>
        <header>
            <%@include file="common\nav.jsp"%>
        </header>
        <form action="controller" method="POST">
            <input type="hidden" value="login" required>
            <label for="username"><f:message key="label.username"/></label>
            <input name="username"type="text">
            <label for="password"><f:message key="label.password"/></label>
            <input name="password"type="password" required>
            <input type="submit">
        </form>
          <%@include file="common\footer.jsp"%>
    </body>

</html>