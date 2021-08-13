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

    <h1><f:message key="label.catalogue"/></h1>

    <form action="controller" method="GET">
        <input type="hidden" name="command" value="findBooks" required>
        <input type="text" name="search" required>
        <label for="title"><f:message key="label.title"/></label>
        <input type=radio name="criteria" value="title" id="title" checked>
        <label for="title"><f:message key="label.author"/></label>
        <input type=radio name="criteria" value="author" id="author">
        <label for="title"><f:message key="label.tags"/></label>
        <input type=radio name="criteria" value="tags" id="tags">
        <input type="submit">
    </form>

    <c:if test="${books !=null}">
        <c:forEach var="book" items="${books}">
        <div>
            <h4>${book.title}</h4>
            <p>${book.author}</p>
            <p>${book.blurb}</p>
        </div>
        </c:forEach>
    </c:if>
    
    <%@include file="common\footer.jsp"%>
</body>

</html>