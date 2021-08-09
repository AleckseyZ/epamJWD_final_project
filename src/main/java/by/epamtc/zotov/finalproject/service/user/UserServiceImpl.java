package by.epamtc.zotov.finalproject.service.user;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import by.epamtc.zotov.finalproject.dao.DAOProvider;
import by.epamtc.zotov.finalproject.entity.User;
import by.epamtc.zotov.finalproject.exception.ServiceException;

public class UserServiceImpl implements UserService {
    private final static int SALT_SIZE = 32;
    private final static int ITERATION_COUNT = 10000;
    private final static int KEY_LENGTH = 256;
    private final static String ALGORITHM = "PBKDF2WithHmacSHA1";

    @Override
    public boolean addUser(String username, char[] password, String email) throws ServiceException {
        boolean isSuccesfull = false;

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_SIZE];
        random.nextBytes(salt);

        User newUser = new User(username, new String(hashPasword(password, salt)), new String(salt), email);
        Arrays.fill(password, '0');

        try {
            isSuccesfull = DAOProvider.getUserDAO().addUser(newUser);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return isSuccesfull;
    }

    public boolean authenticate(String username, char[] password) {
        boolean isAuthentic = false;
        byte[] passwordHash=null;
        User existingUser = null;

        try {
            existingUser = DAOProvider.getUserDAO().findUserByUsername(username);
        } catch (Exception e) {
            // TODO: handle exception
        }

        if (existingUser != null) {
            try {
                passwordHash = hashPasword(password, existingUser.getSalt().getBytes());
            } catch (Exception e) {
                // TODO handle exception
            }
            isAuthentic = Arrays.equals(existingUser.getPassword().getBytes(), passwordHash);
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

        } catch (InvalidKeySpecException e) {
            // TODO fix exceptions
        }

        return hashedPassword;
    }
}