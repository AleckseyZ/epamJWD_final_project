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

public class DAOFactory {
    private static volatile DAOFactory instance;
    private final NoticeDAO noticeDAO = new NoticeDAOImplSQL();
    private final UserDAO userDAO = new UserDAOImplSQL();
    private final BookDAO bookDAO = new BookDAOimplSQL();
    private final CardDAO cardDAO = new CardDAOimplSQL();
    private final LoanDAO loanDAO = new LoanDAOImplSQL();

    private DAOFactory() {};

    public static DAOFactory getInstance() {
        if (instance == null) {
            synchronized (DAOFactory.class) {
                instance = new DAOFactory();
            }
        }
        return instance;
    }

    public NoticeDAO getNoticeDAO() {
        return noticeDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public BookDAO getBookDAO() {
        return bookDAO;
    }

    public CardDAO getCardDAO() {
        return cardDAO;
    }

    public LoanDAO getLoanDAO() {
        return loanDAO;
    }
}