package by.epamtc.zotov.finalproject.dao.card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import by.epamtc.zotov.finalproject.dao.connection.ConnectionPool;
import by.epamtc.zotov.finalproject.entity.Card;
import by.epamtc.zotov.finalproject.exception.DAOException;

public class CardDAOimplSQL implements CardDAO {
    private static final String GET_ALL = "SELECT * FROM cards";
    private static final String FIND_CARD_BY_ID = "SELECT * FROM cards WHERE card_id=?";
    private static final String FIND_CARD_BY_USER_ID = "SELECT * FROM cards WHERE user_id=?";
    private static final String FIND_STATUS_BY_STATUS_ID = "SELECT status_name FROM cards LEFT JOIN card_statuses USING(status_id) WHERE status_id=?";
    private static final String ADD_CARD = "INSERT INTO public.cards(user_id, holder_name) VALUES (?,?)";

    @Override
    public List<Card> getAllCards() throws DAOException {
        List<Card> cards = new ArrayList<>();
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(GET_ALL);

            while (result.next()) {
                cards.add(new Card(result.getInt("card_id"), result.getInt("user_id"), result.getString("holder_name"),
                        result.getInt("status_id")));
            }

        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return cards;
    }

    @Override
    public Card findCardById(int targetId) throws DAOException {
        Card card = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CARD_BY_ID);
            preparedStatement.setInt(1, targetId);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                card = new Card(result.getInt("card_id"), result.getInt("user_id"), result.getString("holder_name"),
                        result.getInt("status_id"));
            }

        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return card;
    }

    @Override
    public Card findCardByUserId(int targetId) throws DAOException {
        Card card = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_CARD_BY_USER_ID);
            preparedStatement.setInt(1, targetId);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                card = new Card(result.getInt("card_id"), result.getInt("user_id"), result.getString("holder_name"),
                        result.getInt("status_id"));
            }

        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return card;
    }

    public boolean addCard(Card newCard) throws DAOException {
        boolean isSuccesfull = false;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CARD);
            preparedStatement.setInt(1, newCard.getUserId());
            preparedStatement.setString(2, newCard.getHolderName());
            if (preparedStatement.executeUpdate() != 0) {
                isSuccesfull = true;
            }
        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return isSuccesfull;
    }

    @Override
    public String findStatusByStatusId(int statusId) throws DAOException {
        String status = null;
        Connection connection = null;

        try {
            connection = ConnectionPool.getInstance().takeConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(FIND_STATUS_BY_STATUS_ID);
            preparedStatement.setInt(1, statusId);
            ResultSet result = preparedStatement.executeQuery();

            if (result.next()) {
                status = result.getString("status_name");
            }
        } catch (SQLException e) {
            throw new DAOException("Database exception", e);
        } catch (InterruptedException e) {
            throw new DAOException("Interrupted while aquiring connection", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }

        return status;
    }
}