package infrastructure.out;

import domain.entity.User;
import domain.service.UserService;
import infrastructure.config.DatabaseConfig;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements UserService {

    @Override
    public void createUser(User user) {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getLong(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findUserById(Long id) {
        String sql = "SELECT id, name, email FROM users WHERE id = ?";
        User user = null;

        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setEmail(resultSet.getString("email"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public void updateUser(User userUpdate) {
        String sql = "UPDATE users SET name = ?, email = ? WHERE id = ?";
        System.out.println(userUpdate.getId());
        try (Connection connection = DatabaseConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userUpdate.getName());
            statement.setString(2, userUpdate.getEmail());
            statement.setLong(3, userUpdate.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteUser(Long id) {
        String query = "DELETE FROM users WHERE id = ?";
        try  (Connection connection = DatabaseConfig.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)){
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
