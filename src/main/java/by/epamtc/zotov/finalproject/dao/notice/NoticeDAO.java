package by.epamtc.zotov.finalproject.dao.notice;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Notice;
import by.epamtc.zotov.finalproject.exception.DAOException;

public interface NoticeDAO {
    public List<Notice>getAllNotices() throws DAOException;
    public Notice getNoticeById(int targetId) throws DAOException;
    public boolean addNotice(Notice notice) throws DAOException;
    public boolean updateNotice(Notice notice) throws DAOException;
    public boolean deleteNotice(int targetId) throws DAOException;
}