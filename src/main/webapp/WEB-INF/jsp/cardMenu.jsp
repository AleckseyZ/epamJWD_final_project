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
                <f:message key="label.cardMenu" />
            </h1>

            <form action="controller" method="GET">
                <input type="hidden" name="command" value="findCard" required>
                <label for="targetId">
                    <f:message key="label.cardId" />
                </label>
                <input type=text name="targetId" required>
                <button type="submit">
                    <f:message key="label.search" />
                </button>
            </form>

            <a href="controller?command=addCardPage">
                <f:message key="label.addCard" />
            </a>

            <c:if test="${card !=null}">
                <div>
                    <p>
                        <c:out value="${card.cardId}" />
                    </p>
                    <p>
                        <c:out value="${username}" />
                    </p>
                    <p>
                        <c:out value="${card.holderName}" />
                    </p>
                    <p>
                        <c:out value="${status}" />
                    </p>
                    <form action="controller" method="POST">
                        <input name="command" type="hidden" value="blockCard">
                        <input name="targetId" type="hidden" value="${card.cardId}">
                        <button type="submit">
                            <f:message key="label.block" />
                        </button>
                    </form>
                </div>
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