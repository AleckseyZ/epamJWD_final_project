package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.Validator;
import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;
import by.epamtc.zotov.finalproject.controller.atribute.PagePaths;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.entity.Notice;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UpdateNoticePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccessful = false;
        String targetId = request.getParameter(JSPAtributes.TARGET_ID);

        if (Validator.getInstance().validateNumber(targetId)) {
            try {
                Notice target = ServiceFactory.getInstance().getNoticeService().getNoticeById(Integer.parseInt(targetId));

                request.setAttribute(JSPAtributes.TARGET_ID, target.getNoticeId());
                request.setAttribute(JSPAtributes.TITLE, target.getTitleText());
                request.setAttribute(JSPAtributes.BODY, target.getBodyText());
                isSuccessful = true;
            } catch (ServiceException e) {
                logger.error("Exception while retriving notice", e);
            }
        }

        if (isSuccessful) {
            request.getRequestDispatcher(PagePaths.UPDATE_NOTICE_PAGE).forward(request, response);
        } else {
            request.getSession().setAttribute(JSPAtributes.FAILURE, true);
            request.getRequestDispatcher(PagePaths.HOME_PAGE).forward(request, response);
        }

    }
}