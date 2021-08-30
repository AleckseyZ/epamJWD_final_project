package by.epamtc.zotov.finalproject.service.card;

import by.epamtc.zotov.finalproject.dao.DAOFactory;
import by.epamtc.zotov.finalproject.entity.Card;
import by.epamtc.zotov.finalproject.exception.DAOException;
import by.epamtc.zotov.finalproject.exception.ServiceException;

public class CardServiceImpl implements CardService {
    @Override
    public boolean addCard(String username, String holder) throws ServiceException {
        boolean isSuccesfull = false;
        try {
            int userId = DAOFactory.getUserDAO().findUserIdByUsername(username);
            isSuccesfull = DAOFactory.getCardDAO().addCard(new Card(userId, holder));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isSuccesfull;
    }

    @Override
    public Card findCardById(int targetId) throws ServiceException {
        Card targedCard = null;
        try {
            targedCard = DAOFactory.getCardDAO().findCardById(targetId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return targedCard;
    }

    @Override
    public Card findCardByUserId(int targetId) throws ServiceException {
        Card targedCard = null;
        try {
            targedCard = DAOFactory.getCardDAO().findCardByUserId(targetId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return targedCard;
    }

    @Override
    public String findStatusByStatusId(int targetId) throws ServiceException {
        String status = null;
        try {
            status = DAOFactory.getCardDAO().findStatusByStatusId(targetId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return status;
    }
}