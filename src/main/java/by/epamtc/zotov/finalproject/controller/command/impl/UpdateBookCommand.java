package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import by.epamtc.zotov.finalproject.controller.Validator;
import by.epamtc.zotov.finalproject.controller.atribute.CommandPaths;
import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UpdateBookCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccessful = false;
        String targetId = request.getParameter(JSPAtributes.TARGET_ID);
        String title = request.getParameter(JSPAtributes.TITLE);
        String author = request.getParameter(JSPAtributes.AUTHOR);
        String blurb = request.getParameter(JSPAtributes.BLURB);
        String amount = request.getParameter(JSPAtributes.AMOUNT);
        String tags = request.getParameter(JSPAtributes.TAGS);
        String originalCover = request.getParameter(JSPAtributes.ORIGINAL_COVER);
        Part cover = request.getPart(JSPAtributes.COVER);

        if (Validator.getInstance().validateNumber(targetId) && Validator.getInstance().validateNumber(amount)
                && title != null && author != null && blurb != null && originalCover != null) {
            try {
                if (ServiceFactory.getInstance().getBookService().updateBook(Integer.parseInt(targetId), title, author, blurb,
                        Integer.parseInt(amount), tags, originalCover, cover)) {
                    isSuccessful = true;
                }
            } catch (ServiceException e) {
                logger.error("Exception while updating book", e);
            }
        }

        if (!isSuccessful) {
            request.getSession().setAttribute(JSPAtributes.FAILURE, true);
        }
        response.sendRedirect(CommandPaths.CATALOGUE);
    }
}
