package dao;

import domain.Message;
import utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMessageDao implements MessageDao{
    @Override
    public boolean create(Message message) {
        String sql = "INSERT INTO messages (sender_id, receiver_id, content) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, message.getSenderId());
            preparedStatement.setLong(2, message.getReceiverId());
            preparedStatement.setString(3, message.getContent());

            int changedRows = preparedStatement.executeUpdate();
            return changedRows > 0;
        } catch (SQLException e) {
            System.out.println("Something went wrong with creation message in DB");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<Message> findById(Integer messageId) {
        String sql = "SELECT * FROM messages WHERE message_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, messageId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Message message = new Message(resultSet.getLong("message_id"),
                                                resultSet.getLong("sender_id"),
                                                resultSet.getLong("receiver_id"),
                                                resultSet.getString("content"),
                                                resultSet.getTimestamp("timestamp")
                                            );
                return Optional.of(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Message> findAllBySenderId(Integer senderId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE sender_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, senderId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Message message = new Message(resultSet.getLong("message_id"),
                        resultSet.getLong("sender_id"),
                        resultSet.getLong("receiver_id"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("timestamp")
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public List<Message> findAllByReceiverId(Integer receiverId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE receiver_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, receiverId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Message message = new Message(resultSet.getLong("message_id"),
                        resultSet.getLong("sender_id"),
                        resultSet.getLong("receiver_id"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("timestamp")
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public List<Message> findAllBetweenUsers(Integer userId1, Integer userId2) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE (sender_id = ? AND receiver_id = ?) OR (sender_id = ? AND receiver_id = ?) ORDER BY timestamp";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, userId1);
            preparedStatement.setInt(2, userId2);
            preparedStatement.setInt(3, userId2);
            preparedStatement.setInt(4, userId1);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Message message = new Message(resultSet.getLong("message_id"),
                        resultSet.getLong("sender_id"),
                        resultSet.getLong("receiver_id"),
                        resultSet.getString("content"),
                        resultSet.getTimestamp("timestamp")
                );
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public boolean delete(Integer messageId) {
        return false;
    }
}
