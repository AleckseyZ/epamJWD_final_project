package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;
import by.epamtc.zotov.finalproject.controller.atribute.PagePaths;
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
        List<Book> books = null;
        String searchString = request.getParameter(JSPAtributes.QUERY);
        String criteria = request.getParameter(JSPAtributes.QUERY_CRITERIA);
        if (searchString != null && criteria != null) {
            try {
                switch (criteria) {
                    case JSPAtributes.TITLE:
                        books = ServiceFactory.getBookService().findBooksByTitle(searchString);
                        break;
                    case JSPAtributes.AUTHOR:
                        books = ServiceFactory.getBookService().findBooksByAuthor(searchString);
                        break;
                    case JSPAtributes.TAGS:
                        books = ServiceFactory.getBookService().findBooksByTags(searchString);
                        break;
                }
            } catch (ServiceException e) {
                logger.error("Exception while searching for books", e);
            }
        }

        request.setAttribute(JSPAtributes.BOOKS, books);
        request.getRequestDispatcher(PagePaths.CATALOGUE).forward(request, response);
    }
}