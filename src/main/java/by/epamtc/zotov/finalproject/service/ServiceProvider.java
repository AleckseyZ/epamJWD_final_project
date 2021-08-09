package by.epamtc.zotov.finalproject.service;

import by.epamtc.zotov.finalproject.service.notice.NoticeService;
import by.epamtc.zotov.finalproject.service.notice.NoticeServiceImpl;

public class ServiceProvider {
    private static final NoticeService announcementService = new NoticeServiceImpl();

    private ServiceProvider(){};

    public static NoticeService getNoticeService() {
        return announcementService;
    }
}