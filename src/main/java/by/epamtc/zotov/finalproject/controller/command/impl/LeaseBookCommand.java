package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.Validator;
import by.epamtc.zotov.finalproject.controller.atribute.CommandPaths;
import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;
import by.epamtc.zotov.finalproject.service.loan.LoanService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class LeaseBookCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoanService loanService = ServiceFactory.getLoanService();
        String bookId = request.getParameter(JSPAtributes.BOOK_ID);
        String cardId = request.getParameter(JSPAtributes.CARD_ID);
        String status = request.getParameter(JSPAtributes.STATUS);
        boolean isSuccessful = false;
        if (Validator.getInstance().validateNumber(bookId) && Validator.getInstance().validateNumber(cardId)
                && status != null) {
            try {
                if (loanService.addLoan(bookId, cardId, status)) {
                    isSuccessful = true;
                }
            } catch (ServiceException e) {
                logger.error("Exception while adding loan", e);
            }
        }
        if(!isSuccessful) {
            request.getSession().setAttribute(JSPAtributes.FAILURE, true);
        }
        response.sendRedirect(CommandPaths.CATALOGUE);
    }
}