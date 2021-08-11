<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <%@include file="common\language.jsp"%>
    <title><f:message key="label.title"/></title>
</head>

<body>
    <header>
      <%@include file="common\nav.jsp"%>
    </header>
    <h1><f:message key="label.librarianMenu"/></h1>
    <p><f:message key="label.bookMenu"/></p>
    <p><f:message key="label.cardMenu"/></p>
    <p><f:message key="label.loanMenu"/></p>    
    <%@include file="common\footer.jsp"%>
</body>

</html>