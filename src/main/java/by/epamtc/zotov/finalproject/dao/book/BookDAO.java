package by.epamtc.zotov.finalproject.dao.book;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Book;
import by.epamtc.zotov.finalproject.exception.DAOException;

public interface BookDAO {
    public List<Book> findBooksByTags(String tags) throws DAOException;
    public List<Book> findAllBooks() throws DAOException;
    public boolean addBook() throws DAOException;
    public boolean updateBook(Book book) throws DAOException;
}