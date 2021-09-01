<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<nav>
    <a href="controller?command=homePage">
        <f:message key="label.homepage" />
    </a>
    <a href="controller?command=catalogue">
        <f:message key="label.catalogue" />
    </a>
    <c:if test="${sessionScope.role == null}">
        <a href="controller?command=logInPage">
            <f:message key="label.login" />
        </a>
        <a href="controller?command=signUpPage">
            <f:message key="label.signup" />
        </a>
    </c:if>
    <c:if test="${sessionScope.role != null}">
        <a href="controller?command=accountPage">
            <f:message key="label.account" />
        </a>
        <c:if test="${sessionScope.role == 'администратор'}">
            <a href="controller?command=adminMenu">
                <f:message key="label.adminMenu" />
            </a>
        </c:if>
        <c:if test="${sessionScope.role == 'библиотекарь'}">
            <a href="controller?command=cardMenu">
                <f:message key="label.cardMenu" />
            </a>
            <a href="controller?command=loanMenu">
                <f:message key="label.loanMenu" />
            </a>
        </c:if>
        <a href="controller?command=logOut">
            <f:message key="label.logout" />
        </a>
    </c:if>
    <div class="lang-wrapper">
        <a
            href="controller?command=changeLanguage&lang=ru&page=${requestScope['javax.servlet.forward.request_uri']}?${requestScope['javax.servlet.forward.query_string']}">
            <f:message key="label.ru" />
        </a>
        <a
            href="controller?command=changeLanguage&lang=en&page=${requestScope['javax.servlet.forward.request_uri']}?${requestScope['javax.servlet.forward.query_string']}">
            <f:message key="label.en" />
        </a>
    </div>
</nav>

<img src="${pageContext.request.contextPath}/image/banner.jpg" alt="A row of bookshelfs" class="banner" />