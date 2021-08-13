package by.epamtc.zotov.finalproject.dao;

import by.epamtc.zotov.finalproject.dao.book.BookDAO;
import by.epamtc.zotov.finalproject.dao.book.BookDAOimplSQL;
import by.epamtc.zotov.finalproject.dao.notice.NoticeDAO;
import by.epamtc.zotov.finalproject.dao.notice.NoticeDAOImplSQL;
import by.epamtc.zotov.finalproject.dao.tag.TagDAO;
import by.epamtc.zotov.finalproject.dao.tag.TagDAOimplSQL;
import by.epamtc.zotov.finalproject.dao.user.UserDAO;
import by.epamtc.zotov.finalproject.dao.user.UserDAOImplSQL;

public class DAOFactory {
    private static final NoticeDAO noticeDAO = new NoticeDAOImplSQL();
    private static final UserDAO userDAO = new UserDAOImplSQL();
    private static final BookDAO bookDAO = new BookDAOimplSQL();
    private static final TagDAO tagDAO = new TagDAOimplSQL();

    private DAOFactory() {
    };

    public static NoticeDAO getNoticeDAO() {
        return noticeDAO;
    }

    public static UserDAO getUserDAO() {
        return userDAO;
    }

    public static BookDAO getBookDAO() {
        return bookDAO;
    }

    public static TagDAO getTagDAO() {
        return tagDAO;
    }
}