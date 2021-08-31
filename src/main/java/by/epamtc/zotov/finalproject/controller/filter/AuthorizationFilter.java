package by.epamtc.zotov.finalproject.controller.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.atribute.CommandPaths;
import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;

public class AuthorizationFilter implements Filter {
    private List<String> adminOnlyList;
    private List<String> librarianOnlyList;
    private List<String> atLeastUser;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        adminOnlyList = new ArrayList<String>();
        adminOnlyList.add("adminMenu");
        adminOnlyList.add("findUser");
        adminOnlyList.add("updateRole");

        librarianOnlyList = new ArrayList<String>();
        librarianOnlyList.add("cardMenu");
        librarianOnlyList.add("findCard");
        librarianOnlyList.add("addCardPage");
        librarianOnlyList.add("addCard");
        librarianOnlyList.add("loanMenu");
        librarianOnlyList.add("findLoans");
        librarianOnlyList.add("addNoticePage");
        librarianOnlyList.add("addNotice");
        librarianOnlyList.add("updateNoticePage");
        librarianOnlyList.add("updateNotice");
        librarianOnlyList.add("deleteNotice");
        librarianOnlyList.add("addBookPage");
        librarianOnlyList.add("addBook");
        librarianOnlyList.add("updateBook");

        atLeastUser = new ArrayList<String>();
        atLeastUser.add("accountPage");
        atLeastUser.add("updateMail");
        atLeastUser.add("updatePassword");
        atLeastUser.add("leaseBook");
        atLeastUser.add("updateLease");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        boolean isAuthorized = true;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String command = httpRequest.getParameter(JSPAtributes.COMMAND);
        Object roleObject = httpRequest.getSession().getAttribute(JSPAtributes.ROLE);
        String role = null;

        if (roleObject != null) {
            role = roleObject.toString();
        }

        if ((atLeastUser.contains(command) && role == null)
                || (adminOnlyList.contains(command) && (role == null || !role.equals(JSPAtributes.ADMIN_ROLE)))
                || (librarianOnlyList.contains(command)
                        && (role == null || !role.equals(JSPAtributes.LIBRARIAN_ROLE)))) {
            isAuthorized = false;
        }

        if (isAuthorized) {
            chain.doFilter(request, response);
        } else {
            httpRequest.getSession().setAttribute(JSPAtributes.UNATHORIZED, true);
            HttpServletResponse htppResponse = (HttpServletResponse) response;
            htppResponse.sendRedirect(CommandPaths.HOME_PAGE);
        }
    }
}