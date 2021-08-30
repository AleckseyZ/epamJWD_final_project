package by.epamtc.zotov.finalproject.dao;

import by.epamtc.zotov.finalproject.dao.book.BookDAO;
import by.epamtc.zotov.finalproject.dao.book.BookDAOimplSQL;
import by.epamtc.zotov.finalproject.dao.card.CardDAO;
import by.epamtc.zotov.finalproject.dao.card.CardDAOimplSQL;
import by.epamtc.zotov.finalproject.dao.loan.LoanDAO;
import by.epamtc.zotov.finalproject.dao.loan.LoanDAOImplSQL;
import by.epamtc.zotov.finalproject.dao.notice.NoticeDAO;
import by.epamtc.zotov.finalproject.dao.notice.NoticeDAOImplSQL;
import by.epamtc.zotov.finalproject.dao.user.UserDAO;
import by.epamtc.zotov.finalproject.dao.user.UserDAOImplSQL;

//TODO READ ABOUT STATIC FACTORY OR CHANGE TO SINGLTON
public class DAOFactory {
    private static final NoticeDAO noticeDAO = new NoticeDAOImplSQL();
    private static final UserDAO userDAO = new UserDAOImplSQL();
    private static final BookDAO bookDAO = new BookDAOimplSQL();
    private static final CardDAO cardDAO = new CardDAOimplSQL();
    private static final LoanDAO loanDAO = new LoanDAOImplSQL();

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

    public static CardDAO getCardDAO() {
        return cardDAO;
    }

    public static LoanDAO getLoanDAO() {
        return loanDAO;
    }
}