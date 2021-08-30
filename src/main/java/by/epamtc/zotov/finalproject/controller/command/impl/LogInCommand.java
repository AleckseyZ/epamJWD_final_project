package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.Validator;
import by.epamtc.zotov.finalproject.controller.atribute.CommandPaths;
import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;
import by.epamtc.zotov.finalproject.service.user.UserService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class LogInCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isAuthentic = false;
        boolean isSuccesfull = false;
        String username = request.getParameter(JSPAtributes.USERNAME);
        String password = request.getParameter(JSPAtributes.PASSWORD);

        if (username != null || Validator.getInstance().validatePassword(password)) {
            UserService userService = ServiceFactory.getUserService();

            try {
                isAuthentic = userService.authenticate(username, password.toCharArray());
            } catch (ServiceException e) {
                logger.error("Exception during authentification", e);
            }
            password = null;

            if (isAuthentic) {
                try {
                    request.getSession().setAttribute(JSPAtributes.ROLE, userService.findUserTypeByUsername(username));
                    request.getSession().setAttribute(JSPAtributes.USER_ID, userService.findUserIdByUsername(username));
                    isSuccesfull = true;
                } catch (ServiceException e) {
                    logger.error("Exception while retriving user data", e);
                }
            }
        }
        if (isSuccesfull) {
            response.sendRedirect(CommandPaths.HOME_PAGE);
        } else {
            request.getSession().setAttribute(JSPAtributes.FAILURE, true);
            response.sendRedirect(CommandPaths.LOGIN_PAGE);
        }
    }
}