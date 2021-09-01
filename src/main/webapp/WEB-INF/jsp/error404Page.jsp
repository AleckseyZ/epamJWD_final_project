<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
    <%@include file="common\head.jsp" %>
</head>

<body>
    <header>
        <%@include file="common\nav.jsp" %>
    </header>
    <div class="wrapper">

        <div class="side">
        </div>
        <div class="content">
            <h1>
                <f:message key="label.error404" />
            </h1>
            <p>
                <f:message key="label.error404Explanation" />
            </p>
        </div>
        <div class="side">
        </div>
    </div>
    <footer>
        <%@include file="common\footer.jsp" %>
    </footer>
</body>

</html>