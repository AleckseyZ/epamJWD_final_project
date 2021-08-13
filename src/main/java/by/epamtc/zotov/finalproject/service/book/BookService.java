package by.epamtc.zotov.finalproject.service.book;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Book;
import by.epamtc.zotov.finalproject.exception.ServiceException;

public interface BookService {
    public List<Book> getAllBooks() throws ServiceException;
    public List<Book> findBooksByTitle(String title) throws ServiceException;
    public List<Book> findBooksByAuthor(String title) throws ServiceException;
    public List<Book> findBooksByTags(String tags) throws ServiceException;
}