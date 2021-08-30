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

    <h1><f:message key="label.catalogue"/></h1>

    <c:if test = "${failure != null}">
            <f:message key="label.genericFailureMsg"/>
            <c:remove var="failure"/>
    </c:if>

    <form action="controller" method="GET">
        <input type="hidden" name="command" value="findBooks" required>
        <input type="text" name="query" required>
        <label for="title"><f:message key="label.bookTitle"/></label>
        <input type=radio name="criteria" value="title" id="title" checked>
        <label for="title"><f:message key="label.author"/></label>
        <input type=radio name="criteria" value="author" id="author">
        <label for="title"><f:message key="label.tags"/></label>
        <input type=radio name="criteria" value="tags" id="tags">
        <input type="submit">
    </form>

    <c:if test="${sessionScope.role == 'библиотекарь'}">
        <a href="controller?command=addBookPage">
           <f:message key="label.addBook" />
       </a>
   </c:if>

    <c:if test="${books !=null}">
        <c:forEach var="book" items="${books}">
        <div>
            <img src="http://localhost:8080/library/img/${book.cover}" alt="Book cover" width="150" height="200">
            <p>${book.tags}</p>
            <h4>${book.title}</h4>
            <p>${book.author}</p>
            <p>${book.blurb}</p>

            <c:if test="${sessionScope.role == 'библиотекарь'}">
            <a href="controller?command=updateBookPage&targetId=${book.bookId}">
            <f:message key="label.editBook" />
            </a>

            <form action="controller" method="POST">
                <input name="command" type="hidden" value="leaseBook">
                <input name="status" type="hidden" value="активен">
                <input name="bookId" type="hidden" value="${book.bookId}">
                <label for="cardId"><f:message key="label.cardId"/></label>
                <input name="cardId" type="number" required>
                <input type="submit">
            </form>

            </c:if>

            <c:if test="${sessionScope.role == 'пользователь'}">
                <form action="controller" method="POST">
                    <input name="command" type="hidden" value="leaseBook">
                    <input name="status" type="hidden" value="резервация">
                    <input name="bookId" type="hidden" value="${book.bookId}">
                    <label for="cardId"><f:message key="label.cardId"/></label>
                    <input name="cardId" type="number" required>
                    <input type="submit">
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