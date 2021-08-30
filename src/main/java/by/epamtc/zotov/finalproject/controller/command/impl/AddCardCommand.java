package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.atribute.CommandPaths;
import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AddCardCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccessful = false;
        String username = request.getParameter(JSPAtributes.USERNAME);
        String holder = request.getParameter(JSPAtributes.HOLDER);

        if (username != null && holder != null) {
            try {
                if (ServiceFactory.getCardService().addCard(username, holder)) {
                    isSuccessful = true;
                }
            } catch (ServiceException e) {
                logger.error("Exception while adding book", e);
            }
        }

        if (isSuccessful) {
            response.sendRedirect(CommandPaths.CARD_MENU);
        } else {
            request.getSession().setAttribute(JSPAtributes.FAILURE, true);
            response.sendRedirect(CommandPaths.ADD_CARD_PAGE);
        }
    }
}