package by.epamtc.zotov.finalproject.dao.book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.zotov.finalproject.dao.connection.ConnectionPool;
import by.epamtc.zotov.finalproject.entity.Book;
import by.epamtc.zotov.finalproject.exception.DAOException;

public class BookDAOimplSQL implements BookDAO {
    private static final String GET_ALL = "SELECT * FROM books";

    @Override
    public List<Book> getAllBooks() throws DAOException {
        List<Book> Books = new ArrayList<>();

        Connection connection = ConnectionPool.getInstance().takeConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(GET_ALL);

            while (result.next()) {
                Books.add(new Book(result.getInt("book_id"), result.getString("author"), result.getString("blurb"),
                        result.getString("title"), result.getInt("amount")));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return Books;
    }
}