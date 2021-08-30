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
   
    <p><f:message key="label.username"/> ${user.username}</p>
    <p><f:message key="label.email"/> ${user.email}</p>

    <form action="controller" method="POST">
      <input name="command" type="hidden" value="updateMail">
      <input name="targetId" type="hidden" value="${sessionScope.userId}">
      <label for="email"><f:message key="label.email"/></label>
      <input name="email" type="email" required>
      <input type="submit">
    </form>

    <a href="controller?command=updateMailPage&targetId=${sessionScope.userId}">
      <f:message key="label.updateMail" />
    </a>

    <form action="controller" method="POST">
      <input name="command" type="hidden" value="updatePassword">
      <input name="targetId" type="hidden" value="${sessionScope.userId}">
      <label for="password"><f:message key="label.oldPassword"/></label>
      <input name="password"type="password" required minlength="6">
      <label for="newPassword"><f:message key="label.newPassword"/></label>
      <input name="newPassword"type="password" required minlength="6">
      <label for="repeatPassword"><f:message key="label.repeatPassword"/></label>
      <input name="repeatPassword"type="password" required minlength="6">
      <input type="submit">
    </form>

    <a href="controller?command=updatePasswordPage&targetId=${sessionScope.userId}">
      <f:message key="label.updatePassword" />
    </a>
    <c:if test="${card !=null}">
    <div>
      <p>${card.cardId}</p>
      <p>${status}</p>
    </div>
    </c:if>
    <footer>
      <%@include file="common\footer.jsp"%>
  </footer> 
</body>

</html>