package by.epamtc.zotov.finalproject.service.user;

import by.epamtc.zotov.finalproject.exception.ServiceException;

public interface UserService {
    public boolean addUser(String username, char[] password, String email) throws ServiceException;
    public boolean authenticate(String username, char[] password) throws ServiceException;
}