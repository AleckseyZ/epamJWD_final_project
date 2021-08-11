<nav>    
            <a href="controller?command=homePage"><f:message key="label.homepage"/></a>
            <a href="controller?command=catalogue"><f:message key="label.catalogue"/></a>
            <c:if test = "${sessionScope.role == null}">
                <a href="controller?command=logInPage"><f:message key="label.login"/></a>
                <a href="controller?command=signUpPage"><f:message key="label.signup"/></a>
            </c:if>
            <c:if test = "${sessionScope.role != null}">
                <a href="controller?command=goToAccount"><f:message key="label.account"/></a>
                <a href="controller?command=logOut"><f:message key="label.logout"/></a>
            </c:if>
            <c:if test = "${sessionScope.role == 'admin'}">
                <a href="controller?command=goToAdminMenu"><f:message key="label.administrating"/></a>
            </c:if>
            <c:if test = "${sessionScope.role == 'librarian'}">
                <a href="controller?command=goToLibrarianMenu"><f:message key="label.librarianMenu"/></a>
            </c:if>
            <button><f:message key="label.ru"/></button>
            <button><f:message key="label.en"/></button>
</nav>