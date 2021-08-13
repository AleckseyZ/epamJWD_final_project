package by.epamtc.zotov.finalproject.dao.tag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.zotov.finalproject.dao.connection.ConnectionPool;
import by.epamtc.zotov.finalproject.exception.DAOException;

public class TagDAOimplSQL implements TagDAO {
    private static final String GET_TAGS_BY_BOOK_ID = "SELECT tag_id, tag_name FROM tags LEFT JOIN Tags_tags using(tag_id) WHERE books_tags.book_id  = ?";

    @Override
    public List<String> getTagsByBookId(int tagId) throws DAOException {
        List<String> Tags = new ArrayList<>();

        Connection connection = ConnectionPool.getInstance().takeConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_TAGS_BY_BOOK_ID);
            preparedStatement.setInt(1, tagId);
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Tags.add(result.getString("tag_name"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return Tags;
    }
}