package by.epamtc.zotov.finalproject.service.notice;

import java.sql.Date;
import java.util.List;

import by.epamtc.zotov.finalproject.entity.Notice;
import by.epamtc.zotov.finalproject.exception.ServiceException;

public interface NoticeService {
    public List<Notice> getAllNotices() throws ServiceException;

    public boolean addNotice(String titleText, String bodyText, Date postDate) throws ServiceException;
}