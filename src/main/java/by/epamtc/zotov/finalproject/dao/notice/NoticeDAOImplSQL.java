package by.epamtc.zotov.finalproject.dao.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.zotov.finalproject.dao.connection.ConnectionPool;
import by.epamtc.zotov.finalproject.entity.Notice;
import by.epamtc.zotov.finalproject.exception.DAOException;

public class NoticeDAOImplSQL implements NoticeDAO {
    private static final String GET_ALL = "SELECT * FROM notices ORDER BY notice_id DESC";
    private static final String GET_NOTICE_BY_ID = "SELECT * FROM notices WHERE notice_id=?";
    private static final String ADD_NOTICE = "INSERT INTO notices (notice_title, notice_body, post_date) VALUES (?, ?, ?);";
    private static final String UPDATE_NOTICE = "UPDATE notices SET notice_title=?, notice_body=? WHERE notice_id=?";
    private static final String DELETE_NOTICE = "DELETE FROM notices WHERE notice_id=?";

    @Override
    public List<Notice> getAllNotices() throws DAOException {
        List<Notice> notices = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(GET_ALL);

            while (result.next()) {
                notices.add(new Notice(result.getInt("notice_id"), result.getString("notice_title"),
                        result.getString("notice_body"), result.getDate("post_date")));
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

        return notices;
    }

    @Override
    public boolean addNotice(Notice notice) throws DAOException {
        boolean isSuccesfull = false;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NOTICE);
            preparedStatement.setString(1, notice.getTitleText());
            preparedStatement.setString(2, notice.getBodyText());
            preparedStatement.setDate(3, notice.getPostDate());
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
    public boolean updateNotice(Notice notice) throws DAOException {
        boolean isSuccesfull = false;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_NOTICE);
            preparedStatement.setString(1, notice.getTitleText());
            preparedStatement.setString(2, notice.getBodyText());
            preparedStatement.setInt(3, notice.getNoticeId());
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
    public boolean deleteNotice(int targetId) throws DAOException {
        boolean isSuccesfull = false;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_NOTICE);
            preparedStatement.setInt(1, targetId);
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
    public Notice getNoticeById(int targetId) throws DAOException {
        Notice target = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(GET_NOTICE_BY_ID);
            preparedStatement.setInt(1, targetId);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                target = new Notice(result.getInt("notice_id"), result.getString("notice_title"),
                        result.getString("notice_body"), result.getDate("post_date"));
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

        return target;
    }
}