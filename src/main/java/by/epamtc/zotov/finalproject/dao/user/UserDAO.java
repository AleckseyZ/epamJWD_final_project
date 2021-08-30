package by.epamtc.zotov.finalproject.dao.user;

import java.util.List;

import by.epamtc.zotov.finalproject.entity.User;
import by.epamtc.zotov.finalproject.exception.DAOException;

public interface UserDAO {
    public boolean addUser (User user) throws DAOException;
    public User findUserByUsername(String username) throws DAOException;
    public User findUserById(int targetId) throws DAOException;
    public int findUserIdByUsername(String username) throws DAOException;
    public String findUserTypeByUsername(String username) throws DAOException;
    public boolean updatePassword(int targetId, byte[] newPassword) throws DAOException;
    public boolean updateEmail(int targetId, String newEmail) throws DAOException;
    public boolean updateType(int targetId, int typeId) throws DAOException;
    public int findTypeIdByTypeName(String statusName) throws DAOException;
    public List<String> getUserTypes() throws DAOException;
}