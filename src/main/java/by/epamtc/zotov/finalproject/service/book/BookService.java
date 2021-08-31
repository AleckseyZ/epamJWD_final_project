package by.epamtc.zotov.finalproject.service.book;

import java.util.List;

import javax.servlet.http.Part;

import by.epamtc.zotov.finalproject.entity.Book;
import by.epamtc.zotov.finalproject.exception.ServiceException;

/**
 * Defines service-layer methods related to books.
 */
public interface BookService {
    /**
     * Calls relevant DAO-layer method to query data source
     * 
     * @return list of all books
     * @throws ServiceException
     */
    public List<Book> getAllBooks() throws ServiceException;

    /**
     * Searches data source for books with certain title
     * 
     * @param title desired book's title
     * @return list of all books with desired title
     * @throws ServiceException
     */
    public List<Book> findBooksByTitle(String title) throws ServiceException;

    /**
     * Searches data source for books with certain author
     * 
     * @param author desired author
     * @return list of all books with desired author
     * @throws ServiceException
     */
    public List<Book> findBooksByAuthor(String author) throws ServiceException;

    /**
     * Searches data source for books with certain tags
     * 
     * @param tags String containing
     * @return list of all books with desired tags
     * @throws ServiceException
     */
    public List<Book> findBooksByTags(String tags) throws ServiceException;

    /**
     * Searches data source for book with desired ID
     * 
     * @param tags desired book's ID
     * @return Book object with desired ID or null if no such book exist in data
     *         source
     * @throws ServiceException
     */
    public Book findBookById(int targetId) throws ServiceException;

    /**
     * Creates book object and inserts it into data source
     * 
     * @param title  Book's title
     * @param author Book's author
     * @param blurb  Short description of book's contents
     * @param amount Amount of available books
     * @param tags   String contaning words that describe book
     * @param cover  Image of book's cover
     * @return Whether operation successful or not
     * @throws ServiceException
     */
    public boolean addBook(String title, String author, String blurb, int amount, String tags, Part cover)
            throws ServiceException;

    /**
     * Updates book object in data source
     * 
     * @param targetId     ID of book that is being updated
     * @param title        Book's title
     * @param author       Book's author
     * @param blurb        Short description of book's contents
     * @param amount       Amount of available books
     * @param tags         String contaning words that describe bookName
     * @param cover        new Image of book's cover. If it is null, cover won't be
     *                     updated
     * @param oldCoverName Name of old cover
     * @return Whether operation successful or not
     * @throws ServiceException
     */
    public boolean updateBook(int targetId, String title, String author, String blurb, int amount, String tags,
            String oldCoverName, Part cover) throws ServiceException;
}