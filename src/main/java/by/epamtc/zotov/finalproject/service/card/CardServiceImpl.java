package by.epamtc.zotov.finalproject.service.card;

import by.epamtc.zotov.finalproject.dao.DAOFactory;
import by.epamtc.zotov.finalproject.entity.Card;
import by.epamtc.zotov.finalproject.exception.DAOException;
import by.epamtc.zotov.finalproject.exception.ServiceException;

public class CardServiceImpl implements CardService {
    public static final int BLOCKED_STATUS_CODE = 2;

    @Override
    public boolean addCard(String username, String holder) throws ServiceException {
        boolean isSuccesfull = false;
        try {
            int userId = DAOFactory.getInstance().getUserDAO().findUserIdByUsername(username);
            isSuccesfull = DAOFactory.getInstance().getCardDAO().addCard(new Card(userId, holder));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return isSuccesfull;
    }

    @Override
    public boolean blockCard(int targetId) throws ServiceException {
        boolean isSuccesfull = false;

        try {
            Card card = DAOFactory.getInstance().getCardDAO().findCardById(targetId);
            if (card != null) {
                card.setStatusId(BLOCKED_STATUS_CODE);
                isSuccesfull = DAOFactory.getInstance().getCardDAO().updateCard(card);
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return isSuccesfull;
    }

    @Override
    public Card findCardById(int targetId) throws ServiceException {
        Card targedCard = null;
        try {
            targedCard = DAOFactory.getInstance().getCardDAO().findCardById(targetId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return targedCard;
    }

    @Override
    public Card findCardByUserId(int targetId) throws ServiceException {
        Card targedCard = null;
        try {
            targedCard = DAOFactory.getInstance().getCardDAO().findCardByUserId(targetId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return targedCard;
    }

    @Override
    public String findStatusByStatusId(int targetId) throws ServiceException {
        String status = null;
        try {
            status = DAOFactory.getInstance().getCardDAO().findStatusByStatusId(targetId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return status;
    }
}