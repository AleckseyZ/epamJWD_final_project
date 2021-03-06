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
            <h1>
                <f:message key="label.loanMenu" />
            </h1>

            <c:if test="${failure != null}">
                <f:message key="label.genericFailureMsg" />
                <c:remove var="failure" />
            </c:if>


            <form action="controller" method="GET">
                <input type="hidden" name="command" value="findLoans" required>
                <label for="cardId">
                    <f:message key="label.cardId" />
                </label>
                <input type="number" name="cardId" value="cardId">
                <button type="submit">
                    <f:message key="label.search" />
                </button>
            </form>

            <a href="controller?command=findLoans&criteria=резервация">
                <f:message key="label.findReserved" />
            </a>

            <a href="controller?command=findLoans&criteria=активен">
                <f:message key="label.findActive" />
            </a>

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
                            <td><c:out value="${loan.cardId}"/></td>
                            <td><c:out value="${bookTitles[loop.index]}"/></td>
                            <td><c:out value="${loan.startDate}"/></td>
                            <td><c:out value="${loanStatuses[loop.index]}"/></td>
                            <td><c:out value="${loan.endDate}"/></td>
                            <td>
                                <c:if test="${loanStatuses[loop.index] == 'активен'}">
                                    <form action="controller" method="POST">
                                        <input name="command" type="hidden" value="updateLease">
                                        <input name="status" type="hidden" value="завершён">
                                        <input name="targetId" type="hidden" value="${loan.loanId}">
                                        <button type="submit">
                                            <f:message key="label.closeLease" />
                                        </button>
                                    </form>
                                    <form action="controller" method="POST">
                                        <input name="command" type="hidden" value="updateLease">
                                        <input name="status" type="hidden" value="просрочен">
                                        <input name="targetId" type="hidden" value="${loan.loanId}">
                                        <button type="submit">
                                            <f:message key="label.overdueLease" />
                                        </button>
                                    </form>
                                </c:if>
                                <c:if test="${loanStatuses[loop.index] == 'резервация'}">
                                    <form action="controller" method="POST">
                                        <input name="command" type="hidden" value="updateLease">
                                        <input name="status" type="hidden" value="отклонён">
                                        <input name="targetId" type="hidden" value="${loan.loanId}">
                                        <button type="submit">
                                            <f:message key="label.rejectLease" />
                                        </button>
                                    </form>
                                    <form action="controller" method="POST">
                                        <input name="command" type="hidden" value="updateLease">
                                        <input name="status" type="hidden" value="активен">
                                        <input name="targetId" type="hidden" value="${loan.loanId}">
                                        <button type="submit">
                                            <f:message key="label.aproveLease" />
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

</html>