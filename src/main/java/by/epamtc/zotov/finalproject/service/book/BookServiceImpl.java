package by.epamtc.zotov.finalproject.service.book;

import java.util.ArrayList;
import java.util.List;

import by.epamtc.zotov.finalproject.dao.DAOFactory;
import by.epamtc.zotov.finalproject.dao.book.BookDAO;
import by.epamtc.zotov.finalproject.entity.Book;
import by.epamtc.zotov.finalproject.exception.DAOException;
import by.epamtc.zotov.finalproject.exception.ServiceException;

public class BookServiceImpl implements BookService {

    @Override
    public List<Book> getAllBooks() throws ServiceException {
        List<Book> books = null;

        BookDAO bookDAO = DAOFactory.getBookDAO();
        try {
            books = bookDAO.getAllBooks();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return books;
    }

    @Override
    public List<Book> findBooksByTitle(String title) throws ServiceException {
        List<Book> books = getAllBooks();
        List<Book> foundBooks = new ArrayList<>();
        String targetTitle = title.toLowerCase();

        for (Book book : books) {
            String bookTitle = book.getTitle().toLowerCase();
            if (bookTitle.contains(targetTitle)) {
                foundBooks.add(book);
            }
        }

        return books;
    }

    @Override
    public List<Book> findBooksByAuthor(String author) throws ServiceException {
        List<Book> books = getAllBooks();
        List<Book> foundBooks = new ArrayList<>();
        String targetAuthor = author.toLowerCase();

        for (Book book : books) {
            String bookTitle = book.getAuthor().toLowerCase();
            if (bookTitle.contains(targetAuthor)) {
                foundBooks.add(book);
            }
        }

        return books;
    }

    @Override
    public List<Book> findBooksByTags(String tags) throws ServiceException {
        List<Book> books = getAllBooks();
        List<Book> foundBooks = new ArrayList<>();
        String[] targetTags = tags.toUpperCase().split("[,\\s]+");

        for (Book book : books) {
            try {
                List<String> bookTags = DAOFactory.getTagDAO().getTagsByBookId(book.getBookId());
                boolean isSuitable = true;
                for (String tag : targetTags) {
                    if (!bookTags.contains(tag)) {
                        isSuitable = false;
                        break;
                    }
                }
                if (isSuitable) {
                    foundBooks.add(book);
                }
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }

        return foundBooks;
    }
}