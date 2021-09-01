<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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

      <c:if test="${failure != null}">
        <f:message key="label.genericFailureMsg" />
        <c:remove var="failure" />
      </c:if>

      <div class="userinfo">
      <p>
        <f:message key="label.username" />
        <c:out value="${user.username}" />
      </p>
      <p>
        <f:message key="label.email" />
        <c:out value="${user.email}" />
      </p>
    </div>
    
      <form action="controller" method="POST" id="emailForm" style="display: none;">
        <input name="command" type="hidden" value="updateMail">
        <input name="targetId" type="hidden" value="${sessionScope.userId}">
        <label for="email">
          <f:message key="label.email" />
        </label>
        <input name="email" type="email" required>
        <button type="submit">
          <f:message key="label.change" />
        </button>
      </form>

      <button id="updateEmailButton">
        <f:message key="label.updateMail" />
      </button>

      <form action="controller" method="POST" id="passwordForm" style="display: none;">
        <input name="command" type="hidden" value="updatePassword">
        <input name="targetId" type="hidden" value="${sessionScope.userId}">
        <label for="password">
          <f:message key="label.oldPassword" />
        </label>
        <input name="password" type="password" required minlength="6">
        <label for="newPassword">
          <f:message key="label.newPassword" />
        </label>
        <input name="newPassword" type="password" required minlength="6">
        <label for="repeatPassword">
          <f:message key="label.repeatPassword" />
        </label>
        <input name="repeatPassword" type="password" required minlength="6">
        <button type="submit">
          <f:message key="label.change" />
        </button>
      </form>

      <button id="updatePasButton">
        <f:message key="label.updatePassword" />
      </button>

      <p>
        <f:message key="label.cards" />
      </p>

      <c:if test="${card !=null}">
        <div class="cardinfo">
          <p>
            ID:
            <c:out value="${card.cardId}" />
          </p>
          <p>
            <f:message key="label.status" />
            <c:out value="${status}" />
          </p>
        </div>
      </c:if>

      <p>
        <f:message key="label.yourLoans" />
      </p>

      <c:if test="${loans !=null}">
        <table>
          <tr>
            <th>
              <f:message key="label.cardId" />
            </th>
            <th>
              <f:message key="label.bookTitle" />
            </th>
            <th>
              <f:message key="label.startDate" />
            </th>
            <th>
              <f:message key="label.status" />
            </th>
            <th>
              <f:message key="label.endDate" />
            </th>
            <th>
              <f:message key="label.options" />
            </th>
          </tr>
          <c:forEach var="loan" items="${loans}" varStatus="loop">
            <tr>
              <td>
                <c:out value="${loan.cardId}" />
              </td>
              <td>
                <c:out value="${bookTitles[loop.index]}" />
              </td>
              <td>
                <c:out value="${loan.startDate}" />
              </td>
              <td>
                <c:out value="${loanStatuses[loop.index]}" />
              </td>
              <td>
                <c:out value="${loan.endDate}" />
              </td>
              <td>
                <c:if test="${loanStatuses[loop.index] == 'резервация'}">
                  <form action="controller" method="POST">
                    <input name="command" type="hidden" value="updateLease">
                    <input name="status" type="hidden" value="отклонён">
                    <input name="targetId" type="hidden" value="${loan.loanId}">
                    <button type="submit">
                      <f:message key="label.cancel" />
                    </button>
                  </form>
                </c:if>
              </td>
            </tr>
          </c:forEach>
        </table>
      </c:if>
    </div>
    <div class="side">
    </div>
  </div>
  <footer>
    <%@include file="common\footer.jsp" %>
  </footer>
</body>

<script type="text/javascript">
  var button1 = document.getElementById('updatePasButton');

  button1.onclick = function () {
    var div = document.getElementById('passwordForm');
    if (div.style.display !== 'none') {
      div.style.display = 'none';
    }
    else {
      div.style.display = 'flex';
    }
  };

  var button2 = document.getElementById('updateEmailButton');

  button2.onclick = function () {
    var div = document.getElementById('emailForm');
    if (div.style.display !== 'none') {
      div.style.display = 'none';
    }
    else {
      div.style.display = 'flex';
    }
  };
</script>

</html>