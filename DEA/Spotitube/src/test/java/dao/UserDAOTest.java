package dao;

import nld.spotitube.dao.UserDAO;
import nld.spotitube.exceptions.NoRowsAreEffectedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDAOTest {
    private TestConnection connectionBuilder =new TestConnection();
    private Connection databaseConnection;
    UserDAO userDAO = new UserDAO();

    @BeforeEach
    public void beforeEach(){
        try {
            databaseConnection = connectionBuilder.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            fail();
        }
        connectionBuilder.restoreDatabase();
    }

    @Test
    public void CheckUserExists(){
// Arrange
        String username = DatabaseObjects.user1.getUsername();
        String password = DatabaseObjects.user1.getUserpassword();

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        userDAO.setDataSource(connectionMock);

        // Act
        try {
            assertTrue(userDAO.CheckUserExists(username, password));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            fail();
        }

        // Assert
    }

    @Test
    public void CheckUserExistsBadFlow(){
        // Arrange
        String username = "something";
        String password = DatabaseObjects.user1.getUserpassword();

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        userDAO.setDataSource(connectionMock);

        // Act
        try {
            assertFalse(userDAO.CheckUserExists(username, password));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Assert
    }

    @Test
    public void setToken(){
        // Arrange
        String username = DatabaseObjects.user1.getUsername();
        String token = "TOKEN";

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        userDAO.setDataSource(connectionMock);

        // Act
        try {
            userDAO.SetToken(username, token);
        } catch (SQLException | NoRowsAreEffectedException throwables) {
            throwables.printStackTrace();
        }

        // Assert

        //check if it worked:
        String sql= "SELECT token FROM users where naam = "+username;
        try (Connection connection = connectionBuilder.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            //Assert
            boolean result =false;
            while(resultSet.next()){
                result = true;
                assertEquals(resultSet.getString("token"), token);
            }
            assertEquals(true, result);
        } catch (Exception e) {
        }
    }

    @Test
    public void CheckTokenExists(){
// Arrange
        String token = DatabaseObjects.user1.getToken();

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        userDAO.setDataSource(connectionMock);

        // Act
        try {
            assertTrue(userDAO.CheckTokenExists(token));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            fail();
        }

        // Assert
    }

    @Test
    public void CheckTokenExistsWithNotExistingToken(){
// Arrange
        String token = "TEST";

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        userDAO.setDataSource(connectionMock);

        // Act
        try {
            assertFalse(userDAO.CheckTokenExists(token));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            fail();
        }

        // Assert
    }
}
