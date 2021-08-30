package by.epamtc.zotov.finalproject.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.zotov.finalproject.dao.connection.ConnectionPool;
import by.epamtc.zotov.finalproject.entity.User;
import by.epamtc.zotov.finalproject.exception.DAOException;

public class UserDAOImplSQL implements UserDAO {
    private static final String ADD_USER = "INSERT INTO users(username, user_password, salt, email) VALUES (?, ?, ?, ?);";
    private static final String FIND_USER_BY_NAME = "SELECT * FROM users WHERE username=?";
    private static final String FIND_USER_TYPE_BY_USERNAME = "SELECT user_type FROM user_types LEFT JOIN users USING(user_type_id) WHERE username=?";
    private static final String FIND_USER_ID_BY_USERNAME = "SELECT user_id FROM users WHERE username=?";
    private static final String FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id=?";
    private static final String UPDATE_EMAIL = "UPDATE users SET email=? WHERE user_id=?;";
    private static final String UPDATE_PASSWORD = "UPDATE users SET user_password=? WHERE user_id=?;";
    private static final String UPDATE_TYPE = "UPDATE users SET user_type_id=? WHERE user_id=?;";
    private static final String FIND_USER_TYPE_ID_BY_TYPE_NAME = "SELECT user_type_id FROM user_types WHERE user_type=?";
    private static final String GET_USER_TYPES = "SELECT user_type FROM user_types";

    @Override
    public boolean addUser(User user) throws DAOException {
        boolean isSuccesfull = false;
        // TODO EXCEPTION WITH CONNECTION
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setBytes(2, user.getPassword());
            preparedStatement.setBytes(3, user.getSalt());
            preparedStatement.setString(4, user.getEmail());
            if (preparedStatement.executeUpdate() != 0) {
                isSuccesfull = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return isSuccesfull;
    }

    @Override
    public User findUserByUsername(String username) throws DAOException {
        User user = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_NAME);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                user = new User(result.getInt("user_id"), result.getInt("user_type_id"), result.getString("username"), result.getBytes("user_password"), result.getBytes("salt"),
                        result.getString("email"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return user;
    }

    @Override
    public String findUserTypeByUsername(String username) throws DAOException {
        String userType = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_TYPE_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                userType = result.getString("user_type");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return userType;
    }

    @Override
    public int findUserIdByUsername(String username) throws DAOException {
        int userId = 0;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_ID_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                userId = result.getInt("user_id");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return userId;
    }

    @Override
    public User findUserById(int targetId) throws DAOException {
        User targetUser = null;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID);
            preparedStatement.setInt(1, targetId);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                targetUser = new User(result.getInt("user_id"), result.getInt("user_type_id"), result.getString("username"),
                        result.getBytes("user_password"), result.getBytes("salt"), result.getString("email"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return targetUser;
    }

    @Override
    public boolean updateEmail(int targetId, String newEmail) throws DAOException {
        boolean isSuccesfull = false;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMAIL);
            preparedStatement.setString(1, newEmail);
            preparedStatement.setInt(2, targetId);
            if (preparedStatement.executeUpdate() != 0) {
                isSuccesfull = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return isSuccesfull;
    }

    @Override
    public boolean updatePassword(int targetId, byte[] newPassword) throws DAOException {
        boolean isSuccesfull = false;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD);
            preparedStatement.setBytes(1, newPassword);
            preparedStatement.setInt(2, targetId);
            if (preparedStatement.executeUpdate() != 0) {
                isSuccesfull = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        
        return isSuccesfull;
    }

    @Override
    public boolean updateType(int targetId, int typeId) throws DAOException {
        boolean isSuccesfull = false;

        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TYPE);
            preparedStatement.setInt(1, typeId);
            preparedStatement.setInt(2, targetId);
            if (preparedStatement.executeUpdate() != 0) {
                isSuccesfull = true;
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return isSuccesfull;
    }

    @Override
    public int findTypeIdByTypeName(String statusName) throws DAOException {
        int typeId = 0;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_TYPE_ID_BY_TYPE_NAME);
            preparedStatement.setString(1, statusName);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                typeId=result.getInt("user_type_id");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }
        return typeId;
    }

    @Override
    public List<String> getUserTypes() throws DAOException {
        List<String> userTypes= new ArrayList<>();

        Connection connection = ConnectionPool.getInstance().takeConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(GET_USER_TYPES);

            while (result.next()) {
                userTypes.add(result.getString("user_type"));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return userTypes;
    }
}