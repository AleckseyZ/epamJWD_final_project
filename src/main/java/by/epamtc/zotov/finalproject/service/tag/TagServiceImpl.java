package by.epamtc.zotov.finalproject.service.tag;

import java.util.List;

import by.epamtc.zotov.finalproject.dao.DAOFactory;
import by.epamtc.zotov.finalproject.exception.DAOException;
import by.epamtc.zotov.finalproject.exception.ServiceException;

public class TagServiceImpl implements TagService {
    @Override
    public List<String> getTagsByBookId(int bookId) throws ServiceException {
        List<String> tags = null;
        try {
            tags = DAOFactory.getTagDAO().getTagsByBookId(bookId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return tags;
    }

    @Override
    public String getTagStringByBookId(int bookId) throws ServiceException {
        List<String> tags = getTagsByBookId(bookId);
        StringBuffer tagString = new StringBuffer();

        for (String tag : tags) {
            tagString.append(tag);
            tagString.append(" ");
        }
        return tagString.toString();
    }
}