package nld.spotitube.dao;

import nld.spotitube.domain.Track;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Default
public class UserDAO implements IUserDAO {

    @Resource(name = "jdbc/Spotitube")
    DataSource dataSource;

    @Override
    public boolean CheckUserExists(String user, String password) {
        String sql = "SELECT * FROM users Where Username = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String DBPassword = resultSet.getString("UserPassword");
                if (DBPassword.equals(password)) {
                    return true;
                }

            }
            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        //for safety
        return false;

    }

    @Override
    public void SetToken(String user, String token) {
        String sql = "UPDATE users SET Token = ? WHERE Username = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, token);
            statement.setString(2, user);
            int affectedRows = statement.executeUpdate();
            // todo make exception
        } catch (SQLException exception) {
            exception.printStackTrace();
        }


    }

    @Override
    public boolean CheckToken(String user, String token) {
        String sql = "select token from user where Username = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("token").equals(token)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        //for safety
        return false;

    }

    @Override
    public boolean CheckTokenExists(String token) {
        String sql = "select token from users where token = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, token);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("token").equals(token)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        //for safety
        return false;

    }
}
