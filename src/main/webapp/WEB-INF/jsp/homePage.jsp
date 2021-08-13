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

    <h1><f:message key="label.announcment"/></h1>
    <c:if test="${notices !=null}">
        <c:forEach var="notice" items="${notices}">
        <div>
            <h4>${notice.postDate}</h4>
            <h2>${notice.titleText}</h2>
            <p>${notice.bodyText}</p>
        </div>
        </c:forEach>
    </c:if>
    
    <%@include file="common\footer.jsp"%>
</body>

</html>