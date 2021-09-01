package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;
import by.epamtc.zotov.finalproject.controller.atribute.PagePaths;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.entity.User;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;
import by.epamtc.zotov.finalproject.service.user.UserService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class FindUserCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = ServiceFactory.getInstance().getUserService();

        try {
            User user = userService.findUserByUsername(request.getParameter(JSPAtributes.USERNAME));
            if (user != null) {
                request.setAttribute(JSPAtributes.USER, user);
                request.setAttribute(JSPAtributes.USER_TYPE, userService.findUserTypeByUsername(user.getUsername()));
                request.setAttribute(JSPAtributes.USER_TYPE_OPTIONS, userService.getUserTypes());
            }
        } catch (ServiceException e) {
            logger.error("Exception while searching for users", e);
        }
        request.getRequestDispatcher(PagePaths.ADMIN_MENU).forward(request, response);
    }
}