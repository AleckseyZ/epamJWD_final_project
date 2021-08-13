package by.epamtc.zotov.finalproject.service.tag;

import java.util.List;

import by.epamtc.zotov.finalproject.exception.ServiceException;

public interface TagService {
    public List<String> getTagsByBookId(int bookId) throws ServiceException;
    public String getTagStringByBookId(int bookId) throws ServiceException;
}