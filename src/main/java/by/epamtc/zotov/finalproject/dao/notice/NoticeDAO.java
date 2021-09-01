package by.epamtc.zotov.finalproject.dao.notice;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Notice;
import by.epamtc.zotov.finalproject.exception.DAOException;

/**
 * Defines methods related to working with loans in the datasource.
 */
public interface NoticeDAO {
    /**
     * Retrives a list of all notices in the datasource.
     */
    public List<Notice> getAllNotices() throws DAOException;

    /**
     * Searches datasource for a notice with specified ID.
     * 
     * @return Notice with specified ID or null if no such notice exist
     */
    public Notice getNoticeById(int targetId) throws DAOException;

    /**
     * Adds notice to the datasource
     * 
     * @return whether operation was successful or not
     */
    public boolean addNotice(Notice notice) throws DAOException;

    /**
     * Updates notice in the datasource
     * 
     * @return whether operation was successful or not
     */
    public boolean updateNotice(Notice notice) throws DAOException;

    /**
     * Deletes notice with specified ID from the datasource
     * 
     * @return whether operation was successful or not
     */
    public boolean deleteNotice(int targetId) throws DAOException;
}