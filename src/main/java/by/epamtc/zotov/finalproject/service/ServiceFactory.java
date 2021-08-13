package by.epamtc.zotov.finalproject.service;

import by.epamtc.zotov.finalproject.service.book.BookService;
import by.epamtc.zotov.finalproject.service.book.BookServiceImpl;
import by.epamtc.zotov.finalproject.service.notice.NoticeService;
import by.epamtc.zotov.finalproject.service.notice.NoticeServiceImpl;
import by.epamtc.zotov.finalproject.service.tag.TagService;
import by.epamtc.zotov.finalproject.service.tag.TagServiceImpl;
import by.epamtc.zotov.finalproject.service.user.UserService;
import by.epamtc.zotov.finalproject.service.user.UserServiceImpl;

public class ServiceFactory {
    private static final NoticeService announcementService = new NoticeServiceImpl();
    private static final UserService userService = new UserServiceImpl();
    private static final BookService bookService = new BookServiceImpl();
    private static final TagService tagService = new TagServiceImpl();

    private ServiceFactory(){};

    public static NoticeService getNoticeService() {
        return announcementService;
    }

    public static UserService getUserService() {
        return userService;
    }

    public static BookService getBookService() {
        return bookService;
    }

    public static TagService getTagService() {
        return tagService;
    }
}