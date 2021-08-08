<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
        <%@include file="common\locale.jsp"%>
    </head>
    <body>
        <header>
            <%@include file="common\nav.jsp"%>
        </header>
        <form action="controller">
            <input type="hidden" value="login">
            <label for="username"><f:message key="locale.username"/></label>
            <input name="username"type="text">
            <label for="password"><f:message key="locale.password"/></label>
            <input name="password"type="password">
        </form>
          <%@include file="common\footer.jsp"%>
    </body>

</html>