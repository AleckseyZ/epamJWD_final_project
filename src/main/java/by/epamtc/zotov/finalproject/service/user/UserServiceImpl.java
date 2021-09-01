package by.epamtc.zotov.finalproject.service.user;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.List;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import by.epamtc.zotov.finalproject.dao.DAOFactory;
import by.epamtc.zotov.finalproject.dao.user.UserDAO;
import by.epamtc.zotov.finalproject.entity.User;
import by.epamtc.zotov.finalproject.exception.DAOException;
import by.epamtc.zotov.finalproject.exception.ServiceException;

public class UserServiceImpl implements UserService {
    private final static int SALT_SIZE = 32;

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
            isSuccesfull = DAOFactory.getInstance().getUserDAO().addUser(newUser);
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
            existingUser = DAOFactory.getInstance().getUserDAO().findUserByUsername(username);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        if (existingUser != null) {
            hashedPassword = hashPasword(password, existingUser.getSalt());
            isAuthentic = Arrays.equals(existingUser.getPassword(), hashedPassword);
            Arrays.fill(password, '0');
        }

        return isAuthentic;
    }

    private byte[] hashPasword(char[] password, byte[] salt) throws ServiceException {
        int ITERATION_COUNT = 10000;
        int KEY_LENGTH = 256;
        String ALGORITHM = "PBKDF2WithHmacSHA1";
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

    @Override
    public boolean updatePassword(int targetId, char[] password, char[] newPassword, char[] repeatPassword)
            throws ServiceException {
        boolean isSuccesfull = false;

        if (Arrays.equals(newPassword, repeatPassword)) {
            Arrays.fill(repeatPassword, '0');
            try {
                User user = DAOFactory.getInstance().getUserDAO().findUserById(targetId);
                String username = user.getUsername();
                boolean isAuthentic = authenticate(username, password);
                Arrays.fill(password, '0');

                if (isAuthentic) {
                    byte[] hashedPassword = hashPasword(newPassword, user.getSalt());
                    Arrays.fill(newPassword, '0');
                    isSuccesfull = DAOFactory.getInstance().getUserDAO().updatePassword(targetId, hashedPassword);
                }
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }

        return isSuccesfull;
    }

    @Override
    public boolean updateEmail(int targetId, String newEmail) throws ServiceException {
        boolean isSuccesfull = false;

        try {
            isSuccesfull = DAOFactory.getInstance().getUserDAO().updateEmail(targetId, newEmail);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return isSuccesfull;
    }

    @Override
    public boolean updateStatus(int targetId, String newType) throws ServiceException {
        boolean isSuccesfull = false;
        UserDAO userDAO= DAOFactory.getInstance().getUserDAO();
        int typeId;

        try {
            typeId = userDAO.findTypeIdByTypeName(newType);
        } catch (DAOException e) {
            throw new ServiceException("Exception while retriving user type by type name",e);
        }

        try {
            isSuccesfull = userDAO.updateType(targetId, typeId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return isSuccesfull;
    }

    @Override
    public String findUserTypeByUsername(String username) throws ServiceException {
        String userType = null;

        try {
            userType = DAOFactory.getInstance().getUserDAO().findUserTypeByUsername(username);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return userType;
    }

    @Override
    public int findUserIdByUsername(String username) throws ServiceException {
        int userId = 0;

        try {
            userId = DAOFactory.getInstance().getUserDAO().findUserIdByUsername(username);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return userId;
    }

    @Override
    public User findUserById(int targetId) throws ServiceException {
        User targetUser = null;

        try {
            targetUser = DAOFactory.getInstance().getUserDAO().findUserById(targetId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return targetUser;
    }
    
    @Override
    public User findUserByUsername(String username) throws ServiceException {
        User targetUser = null;

        try {
            targetUser = DAOFactory.getInstance().getUserDAO().findUserByUsername(username);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return targetUser;
    }

    @Override
    public List<String> getUserTypes() throws ServiceException {
        List<String> userTypes=null;

        try {
            userTypes = DAOFactory.getInstance().getUserDAO().getUserTypes();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

        return userTypes;
    }
}