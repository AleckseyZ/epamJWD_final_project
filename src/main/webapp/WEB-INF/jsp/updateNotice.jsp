<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <%@include file="common\head.jsp"%>
</head>

<body>
    <header>
        <%@include file="common\nav.jsp"%>
    </header>

    <c:if test = "${failure != null}">
        <f:message key="label.genericFailureMsg"/>
        <c:remove var="failure"/>
   </c:if>

    <c:remove var="message" scope="session"/>

    <form action="controller" method="POST">
        <input name="command" type="hidden" value="updateNotice">
        <input name="targetId" type="hidden" value="${targetId}">
        <label for="title"><f:message key="label.noticeTitle"/></label>
        <input name="title"type="text" value="${title}">
        <label for="body"><f:message key="label.noticeBody"/></label>
        <textarea name="body">${body}</textarea>
        <input type="submit">
    </form>

    <footer>
        <%@include file="common\footer.jsp"%>
    </footer> 
</body>

</html>