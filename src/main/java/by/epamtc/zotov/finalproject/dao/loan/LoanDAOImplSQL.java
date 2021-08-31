package by.epamtc.zotov.finalproject.dao.loan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.zotov.finalproject.dao.connection.ConnectionPool;
import by.epamtc.zotov.finalproject.entity.Loan;
import by.epamtc.zotov.finalproject.exception.DAOException;

public class LoanDAOImplSQL implements LoanDAO {
    private static final String GET_ALL = "SELECT loan_id, book_id, card_id, status_id, start_date, end_date FROM loans ORDER BY start_date";
    private static final String FIND_LOANS_BY_STATUS = "SELECT loan_id, book_id, card_id, status_id, start_date, end_date FROM loans WHERE status_id=? ORDER BY start_date";
    private static final String FIND_LOANS_BY_CARD = "SELECT loan_id, book_id, card_id, status_id, start_date, end_date FROM loans WHERE card_id=? ORDER BY start_date";
    private static final String FIND_LEASED_BOOK_COUNT = "SELECT COUNT(loan_id) as leased_count FROM loans WHERE book_id = ? AND status_id IN (1,3,4)";
    private static final String UPDATE_LOAN = "UPDATE loans SET book_id=?, card_id=?, status_id=?, end_date=? WHERE loan_id=?";
    private static final String ADD_LOAN = "INSERT INTO loans(book_id, card_id, status_id, start_date) VALUES (?, ?, ?, ?)";
    private static final String FIND_STATUS_ID_BY_STATUS_NAME = "SELECT status_id FROM loan_statuses WHERE status_name=?";
    private static final String FIND_STATUS_NAME_BY_STATUS_ID = "SELECT status_name FROM loan_statuses WHERE status_id=?";
    private static final String FIND_LOAN_BY_LOAN_ID = "SELECT loan_id, book_id, card_id, status_id, start_date, end_date FROM loans where loan_id=?";

    @Override
    public List<Loan> getAllLoans() throws DAOException {
        List<Loan> loans = new ArrayList<>();
        Connection connection = null;
        
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(GET_ALL);

            while (result.next()) {
                loans.add(new Loan(result.getInt("loan_id"), result.getInt("book_id"), result.getInt("card_id"),
                        result.getInt("status_id"), result.getDate("start_date"), result.getDate("end_date")));
            }

        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return loans;
    }

    @Override
    public boolean addLoan(Loan loan) throws DAOException {
        boolean isSuccesfull = false;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_LOAN);
            preparedStatement.setInt(1, loan.getBookId());
            preparedStatement.setInt(2, loan.getCardId());
            preparedStatement.setInt(3, loan.getStatusId());
            preparedStatement.setDate(4, loan.getStartDate());
            if (preparedStatement.executeUpdate() != 0) {
                isSuccesfull = true;
            }
        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return isSuccesfull;
    }

    @Override
    public boolean updateLoan(Loan loan) throws DAOException {
        boolean isSuccesfull = false;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LOAN);
            preparedStatement.setInt(1, loan.getBookId());
            preparedStatement.setInt(2, loan.getCardId());
            preparedStatement.setInt(3, loan.getStatusId());
            preparedStatement.setDate(4, loan.getEndDate());
            preparedStatement.setInt(5, loan.getLoanId());
            if (preparedStatement.executeUpdate() != 0) {
                isSuccesfull = true;
            }
        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return isSuccesfull;
    }

    @Override
    public int findLeasedBooksNumber(int targetId) throws DAOException {
        int leased = -1;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_LEASED_BOOK_COUNT);
            preparedStatement.setInt(1, targetId);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                leased = result.getInt("leased_count");
            }
        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return leased;
    }

    @Override
    public List<Loan> findLoansByCardId(int targetId) throws DAOException {
        List<Loan> loans = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_LOANS_BY_CARD);
            preparedStatement.setInt(1, targetId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                loans.add(new Loan(result.getInt("loan_id"), result.getInt("book_id"), result.getInt("card_id"),
                        result.getInt("status_id"), result.getDate("start_date"), result.getDate("end_date")));
            }

        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return loans;
    }

    @Override
    public List<Loan> findLoansByStatus(int statusId) throws DAOException {
        List<Loan> loans = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_LOANS_BY_STATUS);
            preparedStatement.setInt(1, statusId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                loans.add(new Loan(result.getInt("loan_id"), result.getInt("book_id"), result.getInt("card_id"),
                        result.getInt("status_id"), result.getDate("start_date"), result.getDate("end_date")));
            }

        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return loans;
    }

    @Override
    public int findStatusIdByStatusName(String statusName) throws DAOException {
        int statusId = 0;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_STATUS_ID_BY_STATUS_NAME);
            preparedStatement.setString(1, statusName);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                statusId = result.getInt("status_id");
            }
        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return statusId;
    }

    @Override
    public String findStatusNameByStatusId(int statusId) throws DAOException {
        String statusName = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_STATUS_NAME_BY_STATUS_ID);
            preparedStatement.setInt(1, statusId);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                statusName = result.getString("status_name");
            }
        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return statusName;
    }

    @Override
    public Loan findLoanById(int targetId) throws DAOException {
        Loan targetLoan = null;

        Connection connection = null;
        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_LOAN_BY_LOAN_ID);
            preparedStatement.setInt(1, targetId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                targetLoan = new Loan(result.getInt("loan_id"), result.getInt("book_id"), result.getInt("card_id"),
                        result.getInt("status_id"), result.getDate("start_date"), result.getDate("end_date"));
            }

        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return targetLoan;
    }
}