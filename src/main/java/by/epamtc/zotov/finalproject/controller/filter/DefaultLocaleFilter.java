package by.epamtc.zotov.finalproject.controller.filter;

import javax.servlet.Filter;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DefaultLocaleFilter implements Filter {
    public static final String DEFAULT_LOCALE = "ru";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();

        if (session.getAttribute("locale") == null) {
            session.setAttribute("locale", DEFAULT_LOCALE);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
