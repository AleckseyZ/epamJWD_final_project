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
    <h1><f:message key="label.loanMenu"/></h1> 
    <%@include file="common\footer.jsp"%>
</body>

</html>