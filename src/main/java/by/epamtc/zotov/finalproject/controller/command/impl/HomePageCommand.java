package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.PagePath;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.entity.Notice;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class HomePageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Notice> notices = null;
        try {
            notices = ServiceFactory.getNoticeService().getAllNotices();
        request.setAttribute("notices", notices);
        } catch (ServiceException e) {
            logger.error("Couldn't get notices",e);
        }
        request.getRequestDispatcher(PagePath.HOME_PAGE_PATH).forward(request, response);
    }
}