package dao;

import nld.spotitube.dao.PlaylistsDAO;
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

public class PlaylistsDAOTest {
    private TestConnection connectionBuilder =new TestConnection();
    private Connection databaseConnection;
    PlaylistsDAO playlistsDAO = new PlaylistsDAO();

    @BeforeEach
    public void beforeEach(){
        try {
            databaseConnection = connectionBuilder.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        connectionBuilder.restoreDatabase();
    }

//    @Test
//    public void getPlaylists(){
//        // Arrange
//        String Token = DatabaseObjects.user1.getToken();
//
//        //mock
//        DataSource connectionMock = mock(DataSource.class);
//        try {
//            when(connectionMock.getConnection()).thenReturn(databaseConnection);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        playlistsDAO.setDataSource(connectionMock);
//
//        // Act
//        try {
//            playlistsDAO.getPlaylists(Token);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        fail();
//
//
//    }


    @Test
    public void Addplaylist(){
        // Arrange
        String playlistName = "Non-Stop-Pop";
        String Token = DatabaseObjects.user1.getToken();

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        playlistsDAO.setDataSource(connectionMock);

        // Act
        try {
            playlistsDAO.addPlaylist(playlistName, Token);
        } catch (SQLException | NoRowsAreEffectedException throwables) {
            throwables.printStackTrace();
        }

        //check if it worked:
        String sql= "SELECT token FROM playlist p join users u on p.eigenaar = u.id where naam = "+playlistName;
        try (Connection connection = connectionBuilder.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            //Assert
            boolean result =false;
            while(resultSet.next()){
                result = true;
                assertEquals(resultSet.getString("token"), Token);
            }
            assertEquals(true,result);
        } catch (Exception e) {
        }
    }

    @Test
    public void deletePlaylist(){
        // Arrange
        int playlistID = 10;

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        playlistsDAO.setDataSource(connectionMock);

        // Act
        try {
            playlistsDAO.deletePlaylist(playlistID);
        } catch (SQLException | NoRowsAreEffectedException throwables) {
            throwables.printStackTrace();
        }

        //check if it worked:
        String sql= "SELECT * FROM playlist where id = "+playlistID;
        try (Connection connection = connectionBuilder.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            //Assert
            while(resultSet.next()){
                assertEquals(true,false);
            }
        } catch (Exception e) {
        }
    }

    @Test
    public void updatePlaylist(){
        // Arrange
        int playlistID = 10;
        String playlistName = "Non-stop-pop!";

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        playlistsDAO.setDataSource(connectionMock);

        // Act
        try {
            playlistsDAO.updatePlaylist(playlistName, playlistID);
        } catch (SQLException | NoRowsAreEffectedException throwables) {
            throwables.printStackTrace();
        }

        //check if it worked:
        boolean result =false;
        String resultNaam ="";
        String sql= "SELECT Naam FROM playlist where id = "+playlistID + ";";
        try (Connection connection = connectionBuilder.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            //Assert

            while(resultSet.next()){
                result = true;
                resultNaam = resultSet.getString("Naam");

            }
        } catch (Exception e) {
        }
        assertEquals(true,result);
        assertEquals(playlistName,resultNaam );
    }





    @Test
    public void getTokenofOwner(){
        //arrange
        String token = DatabaseObjects.user1.getToken();
        int playlistId = DatabaseObjects.playlist1.getId();

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        playlistsDAO.setDataSource(connectionMock);

        //act
        String responseToken= null;
        try {
            responseToken = playlistsDAO.getTokenOfOwner(playlistId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //assert
        assertEquals(token, responseToken);

    }

}
