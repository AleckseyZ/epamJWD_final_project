package by.epamtc.zotov.finalproject.service.notice;

import java.sql.Date;
import java.util.List;

import by.epamtc.zotov.finalproject.dao.DAOFactory;
import by.epamtc.zotov.finalproject.dao.notice.NoticeDAO;
import by.epamtc.zotov.finalproject.entity.Notice;
import by.epamtc.zotov.finalproject.exception.DAOException;
import by.epamtc.zotov.finalproject.exception.ServiceException;

public class NoticeServiceImpl implements NoticeService {
    @Override
    public List<Notice> getAllNotices() throws ServiceException {
        List<Notice> notices = null;

        NoticeDAO noticeDAO = DAOFactory.getInstance().getNoticeDAO();
        try {
            notices = noticeDAO.getAllNotices();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return notices;
    }

    @Override
    public boolean addNotice(String titleText, String bodyText) throws ServiceException {
        boolean isSuccesfull = false;

        Notice notice = new Notice(titleText, bodyText, new Date(System.currentTimeMillis()));
        try {
            isSuccesfull = DAOFactory.getInstance().getNoticeDAO().addNotice(notice);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return isSuccesfull;
    }

    @Override
    public boolean updateNotice(int targetId, String titleText, String bodyText) throws ServiceException {
        boolean isSuccesfull = false;

        Notice notice = new Notice(targetId, titleText, bodyText);
        try {
            isSuccesfull = DAOFactory.getInstance().getNoticeDAO().updateNotice(notice);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return isSuccesfull;
    }

    @Override
    public boolean deleteNotice(int targetId) throws ServiceException {
        boolean isSuccesfull = false;

        try {
            isSuccesfull = DAOFactory.getInstance().getNoticeDAO().deleteNotice(targetId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return isSuccesfull;
    }

    @Override
    public Notice getNoticeById(int targetId) throws ServiceException {
        Notice target=null;

        try {
            target = DAOFactory.getInstance().getNoticeDAO().getNoticeById(targetId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        
        return target;
    }
}