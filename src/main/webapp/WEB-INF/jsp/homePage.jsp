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

    <h1><f:message key="label.announcment"/></h1>
    <c:if test = "${failure != null}">
        <f:message key="label.genericFailureMsg"/>
        <c:remove var="failure"/>
   </c:if>
   
    <c:if test="${sessionScope.role == 'библиотекарь'}">
         <a href="controller?command=addNoticePage">
            <f:message key="label.addNotice" />
        </a>
    </c:if>
    <c:if test="${notices !=null}">
        <c:forEach var="notice" items="${notices}">
        <div>
            <h4>${notice.postDate}</h4>
            <h2>${notice.titleText}</h2>
            <p>${notice.bodyText}</p>
            <c:if test="${sessionScope.role == 'библиотекарь'}">
            <a href="controller?command=updateNoticePage&targetId=${notice.noticeId}">
                <f:message key="label.editNotice" />
            </a>
            <form action="controller" method="POST">
                <input name="command" type="hidden" value="deleteNotice">
                <input name="targetId" type="hidden" value="${notice.noticeId}">
                <button type="submit"><f:message key="label.deleteNotice" /></button>
            </form>
            </c:if>
        </div>
        </c:forEach>
    </c:if>
    
    <footer>
        <%@include file="common\footer.jsp"%>
    </footer> 
</body>

</html>