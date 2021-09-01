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
import by.epamtc.zotov.finalproject.entity.Loan;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;
import by.epamtc.zotov.finalproject.service.loan.LoanService;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class FindLoansCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Loan> loans = null;
        List<String> bookTitles = null;
        List<String> loanStatuses = null;

        String criteria = request.getParameter(JSPAtributes.QUERY_CRITERIA);

        if (criteria != null) {
            LoanService loanService = ServiceFactory.getInstance().getLoanService();

            try {
                loans = loanService.findLoansByStatus(criteria);
            } catch (ServiceException e) {
                logger.error("Exception while searching for loans by status", e);
            }

            if (loans != null) {
                bookTitles = new ArrayList<String>();
                loanStatuses = new ArrayList<String>();
                for (Loan loan : loans) {
                    try {
                        loanStatuses.add(loanService.findStatusNameByStatusId(loan.getStatusId()));
                    } catch (ServiceException e) {
                        logger.error("Exception while retriving status name", e);
                    }

                    try {
                        bookTitles.add(ServiceFactory.getInstance().getBookService().findBookById(loan.getBookId()).getTitle());
                    } catch (ServiceException e) {
                        logger.error("Exception while retriving status name", e);
                    }
                }
            }

            request.setAttribute("loans", loans);
            request.setAttribute("bookTitles", bookTitles);
            request.setAttribute("loanStatuses", loanStatuses);
        }

        request.getRequestDispatcher(PagePaths.LOAN_MENU).forward(request, response);
    }
}