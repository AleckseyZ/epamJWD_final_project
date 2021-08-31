package by.epamtc.zotov.finalproject.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epamtc.zotov.finalproject.controller.atribute.JSPAtributes;
import by.epamtc.zotov.finalproject.controller.atribute.PagePaths;
import by.epamtc.zotov.finalproject.controller.command.Command;
import by.epamtc.zotov.finalproject.entity.Card;
import by.epamtc.zotov.finalproject.entity.Loan;
import by.epamtc.zotov.finalproject.entity.User;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AccountPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = null;
        Card card = null;
        String status = null;
        List<Loan> loans = null;
        List<String> loanStatuses = null;
        List<String> bookTitles = null;

        try {
            user = ServiceFactory.getUserService()
                    .findUserById(Integer.parseInt(request.getSession().getAttribute(JSPAtributes.USER_ID).toString()));
            request.setAttribute(JSPAtributes.USER, user);
        } catch (ServiceException e) {
            logger.error("Exception while retriving user by id", e);
        }

        try {
            card = ServiceFactory.getCardService().findCardByUserId(
                    Integer.parseInt(request.getSession().getAttribute(JSPAtributes.USER_ID).toString()));
            request.setAttribute(JSPAtributes.CARD, card);
        } catch (ServiceException e) {
            logger.error("Exception while retriving card by user id", e);
        }

        if (card != null) {
            try {
                status = ServiceFactory.getCardService().findStatusByStatusId(card.getCardId());
                request.setAttribute(JSPAtributes.STATUS, status);
            } catch (ServiceException e) {
                logger.error("Exception while retriving card by user id", e);
            }

            try {
                loans = ServiceFactory.getLoanService().findLoansByCardId(card.getCardId());
                request.setAttribute(JSPAtributes.LOANS, loans);
                loanStatuses = new ArrayList<>();
                bookTitles = new ArrayList<>();

                for (Loan loan : loans) {
                    loanStatuses.add(ServiceFactory.getLoanService().findStatusNameByStatusId(loan.getStatusId()));
                    bookTitles.add(ServiceFactory.getBookService().findBookById(loan.getBookId()).getTitle());
                }
                request.setAttribute(JSPAtributes.LOAN_STATUSES, loanStatuses);
                request.setAttribute(JSPAtributes.BOOK_TITLES, bookTitles);
                
            } catch (ServiceException e) {
                logger.error("Exception while retriving user's loans", e);
            }
        }

        request.getRequestDispatcher(PagePaths.PROFILE).forward(request, response);
    }
}