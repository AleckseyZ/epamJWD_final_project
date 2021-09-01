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

public class CatalogueCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = null;

        try {
            books = ServiceFactory.getInstance().getBookService().getAllBooks();
            
            for (int i = 0; i < books.size(); i++) {
                Book book = books.get(i);
                book.setAmount(ServiceFactory.getInstance().getBookService().findAvailable(book.getBookId()));
            }

            request.setAttribute(JSPAtributes.BOOKS, books);
        } catch (ServiceException e) {
            logger.error("Exception while retriving list of books", e);
        }
        request.getRequestDispatcher(PagePaths.CATALOGUE).forward(request, response);
    }
}