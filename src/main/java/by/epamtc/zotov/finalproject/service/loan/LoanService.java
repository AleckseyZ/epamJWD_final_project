package by.epamtc.zotov.finalproject.service.loan;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Loan;
import by.epamtc.zotov.finalproject.exception.ServiceException;

/**
 * Defines service-layer methods related to loans.
 */
public interface LoanService {
    /**
     * Calls relevant DAO-layer method to query data source.
     * 
     * @return list of all loans
     * @throws ServiceException
     */
    public List<Loan> getAllLoans() throws ServiceException;

    /**
     * Queries datasource to count number of book's with certain ID that are currently being leased.
     * @param targetId ID for book to count leased
     * @return number of currently leased books with target ID
     * @throws ServiceException
     */
    public int findLeasedBookNumber(int targetId) throws ServiceException;

    /**
     * Searches data source for loan objects with certain status.
     * @param status desired status
     * @return List of loans with desired status
     * @throws ServiceException
     */
    public List<Loan> findLoansByStatus(String status) throws ServiceException;
/**
     * Searches data source for loans taken on card with certain card ID.
     * @param cardId desired card ID
     * @return List of loans with desired card ID
     * @throws ServiceException
     */
    public List<Loan> findLoansByCardId(int cardId) throws ServiceException;

    /**
     * Updates loan object with certain ID in the datasource.
     * @param targetId ID of loan to update
     * @param status New loan status
     * @return Whether operation was successful or not
     * @throws ServiceException
     */
    public boolean updateLoan(String targetId, String status) throws ServiceException;

    /**
     * Creates new loan object and inserts it into data source.
     * @param bookId ID of book that is beaing leased
     * @param cardId ID of card that takes the loan
     * @param status Starting status of loan
     * @return Whether operation was successful or not
     * @throws ServiceException
     */
    public boolean addLoan(String bookId, String cardId, String status) throws ServiceException;

    /**
     * Searches data source for loan status name by loan status id.
     * @param statusId ID of desired status
     * @return String containing name of desired status
     * @throws ServiceException
     */
    public String findStatusNameByStatusId(int statusId) throws ServiceException;
}