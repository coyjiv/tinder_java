package dao;

import domain.Reaction;
import utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcReactionDao implements ReactionDao{
    @Override
    public boolean create(Reaction reaction) {
        String sql = "INSERT INTO reactions (user_id, target_user_id, type) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, reaction.getUserId());
            preparedStatement.setLong(2, reaction.getTargetUserId());
            preparedStatement.setString(3, reaction.getType());

            int changedRows = preparedStatement.executeUpdate();
            return changedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Optional<Reaction> findById(Long reactionId) {
        String sql = "SELECT * FROM reactions WHERE reaction_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, reactionId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Reaction reaction = new Reaction(resultSet.getLong("reaction_id"),
                        resultSet.getLong("user_id"),
                        resultSet.getLong("target_user_id"),
                        resultSet.getString("type"),
                        resultSet.getTimestamp("timestamp"));
                return Optional.of(reaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Reaction> findByUserId(Long userId) {
        List<Reaction> reactions = new ArrayList<>();
        String sql = "SELECT * FROM reactions WHERE user_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Reaction reaction = new Reaction(resultSet.getLong("reaction_id"),
                        resultSet.getLong("user_id"),
                        resultSet.getLong("target_user_id"),
                        resultSet.getString("type"),
                        resultSet.getTimestamp("timestamp"));
                reactions.add(reaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reactions;
    }


    @Override
    public boolean delete(Long reactionId) {
        return false;
    }
}
