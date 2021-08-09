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
    private static final String ADD_NOTICE = "INSERT INTO notices (notice_title, notice_body, post_date) VALUES (?, ?, ?);";

    @Override
    public List<Notice> getAllNotices() throws DAOException {
        List<Notice> notices = new ArrayList<>();

        Connection connection = ConnectionPool.getInstance().takeConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(GET_ALL);

            while (result.next()) {
                notices.add(new Notice(result.getInt("notice_id"), result.getString("notice_title"),
                        result.getString("notice_body"), result.getDate("post_date")));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return notices;
    }

    @Override
    public boolean addNotice(Notice notice) throws DAOException {
        boolean isSuccesfull = false;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NOTICE);
            preparedStatement.setString(1, notice.getTitleText());
            preparedStatement.setString(2, notice.getBodyText());
            preparedStatement.setDate(3, notice.getPostDate());
            if (preparedStatement.executeUpdate() != 0) {
                isSuccesfull = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return isSuccesfull;
    }
}
