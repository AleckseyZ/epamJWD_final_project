package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.PagePath;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.entity.Book;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class CatalogueCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = null;
        List<String> tags = new ArrayList<>();

        try {
            books = ServiceFactory.getBookService().getAllBooks();
            request.setAttribute("books", books);

            for (Book book : books) {
                String tagString = ServiceFactory.getTagService().getTagStringByBookId(book.getBookId());
                tags.add(tagString);
            }

            request.setAttribute("tags", tags);
        } catch (ServiceException e) {
            logger.error("Exception while retriving list of books", e);
        }
        request.getRequestDispatcher(PagePath.CATALOGUE_PATH).forward(request, response);
    }
}