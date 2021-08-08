package by.epamtc.zotov.finalproject.dao;

import by.epamtc.zotov.finalproject.dao.notice.NoticeDAO;
import by.epamtc.zotov.finalproject.dao.notice.NoticeDAOImplSQL;

public class DAOProvider {
    private static final NoticeDAO noticeDAO = new NoticeDAOImplSQL();;

    private DAOProvider() {
    };

    public static NoticeDAO getNoticeDAO() {
        return noticeDAO;
    }
}