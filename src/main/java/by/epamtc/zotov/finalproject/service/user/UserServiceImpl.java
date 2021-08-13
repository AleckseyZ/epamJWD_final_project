package by.epamtc.zotov.finalproject.service.user;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import by.epamtc.zotov.finalproject.dao.DAOFactory;
import by.epamtc.zotov.finalproject.entity.User;
import by.epamtc.zotov.finalproject.exception.DAOException;
import by.epamtc.zotov.finalproject.exception.ServiceException;

public class UserServiceImpl implements UserService {
    private final static int SALT_SIZE = 32;
    private final static int ITERATION_COUNT = 10000;
    private final static int KEY_LENGTH = 256;
    private final static String ALGORITHM = "PBKDF2WithHmacSHA1";

    @Override
    public boolean addUser(String username, char[] password, String email) throws ServiceException {
        boolean isSuccesfull = false;
        byte[] salt = new byte[SALT_SIZE];

        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        byte[] hashedPassword = hashPasword(password, salt);
        User newUser = new User(username, hashedPassword, salt, email);
        Arrays.fill(password, '0');

        try {
            isSuccesfull = DAOFactory.getUserDAO().addUser(newUser);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return isSuccesfull;
    }

    public boolean authenticate(String username, char[] password) throws ServiceException {
        boolean isAuthentic = false;
        byte[] hashedPassword = null;
        User existingUser = null;

        try {
            existingUser = DAOFactory.getUserDAO().findUserByUsername(username);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (existingUser != null) {
            hashedPassword = hashPasword(password, existingUser.getSalt());
            isAuthentic = Arrays.equals(existingUser.getPassword(), hashedPassword);
        }

        return isAuthentic;
    }

    private byte[] hashPasword(char[] password, byte[] salt) throws ServiceException {
        byte[] hashedPassword = null;

        KeySpec spec = new PBEKeySpec(password, salt, ITERATION_COUNT, KEY_LENGTH);

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(ALGORITHM);
            hashedPassword = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException(e);
        } catch (InvalidKeySpecException e) {
            throw new ServiceException(e);
        }

        return hashedPassword;
    }
}