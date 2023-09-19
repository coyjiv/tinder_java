package dao;

import domain.User;
import utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcUserDao implements UserDao{
    @Override
    public boolean create(User user) {
        String sql = "INSERT INTO users (user_id, username, profile_photo, password) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, user.getUserId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getProfilePhoto());
            preparedStatement.setString(4, user.getPassword());

            int changedRows = preparedStatement.executeUpdate();
            return changedRows > 0;
        } catch (SQLException e) {
            System.out.println("Something went wrong with creation user in DB");
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean update(User user) {
        String sql = "UPDATE users SET username = ?, profile_photo = ?, password = ? WHERE user_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getProfilePhoto());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getUserId());

            int changedRows = preparedStatement.executeUpdate();
            return changedRows > 0;
        } catch (SQLException e) {
            System.out.println("Something went wrong with updating user in DB");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Integer userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, userId);

            int changedRows = preparedStatement.executeUpdate();
            return changedRows > 0;
        } catch (SQLException e) {
            System.out.println("Something went wrong with deleting user from DB");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Optional<User> findById(Integer userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User(resultSet.getLong("user_id"),
                                    resultSet.getString("username"),
                                    resultSet.getString("password"),
                                    resultSet.getString("profile_photo")
                                    );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong with fetching user by ID from DB");
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        // Similar to findById but with a WHERE condition on username
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                User user = new User(resultSet.getLong("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("profile_photo")
                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong with fetching user by ID from DB");
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = DatabaseUtil.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                User user = new User(resultSet.getLong("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("profile_photo")
                );
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Something went wrong with fetching all users from DB");
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public List<User> findLikedUsersByUserId(Long userId) {
        List<User> likedUsers = new ArrayList<>();
        String sql = """
            SELECT u.* 
            FROM users u 
            JOIN reactions r ON u.user_id = r.target_user_id
            WHERE r.user_id = ? AND r.type = 'like'
            """;

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User(resultSet.getLong("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("profile_photo"));
                likedUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likedUsers;
    }
}
