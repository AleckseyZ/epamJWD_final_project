package by.epamtc.zotov.finalproject.service.user;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.User;
import by.epamtc.zotov.finalproject.exception.ServiceException;

/**
 * Defines service-layer methods related to users.
 */
public interface UserService {
    /**
     * Searches data source for user with desired username.
     * 
     * @param username desired username
     * @return Found User or null if no suitable user exist
     * @throws ServiceException
     */
    public User findUserByUsername(String username) throws ServiceException;

    /**
     * Searches data source for user with desired ID.
     * 
     * @param targetId desired ID
     * @return Found User or null if no suitable user exist
     * @throws ServiceException
     */
    public User findUserById(int targetId) throws ServiceException;

    /**
     * Creates new user objects and inserts it into data source.
     * 
     * @param username new user's Username
     * @param password new user's Password
     * @param email    new user's email
     * @return whether operation was success or not
     * @throws ServiceException
     */
    public boolean addUser(String username, char[] password, String email) throws ServiceException;

    /**
     * Updates password of desired user.
     * 
     * @param targetId       ID of desired user
     * @param password       Current password of desired user
     * @param newPassword    New password of desired user
     * @param repeatPassword Repeated new password
     * @return whether operation was success or not
     * @throws ServiceException
     */
    public boolean updatePassword(int targetId, char[] password, char[] newPassword, char[] repeatPassword)
            throws ServiceException;

    /**
     * Updates email of desired user.
     * 
     * @param targetId ID of desired user
     * @param newEmail new email
     * @return whether operation was success or not
     * @throws ServiceException
     */
    public boolean updateEmail(int targetId, String newEmail) throws ServiceException;

    /**
     * Updates status of desired user
     * 
     * @param targetId  ID of desired user
     * @param newStatus new status
     * @return whether operation was success or not
     * @throws ServiceException
     */
    public boolean updateStatus(int targetId, String newStatus) throws ServiceException;

    /**
     * Checks if there is a user with target username and password in the data
     * source.
     * 
     * @param username
     * @param password
     * @return whether there is a user with such credentials or not
     * @throws ServiceException
     */
    public boolean authenticate(String username, char[] password) throws ServiceException;

    /**
     * Searches data source for user type of target user
     * 
     * @param username target's username
     * @return User type of target user
     * @throws ServiceException
     */
    public String findUserTypeByUsername(String username) throws ServiceException;

    /**
     * Searches data source for user ID by username
     * 
     * @param username username of target user
     * @return user ID of user with desired username or 0 if no such user exist
     * @throws ServiceException
     */
    public int findUserIdByUsername(String username) throws ServiceException;

    /**
     * Gets all user types from data source
     * 
     * @return List of user types
     * @throws ServiceException
     */
    public List<String> getUserTypes() throws ServiceException;
}