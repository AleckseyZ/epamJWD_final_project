package by.epamtc.zotov.finalproject.dao.card;

import by.epamtc.zotov.finalproject.entity.Card;
import by.epamtc.zotov.finalproject.exception.DAOException;

public interface CardDAO {
    public boolean addCard(Card newCard) throws DAOException;
}