package by.epamtc.zotov.finalproject.dao.book;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Book;
import by.epamtc.zotov.finalproject.exception.DAOException;

public interface BookDAO {
    public List<Book> getAllBooks() throws DAOException;
}