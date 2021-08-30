package by.epamtc.zotov.finalproject.dao.book;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private static final String ADD_BOOK = "INSERT INTO public.books(author, blurb, amount, title, cover, tags) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_BOOK = "UPDATE books SET author=?, blurb=?, amount=?, title=?,  cover=?, tags=? WHERE book_id=?";
    private static final String GET_BOOK_BY_ID = "SELECT * FROM books WHERE book_id=?";

    @Override
    public List<Book> getAllBooks() throws DAOException {
        List<Book> Books = new ArrayList<>();

        Connection connection = ConnectionPool.getInstance().takeConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(GET_ALL);

            while (result.next()) {
                Books.add(new Book(result.getInt("book_id"), result.getString("author"), result.getString("blurb"),
                        result.getString("title"), result.getString("cover"), result.getString("tags"),
                        result.getInt("amount")));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return Books;
    }

    @Override
    public Book findBookById(int targetId) throws DAOException {
        Book target = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOK_BY_ID);
            preparedStatement.setInt(1, targetId);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                target = new Book(result.getInt("book_id"), result.getString("author"), result.getString("blurb"),
                result.getString("title"), result.getString("cover"), result.getString("tags"),
                result.getInt("amount"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return target;
    }

    @Override
    public boolean addBook(Book book) throws DAOException {
        boolean isSuccesfull = false;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_BOOK);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getBlurb());
            preparedStatement.setInt(3, book.getAmount());
            preparedStatement.setString(4, book.getTitle());
            preparedStatement.setString(5, book.getCover());
            preparedStatement.setString(6, book.getTags());
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

    @Override
    public boolean updateBook(Book book) throws DAOException {
        boolean isSuccesfull = false;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK);
            preparedStatement.setString(1, book.getAuthor());
            preparedStatement.setString(2, book.getBlurb());
            preparedStatement.setInt(3, book.getAmount());
            preparedStatement.setString(4, book.getTitle());
            preparedStatement.setString(5, book.getCover());
            preparedStatement.setString(6, book.getTags());
            preparedStatement.setInt(7, book.getBookId());
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