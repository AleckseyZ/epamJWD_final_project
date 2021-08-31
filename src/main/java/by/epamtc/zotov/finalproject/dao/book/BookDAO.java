package by.epamtc.zotov.finalproject.dao.book;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Book;
import by.epamtc.zotov.finalproject.exception.DAOException;

/**
 * Defines methods related to working with books in the datasource.
 */
public interface BookDAO {
    /**
     * Retrives a list of all books in the datasource.
     */
    public List<Book> getAllBooks() throws DAOException;

    /**
     * Searches datasource for book with target ID
     * 
     * @return Book object with target ID or null if no such object exist
     */
    public Book findBookById(int targetId) throws DAOException;

    /**
     * Adds book to the datasource
     * 
     * @return whether operation was successful or not
     */
    public boolean addBook(Book book) throws DAOException;

    /**
     * Updates book in the datasource
     * 
     * @return whether operation was successful or not
     */
    public boolean updateBook(Book book) throws DAOException;
}