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
                <f:message key="label.catalogue" />
            </h1>

            <c:if test="${failure != null}">
                <f:message key="label.genericFailureMsg" />
                <c:remove var="failure" />
            </c:if>

            <form action="controller" method="GET">
                <input type="hidden" name="command" value="findBooks" required>
                <input type="text" name="query" required>
                <div>
                    <label for="title">
                        <f:message key="label.titleSearch" />
                    </label>
                    <input type=radio name="criteria" value="title" id="title" checked>
                </div>
                <div>
                    <label for="title">
                        <f:message key="label.authorSearch" />
                    </label>
                    <input type=radio name="criteria" value="author" id="author">
                </div>
                <div>
                    <label for="title">
                        <f:message key="label.tagsSearch" />
                    </label>
                    <input type=radio name="criteria" value="tags" id="tags">
                </div>
                <button type="submit">
                    <f:message key="label.search" />
                </button>
            </form>



            <c:if test="${sessionScope.role == 'библиотекарь'}">
                <a href="controller?command=addBookPage">
                    <f:message key="label.addBook" />
                </a>
            </c:if>

            <c:if test="${books !=null}">
                <c:forEach var="book" items="${books}">
                    <div class="book">
                        <img src="http://localhost:8080/library/img/${book.cover}" alt="Book cover" width="150"
                            height="200">
                        <h4>
                            <c:out value="${book.title}" />
                        </h4>
                        <p>
                            <c:out value="${book.author}" />
                        </p>
                        <p>
                            <c:out value="${book.blurb}" />
                        </p>
                        <p>
                            <c:out value="${book.tags}" />
                        </p>

                        <c:if test="1 > ${book.amount}">
                            <p>
                                <f:message key="label.notAvailable" />
                            </p>
                        </c:if>

                        <c:set var="zero" value="${0}" />

                        <div class="options">
                            <c:if test="${sessionScope.role == 'библиотекарь'}">
                                <a href="controller?command=updateBookPage&targetId=${book.bookId}">
                                    <f:message key="label.editBook" />
                                </a>
                            </c:if>

                            <c:if test="${zero >= book.amount}">
                                <f:message key="label.notAvailable" />
                            </c:if>

                            <c:if test="${book.amount > zero}">
                                <c:if test="${sessionScope.role == 'библиотекарь'}">

                                    <form action="controller" method="POST">
                                        <input name="command" type="hidden" value="leaseBook">
                                        <input name="status" type="hidden" value="активен">
                                        <input name="bookId" type="hidden" value="${book.bookId}">
                                        <label for="cardId">
                                            <f:message key="label.cardId" />
                                        </label>
                                        <input name="cardId" type="number" required>
                                        <button type="submit">
                                            <f:message key="label.leaseBook" />
                                        </button>
                                    </form>
                                </c:if>


                                <c:if test="${sessionScope.role == 'пользователь'}">
                                    <form action="controller" method="POST">
                                        <input name="command" type="hidden" value="leaseBook">
                                        <input name="status" type="hidden" value="резервация">
                                        <input name="bookId" type="hidden" value="${book.bookId}">
                                        <label for="cardId">
                                            <f:message key="label.cardId" />
                                        </label>
                                        <input name="cardId" type="number" required>
                                        <button type="submit">
                                            <f:message key="label.reserveBook" />
                                        </button>
                                    </form>
                                </c:if>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
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