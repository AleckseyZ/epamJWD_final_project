package by.epamtc.zotov.finalproject.dao.notice;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Notice;
import by.epamtc.zotov.finalproject.exception.DAOException;

public interface NoticeDAO {
    public List<Notice>getAllNotices() throws DAOException;

    public boolean addNotice(Notice announcment) throws DAOException;
}