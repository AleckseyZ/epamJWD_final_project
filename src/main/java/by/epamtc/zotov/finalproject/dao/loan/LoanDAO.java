package by.epamtc.zotov.finalproject.dao.loan;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Loan;
import by.epamtc.zotov.finalproject.exception.DAOException;

/**
 * Defines methods related to working with loans in the datasource.
 */
public interface LoanDAO {
    /**
     * Retrives a list of all loans in the datasource.
     */
    public List<Loan> getAllLoans() throws DAOException;

    /**
     * Retrives a list of all loans with specified status id.
     */
    public List<Loan> findLoansByStatus(int statusId) throws DAOException;

    /**
     * Retrives a list of all loans with specified card id.
     */
    public List<Loan> findLoansByCardId(int targetId) throws DAOException;

    /**
     * Searches datasource for a loan with specified loan ID.
     * 
     * @return Loan with specified loan ID or null if no such loan exist
     */
    public Loan findLoanById(int targetId) throws DAOException;

    /**
     * Counts books of specified ID in the datasource that are being leased.
     * 
     * @return number of leased books of specified ID or -1 if there is no books
     *         with specified ID in the datasource
     */
    public int findLeasedBooksNumber(int targetId) throws DAOException;

    /**
     * Searches datasource for the status ID of specified status
     * 
     * @return ID of specified status or 0 if no such status exist in the datasource
     */
    public int findStatusIdByStatusName(String statusName) throws DAOException;

    /**
     * Searches datasource for the status name of specified status ID
     * 
     * @return name of specified status or 0 if no such status exist in the
     *         datasource
     */
    public String findStatusNameByStatusId(int statusId) throws DAOException;

    /**
     * Updates loan in the datasource
     * 
     * @return whether operation was successful or not
     */
    public boolean updateLoan(Loan loan) throws DAOException;

    /**
     * Ads loan to the datasource
     * 
     * @return whether operation was successful or not
     */
    public boolean addLoan(Loan loan) throws DAOException;
}