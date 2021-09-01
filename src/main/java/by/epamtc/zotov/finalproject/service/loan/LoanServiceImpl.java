package by.epamtc.zotov.finalproject.service.loan;

import java.sql.Date;
import java.util.List;

import by.epamtc.zotov.finalproject.dao.DAOFactory;
import by.epamtc.zotov.finalproject.dao.loan.LoanDAO;
import by.epamtc.zotov.finalproject.entity.Card;
import by.epamtc.zotov.finalproject.entity.Loan;
import by.epamtc.zotov.finalproject.exception.DAOException;
import by.epamtc.zotov.finalproject.exception.ServiceException;
import by.epamtc.zotov.finalproject.service.ServiceFactory;

public class LoanServiceImpl implements LoanService {
    private static final String DONE_STATUS = "завершён";
    private static final String REJECT_STATUS = "отклонён";
    private static final int BLOCKED_CARD_ID = 2;

    @Override
    public List<Loan> getAllLoans() throws ServiceException {
        List<Loan> loans = null;

        try {
            loans = DAOFactory.getInstance().getLoanDAO().getAllLoans();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return loans;
    }

    @Override
    public boolean addLoan(String bookId, String cardId, String status) throws ServiceException {
        boolean isSuccesfull = false;
        int statusId = findStatusIdByStatusName(status);
        int parsedBookId = Integer.parseInt(bookId);
        int parsedCardId = Integer.parseInt(cardId);

        try {
            if (ServiceFactory.getInstance().getBookService().findAvailable(parsedBookId) > 0) {
                Card card = ServiceFactory.getInstance().getCardService().findCardById(parsedCardId);
                if (card != null && card.getStatusId() != BLOCKED_CARD_ID) {

                    Loan loan = new Loan(parsedBookId, parsedCardId, statusId, new Date(System.currentTimeMillis()));
                    isSuccesfull = DAOFactory.getInstance().getLoanDAO().addLoan(loan);
                }
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return isSuccesfull;
    }

    @Override
    public boolean updateLoan(String targetId, String status) throws ServiceException {
        boolean isSuccesfull = false;
        int parsedTargetId = Integer.parseInt(targetId);
        int statusId = findStatusIdByStatusName(status);

        LoanDAO loanDAO = DAOFactory.getInstance().getLoanDAO();
        try {
            Loan loan = loanDAO.findLoanById(parsedTargetId);
            if (status.equals(DONE_STATUS) || status.equals(REJECT_STATUS)) {
                loan.setEndDate(new Date(System.currentTimeMillis()));
            }
            loan.setStatusId(statusId);
            isSuccesfull = loanDAO.updateLoan(loan);

        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return isSuccesfull;
    }

    @Override
    public List<Loan> findLoansByStatus(String status) throws ServiceException {
        List<Loan> loans = null;
        int statusId = findStatusIdByStatusName(status);

        try {
            loans = DAOFactory.getInstance().getLoanDAO().findLoansByStatus(statusId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return loans;
    }

    private int findStatusIdByStatusName(String statusName) throws ServiceException {
        int statusId;

        try {
            statusId = DAOFactory.getInstance().getLoanDAO().findStatusIdByStatusName(statusName);
        } catch (DAOException e) {
            throw new ServiceException("Exception while retriving statud id by status name", e);
        }

        return statusId;
    }

    public String findStatusNameByStatusId(int statusId) throws ServiceException {
        String statusName;

        try {
            statusName = DAOFactory.getInstance().getLoanDAO().findStatusNameByStatusId(statusId);
        } catch (DAOException e) {
            throw new ServiceException("Exception while retriving statud id by status name", e);
        }

        return statusName;
    }

    @Override
    public List<Loan> findLoansByCardId(int cardId) throws ServiceException {
        List<Loan> loans = null;

        try {
            loans = DAOFactory.getInstance().getLoanDAO().findLoansByCardId(cardId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return loans;
    }

    @Override
    public int findLeasedBookNumber(int targetId) throws ServiceException {
        int leasedBookNumber;

        try {
            leasedBookNumber = DAOFactory.getInstance().getLoanDAO().findLeasedBooksNumber(targetId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return leasedBookNumber;
    }
}