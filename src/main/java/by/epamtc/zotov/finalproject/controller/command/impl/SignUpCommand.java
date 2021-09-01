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

public class SignUpCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccessful = false;
        boolean isUsernameTaken = false;
        String username = request.getParameter(JSPAtributes.USERNAME);
        String password = request.getParameter(JSPAtributes.PASSWORD);
        String repeatPassword = request.getParameter(JSPAtributes.REPEAT_PASSWORD);
        String email = request.getParameter(JSPAtributes.EMAIL);
        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            if (username != null && Validator.getInstance().validatePassword(password)
                    && password.equals(repeatPassword) && Validator.getInstance().validateEmail(email)) {

                if (userService.findUserByUsername(username) != null) {
                    isUsernameTaken = true;
                } else {
                    if (userService.addUser(username, password.toCharArray(), email)) {
                        isSuccessful = true;
                    }
                }
            }
        } catch (ServiceException e) {
            logger.error("Exception while adding user", e);
        }

        if (isSuccessful) {
            response.sendRedirect(CommandPaths.HOME_PAGE);
        } else {
            if (isUsernameTaken) {
                request.getSession().setAttribute(JSPAtributes.USERNAME_TAKEN, true);
            } else {
                request.getSession().setAttribute(JSPAtributes.FAILURE, true);
            }
            response.sendRedirect(CommandPaths.SIGNUP_PAGE);
        }
    }
}