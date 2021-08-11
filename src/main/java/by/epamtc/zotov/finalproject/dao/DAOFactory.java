package by.epamtc.zotov.finalproject.dao;

import by.epamtc.zotov.finalproject.dao.notice.NoticeDAO;
import by.epamtc.zotov.finalproject.dao.notice.NoticeDAOImplSQL;
import by.epamtc.zotov.finalproject.dao.user.UserDAO;
import by.epamtc.zotov.finalproject.dao.user.UserDAOImplSQL;

public class DAOFactory {
    private static final NoticeDAO noticeDAO = new NoticeDAOImplSQL();
    private static final UserDAO userDAO = new UserDAOImplSQL();

    private DAOFactory() {
    };

    public static NoticeDAO getNoticeDAO() {
        return noticeDAO;
    }

    public static UserDAO getUserDAO() {
        return userDAO;
    }
}