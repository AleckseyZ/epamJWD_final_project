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

public class SearchCatalogueCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchString = request.getParameter("search");
        List<Book> books = new ArrayList<>();
        List<String> tags = new ArrayList<>();

        try {
            switch (request.getParameter("criteria")) {
                case "title":
                    books = ServiceFactory.getBookService().findBooksByTitle(searchString);
                    break;
                case "author":
                    books = ServiceFactory.getBookService().findBooksByAuthor(searchString);
                    break;
                case "tags":
                    books = ServiceFactory.getBookService().findBooksByTags(searchString);
                    break;
            }
        } catch (ServiceException e) {
            logger.error("Exception while searching for books", e);
        }
        request.setAttribute("books", books);

        try {
            for (Book book : books) {
                String tagString = ServiceFactory.getTagService().getTagStringByBookId(book.getBookId());
                tags.add(tagString);
            }
        } catch (ServiceException e) {
            logger.error("Exception while retriving tags", e);
        }
        request.setAttribute("tags", tags);

        request.getRequestDispatcher(PagePath.CATALOGUE_PATH).forward(request, response);
    }
}