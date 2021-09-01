package by.epamtc.zotov.finalproject.service;

import by.epamtc.zotov.finalproject.service.book.BookService;
import by.epamtc.zotov.finalproject.service.book.BookServiceImpl;
import by.epamtc.zotov.finalproject.service.card.CardService;
import by.epamtc.zotov.finalproject.service.card.CardServiceImpl;
import by.epamtc.zotov.finalproject.service.loan.LoanService;
import by.epamtc.zotov.finalproject.service.loan.LoanServiceImpl;
import by.epamtc.zotov.finalproject.service.notice.NoticeService;
import by.epamtc.zotov.finalproject.service.notice.NoticeServiceImpl;
import by.epamtc.zotov.finalproject.service.user.UserService;
import by.epamtc.zotov.finalproject.service.user.UserServiceImpl;

public class ServiceFactory {
    private static volatile ServiceFactory instance;
    private final NoticeService announcementService = new NoticeServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final BookService bookService = new BookServiceImpl();
    private final CardService cardService = new CardServiceImpl();
    private final LoanService loanService = new LoanServiceImpl();

    private ServiceFactory(){};

    public static ServiceFactory getInstance() {
        if (instance == null) {
            synchronized (ServiceFactory.class) {
                instance = new ServiceFactory();
            }
        }
        return instance;
    }

    public NoticeService getNoticeService() {
        return announcementService;
    }

    public UserService getUserService() {
        return userService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public CardService getCardService() {
        return cardService;
    }

    public LoanService getLoanService() {
        return loanService;
    }
}