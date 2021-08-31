package by.epamtc.zotov.finalproject.dao.card;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.Card;
import by.epamtc.zotov.finalproject.exception.DAOException;

/**
 * Defines methods related to working with cards in the datasource.
 */
public interface CardDAO {
    /**
     * Retrives list of all library cards in the datasource
     */
    public List<Card> getAllCards() throws DAOException;

    /**
     * Searches database for the card with target ID
     * 
     * @return Card with desired ID or null if no such card exist
     */
    public Card findCardById(int targetId) throws DAOException;

    /**
     * Searches database for the card with desired username
     * 
     * @return Card with desired username or null if no such card exist
     */
    public Card findCardByUserId(int targetId) throws DAOException;

    /**
     * Searches database for the card with desired username
     */
    public String findStatusByStatusId(int statusId) throws DAOException;

    /**
     * Adds card to the data source
     * @return whether operations was success or not
     */
    public boolean addCard(Card newCard) throws DAOException;
}