package by.epamtc.zotov.finalproject.dao.tag;

import java.util.List;

import by.epamtc.zotov.finalproject.exception.DAOException;

public interface TagDAO {
    public List<String> getTagsByBookId(int bookId) throws DAOException; 
}