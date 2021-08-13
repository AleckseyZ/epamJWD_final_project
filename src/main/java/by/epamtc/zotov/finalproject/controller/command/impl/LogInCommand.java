package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.PagePath;
import by.epamtc.zotov.finalproject.controller.RedirectPath;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class LogInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAuthentic = false;

        try {
            isAuthentic = ServiceFactory.getUserService().authenticate(request.getParameter("username"),
                    request.getParameter("password").toCharArray());
        } catch (ServiceException e) {
            logger.error("Exception during authentification", e);
        }

        if (isAuthentic) {
            response.sendRedirect(RedirectPath.HOME_PAGE_REDIRECT);
        } else {
            request.setAttribute("failed", true);
            request.getRequestDispatcher(PagePath.LOGIN_PAGE_PATH).forward(request, response);
        }
    }
}