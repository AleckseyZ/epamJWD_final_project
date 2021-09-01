package by.epamtc.zotov.finalproject.dao.user;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.User;
import by.epamtc.zotov.finalproject.exception.DAOException;

/**
 * Defines methods related to working with users in the datasource.
 */
public interface UserDAO {
    /**
     * Adds user to the darasource
     * 
     * @return whether operation was successful or not
     */
    public boolean addUser(User user) throws DAOException;

    /**
     * Updates password of user with specified ID in the datasource
     * 
     * @param targetId    ID of target user
     * @param newPassword Hash of new password
     * @return whether operation was successful or not
     */
    public boolean updatePassword(int targetId, byte[] newPassword) throws DAOException;

    /**
     * Updates email of user with specified ID in the datasource
     * 
     * @return whether operation was successful or not
     */
    public boolean updateEmail(int targetId, String newEmail) throws DAOException;

    /**
     * Updates type of user with specified ID in the datasource
     * 
     * @return whether operation was successful or not
     */
    public boolean updateType(int targetId, int typeId) throws DAOException;

    /**
     * Searches data source for the user with specified username
     * 
     * @return User object with specified username or null if no such user exist
     */
    public User findUserByUsername(String username) throws DAOException;

    /**
     * Searches data source for the user with specified ID
     * 
     * @return User object with specified ID or null if no such user exist
     */
    public User findUserById(int targetId) throws DAOException;

    /**
     * Searches datasource for ID of user with specified Username
     * 
     * @return ID of user with specified username or 0 if no such user exist
     */
    public int findUserIdByUsername(String username) throws DAOException;

    /**
     * Retrivies user type of user with specified username
     * 
     * @return user type of user with specified username or null if no such user
     *         exist
     */
    public String findUserTypeByUsername(String username) throws DAOException;

    /**
     * Retrivies user type id with specified type name
     * 
     * @return user type id of specified type name or 0 if no such user type exist
     */
    public int findTypeIdByTypeName(String statusName) throws DAOException;

    /**
     * Retrivies list of user types from the data source
     */
    public List<String> getUserTypes() throws DAOException;
}