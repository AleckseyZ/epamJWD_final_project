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
    private static final String ADD_CARD = "INSERT INTO public.cards(user_id, holder_name, holder_phone, issue_date) VALUES (?, ?, ?, ?)";

    @Override
    public List<Card> getAllCards() throws DAOException {
        List<Card> cards = new ArrayList<>();

        Connection connection = ConnectionPool.getInstance().takeConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(GET_ALL);

            while (result.next()) {
                cards.add(new Card(result.getInt("user_id"), result.getString("holder_name"),
                        result.getString("holder_phone"), result.getDate("issue_date")));
            }

        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            ConnectionPool.getInstance().releaseConnection(connection);
        }

        return cards;
    }

    public boolean addCard(Card newCard) throws DAOException {
        boolean isSuccesfull = false;
        Connection connection = ConnectionPool.getInstance().takeConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_CARD);
            preparedStatement.setInt(1, newCard.getUserId());
            preparedStatement.setString(2, newCard.getHolderName());
            preparedStatement.setString(3, newCard.getHolderPhone());
            preparedStatement.setDate(4, newCard.getIssueDate());
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
}