package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;
import by.epamtc.zotov.finalproject.controller.atribute.PagePaths;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.entity.Card;
import by.epamtc.zotov.finalproject.entity.User;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AccountPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        Card card = null;
        String status = null;

        try {
            user = ServiceFactory.getUserService()
                    .findUserById(Integer.parseInt(request.getSession().getAttribute(JSPAtributes.USER_ID).toString()));
        } catch (ServiceException e) {
            logger.error("Exception while retriving user by id", e);
        }

        request.setAttribute("user", user);

        try {
            card = ServiceFactory.getCardService().findCardByUserId(
                    Integer.parseInt(request.getSession().getAttribute(JSPAtributes.USER_ID).toString()));
        } catch (ServiceException e) {
            logger.error("Exception while retriving card by user id", e);
        }

        request.setAttribute("card", card);

        try {
            status = ServiceFactory.getCardService().findStatusByStatusId(card.getCardId());
        } catch (ServiceException e) {
            logger.error("Exception while retriving card by user id", e);
        }

        request.setAttribute(JSPAtributes.STATUS, status);
        request.getRequestDispatcher(PagePaths.PROFILE).forward(request, response);
    }
}