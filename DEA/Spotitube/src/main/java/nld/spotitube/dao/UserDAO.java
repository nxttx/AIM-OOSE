package nld.spotitube.dao;


import nld.spotitube.exceptions.NoRowsAreEffectedException;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Default
public class UserDAO implements IUserDAO {

    @Resource(name = "jdbc/Spotitube")
    DataSource dataSource;

    @Override
    public boolean CheckUserExists(String user, String password) throws SQLException {
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
            throw exception;
        }

    }

    @Override
    public void SetToken(String user, String token) throws NoRowsAreEffectedException, SQLException {
        String sql = "UPDATE users SET Token = ? WHERE Username = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, token);
            statement.setString(2, user);
            int affectedRows = statement.executeUpdate();
            if(affectedRows <1){
                throw new NoRowsAreEffectedException();
            }
        } catch (SQLException exception) {
            throw exception;
        }


    }

    @Override
    public boolean CheckToken(String user, String token) throws SQLException {
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
            throw exception;
        }

    }

    @Override
    public boolean CheckTokenExists(String token) throws SQLException {
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
            throw exception;
        }

    }
}
