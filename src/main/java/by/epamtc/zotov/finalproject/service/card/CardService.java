package by.epamtc.zotov.finalproject.service.card;

import by.epamtc.zotov.finalproject.entity.Card;
import by.epamtc.zotov.finalproject.exception.ServiceException;

/**
 * Defines service-layer methods related to library cards.
 */
public interface CardService {
    /**
     * Call relevant DAO-layer method to query card with desired ID.
     * 
     * @param targetId desired ID
     * @return Object repesenting card with desired ID or null, if no such object
     *         exist in data source
     * @throws ServiceException
     */
    public Card findCardById(int targetId) throws ServiceException;

    /**
     * Call relevant DAO-layer method to query card with desired User ID.
     * 
     * @param targetId desired User ID
     * @return Object repesenting card with desired User ID or null, if no such
     *         object exist in data source
     * @throws ServiceException
     */
    public Card findCardByUserId(int targetId) throws ServiceException;

    /**
     * Call relevant DAO-layer method to find status name by status id
     * 
     * @param targetId Desired status id
     * @return String containing status name
     * @throws ServiceException
     */
    public String findStatusByStatusId(int targetId) throws ServiceException;

    /**
     * Creates new card object and inserts it into data source.
     * 
     * @param username Card holder's username
     * @param holder   Card holders Name, Surname etc
     * @return Whether card was added successful or not
     * @throws ServiceException
     */
    public boolean addCard(String username, String holder) throws ServiceException;
}