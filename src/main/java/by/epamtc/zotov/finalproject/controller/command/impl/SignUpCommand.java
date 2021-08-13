package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.RedirectPath;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            if (ServiceFactory.getUserService().addUser(request.getParameter("username"),
                    request.getParameter("password").toCharArray(), request.getParameter("email"))) {
                response.sendRedirect(RedirectPath.HOME_PAGE_REDIRECT);
            } else {
                response.sendRedirect(RedirectPath.SIGNUP_PAGE_REDIRECT);
            }
        } catch (ServiceException e) {
            logger.error("Exception while adding user", e);
        } 
    }
}