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

    <form action="controller" method="POST" enctype="multipart/form-data">
        <input name="command" type="hidden" value="updateBook">
        <input name="targetId" type="hidden" value="${target.bookId}">
        <input name="originalCover" type="hidden" value="${target.cover}">

        <label for="title"><f:message key="label.bookTitle" /></label>
        <input name="title"type="text" value="${target.title}" required>

        <label for="author"><f:message key="label.author"/></label>
        <input name="author"type="text" value="${target.author}" required>

        <label for="blurlb"><f:message key="label.blurb"/></label>
        <textarea name="blurb" required>${target.blurb}</textarea>

        <label for="amount"><f:message key="label.amount"/></label>
        <input name="amount"type="number" min='0' value="${target.amount}" required>

        <label for="tags"><f:message key="label.tags"/></label>
        <input name="tags" type="text" value="${target.tags}" required>

        <label for="cover"><f:message key="label.cover"/></label>
        <input type="file" name="cover" accept="image/*">
        
        <input type="submit">
    </form>

    <footer>
        <%@include file="common\footer.jsp"%>
    </footer> 
</body>

</html>