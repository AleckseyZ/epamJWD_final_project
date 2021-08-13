package by.epamtc.zotov.finalproject.service;

import by.epamtc.zotov.finalproject.service.notice.NoticeService;
import by.epamtc.zotov.finalproject.service.notice.NoticeServiceImpl;
import by.epamtc.zotov.finalproject.service.user.UserService;
import by.epamtc.zotov.finalproject.service.user.UserServiceImpl;

public class ServiceFactory {
    private static final NoticeService announcementService = new NoticeServiceImpl();
    private static final UserService userService = new UserServiceImpl();

    private ServiceFactory(){};

    public static NoticeService getNoticeService() {
        return announcementService;
    }

    public static UserService getUserService() {
        return userService;
    }
}