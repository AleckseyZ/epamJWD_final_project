package by.epamtc.zotov.finalproject.dao.card;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Card;
import by.epamtc.zotov.finalproject.exception.DAOException;

public interface CardDAO {
    public List<Card> getAllCards() throws DAOException;
    public Card findCardById(int targetId) throws DAOException;
    public Card findCardByUserId(int targetId) throws DAOException;
    public String findStatusByStatusId(int statusId) throws DAOException;
    public boolean addCard(Card newCard) throws DAOException;
}