<nav>    
            <a href="controller?command=homepage"><f:message key="locale.homepage"/></a>
            <a href="controller?command=catalogue"><f:message key="locale.catalogue"/></a>
            <c:if test = "${sessionScope.role == null}">
                <a href="controller?command=logInPage"><f:message key="locale.login"/></a>
                <a href="controller?command=signUpPage"><f:message key="locale.signup"/></a>
            </c:if>
            <c:if test = "${sessionScope.role != null}">
                <a href="controller?command=goToAccount"><f:message key="locale.account"/></a>
                <a href="controller?command=logOut"><f:message key="locale.logout"/></a>
            </c:if>
            <c:if test = "${sessionScope.role == 'admin'}">
                <a href="controller?command=goToAdminMenu"><f:message key="locale.administrating"/></a>
            </c:if>
            <c:if test = "${sessionScope.role == 'librarian'}">
                <a href="controller?command=goToLibrarianMenu"><f:message key="locale.librarianMenu"/></a>
            </c:if>
            <button><f:message key="locale.ru"/></button>
            <button><f:message key="locale.en"/></button>
</nav>