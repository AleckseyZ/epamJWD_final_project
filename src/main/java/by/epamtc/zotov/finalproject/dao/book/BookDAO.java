package by.epamtc.zotov.finalproject.dao.book;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Book;
import by.epamtc.zotov.finalproject.exception.DAOException;


public interface BookDAO {
    public List<Book> getAllBooks() throws DAOException;
    public Book findBookById(int targetId) throws DAOException;
    public boolean addBook(Book book) throws DAOException;
    public boolean updateBook(Book book) throws DAOException;
}