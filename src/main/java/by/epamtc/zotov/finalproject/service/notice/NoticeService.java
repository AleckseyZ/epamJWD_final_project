package by.epamtc.zotov.finalproject.service.notice;

import java.util.List;
import by.epamtc.zotov.finalproject.entity.Notice;
import by.epamtc.zotov.finalproject.exception.ServiceException;

/**
 * Defines service-layer methods related to notices.
 */
public interface NoticeService {
    /**
     * Calls relevant DAO-layer method to query data source.
     * 
     * @return list of all notices
     * @throws ServiceException
     */
    public List<Notice> getAllNotices() throws ServiceException;

    /**
     * Creates new notice objects and inserts it into data source.
     * 
     * @param titleText notice title
     * @param bodyText  information
     * @return whether operation was successful or not
     * @throws ServiceException
     */
    public boolean addNotice(String titleText, String bodyText) throws ServiceException;

    /**
     * Updates notice object with certain ID in the datasource.
     * 
     * @param targetId  ID of notice to update
     * @param titleText notice title
     * @param bodyText  notice information
     * @return whether operation was successful or not
     * @throws ServiceException
     */
    public boolean updateNotice(int targetId, String titleText, String bodyText) throws ServiceException;

    /**
     * Deletes notice with target ID from data source.
     * 
     * @param targetId ID of notice to delete
     * @return whether operation was successful or not
     * @throws ServiceException
     */
    public boolean deleteNotice(int targetId) throws ServiceException;

    /**
     * Searches data source for a notice with certain ID
     * 
     * @param targetId desired ID
     * @return Notice object with desired ID or null if no such object exists
     * @throws ServiceException
     */
    public Notice getNoticeById(int targetId) throws ServiceException;
}