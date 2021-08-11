package by.epamtc.zotov.finalproject.dao.user;

import by.epamtc.zotov.finalproject.entity.User;
import by.epamtc.zotov.finalproject.exception.DAOException;

public interface UserDAO {
    public boolean addUser (User user) throws DAOException;
    public User findUserByUsername(String username) throws DAOException;
}