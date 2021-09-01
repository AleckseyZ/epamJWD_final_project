package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.Validator;
import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;
import by.epamtc.zotov.finalproject.controller.atribute.PagePaths;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.entity.Card;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class FindCardCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String targetId = request.getParameter(JSPAtributes.TARGET_ID);

        if (Validator.getInstance().validateNumber(targetId)) {
            try {
                Card card = ServiceFactory.getInstance().getCardService().findCardById(Integer.parseInt(targetId));
                if (card != null) {
                    request.setAttribute(JSPAtributes.CARD, card);
                    request.setAttribute(JSPAtributes.STATUS,
                            ServiceFactory.getInstance().getCardService().findStatusByStatusId(card.getStatusId()));
                    request.setAttribute(JSPAtributes.USERNAME,
                            ServiceFactory.getInstance().getUserService().findUserById(card.getUserId()).getUsername());
                }
            } catch (ServiceException e) {
                logger.error("Exception while searching for card by id", e);
            }
        }

        request.getRequestDispatcher(PagePaths.CARD_MENU).forward(request, response);
    }
}