package by.epamtc.zotov.finalproject.dao.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epamtc.zotov.finalproject.dao.connection.ConnectionPool;
import by.epamtc.zotov.finalproject.entity.User;
import by.epamtc.zotov.finalproject.exception.DAOException;

public class UserDAOImplSQL implements UserDAO {
    private static final String ADD_USER = "INSERT INTO users(username, user_password, salt, email) VALUES (?, ?, ?, ?);";
    private static final String FIND_USER_BY_NAME = "SELECT * FROM users WHERE username=?";
    private static final String FIND_USER_TYPE_BY_USERNAME = "SELECT user_type FROM user_types LEFT JOIN users USING(user_type_id) WHERE username=?";

    @Override
    public boolean addUser(User user) throws DAOException {
        boolean isSuccesfull = false;
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
                user = new User(result.getString("username"), result.getBytes("user_password"), result.getBytes("salt"),
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
    public String getUserTypeByUsername(String username) throws DAOException {
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
}