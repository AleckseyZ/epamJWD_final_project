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

public class AddNoticeCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccessful = false;
        String title = request.getParameter(JSPAtributes.TITLE);
        String body = request.getParameter(JSPAtributes.BODY);

        if (title != null && body != null && !title.isEmpty()) {

            try {
                if (ServiceFactory.getInstance().getNoticeService().addNotice(title, body)) {
                    isSuccessful = true;
                }
            } catch (ServiceException e) {
                logger.error("Exception while adding notce", e);
            }
        }

        if (isSuccessful) {
            response.sendRedirect(CommandPaths.HOME_PAGE);
        } else {
            request.getSession().setAttribute(JSPAtributes.FAILURE, true);
            response.sendRedirect(CommandPaths.ADD_NOTICE_PAGE);
        }
    }
}