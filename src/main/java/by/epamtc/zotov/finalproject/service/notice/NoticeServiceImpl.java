package by.epamtc.zotov.finalproject.service.notice;

import java.sql.Date;
import java.util.List;

import by.epamtc.zotov.finalproject.dao.DAOProvider;
import by.epamtc.zotov.finalproject.dao.notice.NoticeDAO;
import by.epamtc.zotov.finalproject.entity.Notice;
import by.epamtc.zotov.finalproject.exception.DAOException;
import by.epamtc.zotov.finalproject.exception.ServiceException;

public class NoticeServiceImpl implements NoticeService {
    @Override
    public List<Notice> getAllNotices() throws ServiceException {
        List<Notice> notices = null;

        NoticeDAO noticeDAO = DAOProvider.getNoticeDAO();
        try {
            notices = noticeDAO.getAllNotices();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return notices;
    }

    @Override
    public boolean addNotice(String titleText, String bodyText, Date postDate) throws ServiceException {
        boolean isSuccesfull = false;
        if (titleText != null && bodyText != null && postDate != null) {
            Notice notice = new Notice(titleText, bodyText, postDate);
            try {
                isSuccesfull = DAOProvider.getNoticeDAO().addNotice(notice);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return isSuccesfull;
    }
}