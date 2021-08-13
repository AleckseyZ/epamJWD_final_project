package by.epamtc.zotov.finalproject.dao.card;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Card;
import by.epamtc.zotov.finalproject.exception.DAOException;

public interface CardDAO {
    public List<Card> getAllCards() throws DAOException;
    public boolean addCard(Card newCard) throws DAOException;
}