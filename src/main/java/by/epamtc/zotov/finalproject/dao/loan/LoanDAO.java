package by.epamtc.zotov.finalproject.dao.loan;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Loan;
import by.epamtc.zotov.finalproject.exception.DAOException;

public interface LoanDAO {
    public List<Loan> getAllLoans() throws DAOException;
    public int findLeasedBooksNumber(int targetId) throws DAOException;
    public List<Loan> findLoansByStatus(int statusId) throws DAOException;
    public List<Loan> findLoansByCardId(int targetId) throws DAOException;
    public Loan findLoanById(int targetId) throws DAOException;
    public int findStatusIdByStatusName(String statusName) throws DAOException;
    public String findStatusNameByStatusId(int statusId) throws DAOException;
    public boolean updateLoan(Loan loan) throws DAOException;
    public boolean addLoan(Loan loan) throws DAOException;
}