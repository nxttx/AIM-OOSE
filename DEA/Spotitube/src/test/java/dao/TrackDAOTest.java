package dao;

import nld.spotitube.dao.TrackDAO;
import nld.spotitube.domain.Track;
import nld.spotitube.exceptions.NoRowsAreEffectedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TrackDAOTest {

    private TestConnection connectionBuilder =new TestConnection();
    private Connection databaseConnection;
    TrackDAO trackDAO = new TrackDAO();

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
    public void getAllTracks(){
        // Arrange

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        trackDAO.setDataSource(connectionMock);

        // Act
        ArrayList<Track> responseTracks= null;
        try {
            responseTracks = trackDAO.getAllTracks();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Assert
        assertEquals(DatabaseObjects.track1.getId(), responseTracks.get(0).getId());
        assertEquals(DatabaseObjects.track1.getDescription(), responseTracks.get(0).getDescription());
        assertEquals(DatabaseObjects.track1.getDuration(), responseTracks.get(0).getDuration());
        assertEquals(DatabaseObjects.track1.getPlaycount(), responseTracks.get(0).getPlaycount());
        assertEquals(false, responseTracks.get(0).getOfflineAvailable());
        assertEquals(DatabaseObjects.track1.getPerformer(), responseTracks.get(0).getPerformer());
        assertEquals(DatabaseObjects.track1.getPublicationDate(), responseTracks.get(0).getPublicationDate());
        assertEquals(DatabaseObjects.track1.getTitle(), responseTracks.get(0).getTitle());


        assertEquals(DatabaseObjects.track2.getId(), responseTracks.get(1).getId());
        assertEquals(DatabaseObjects.track2.getDescription(), responseTracks.get(1).getDescription());
        assertEquals(DatabaseObjects.track2.getDuration(), responseTracks.get(1).getDuration());
        assertEquals(DatabaseObjects.track2.getPlaycount(), responseTracks.get(1).getPlaycount());
        assertEquals(false, responseTracks.get(1).getOfflineAvailable());
        assertEquals(DatabaseObjects.track2.getPerformer(), responseTracks.get(1).getPerformer());
        assertEquals(DatabaseObjects.track2.getPublicationDate(), responseTracks.get(1).getPublicationDate());
        assertEquals(DatabaseObjects.track2.getTitle(), responseTracks.get(1).getTitle());
        
        assertEquals(DatabaseObjects.track3.getId(), responseTracks.get(2).getId());
        assertEquals(DatabaseObjects.track3.getDescription(), responseTracks.get(2).getDescription());
        assertEquals(DatabaseObjects.track3.getDuration(), responseTracks.get(2).getDuration());
        assertEquals(DatabaseObjects.track3.getPlaycount(), responseTracks.get(2).getPlaycount());
        assertEquals(false, responseTracks.get(2).getOfflineAvailable());
        assertEquals(DatabaseObjects.track3.getPerformer(), responseTracks.get(2).getPerformer());
        assertEquals(DatabaseObjects.track3.getPublicationDate(), responseTracks.get(2).getPublicationDate());
        assertEquals(DatabaseObjects.track3.getTitle(), responseTracks.get(2).getTitle());

    }

    @Test
    public void getTrack(){
        // Arrange
        int trackId = 1;

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        trackDAO.setDataSource(connectionMock);

        // Act
        Track responseTracks= null;
        try {
            responseTracks = trackDAO.getTrack(trackId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Assert
        assertEquals(DatabaseObjects.track1.getId(), responseTracks.getId());
        assertEquals(DatabaseObjects.track1.getDescription(), responseTracks.getDescription());
        assertEquals(DatabaseObjects.track1.getDuration(), responseTracks.getDuration());
        assertEquals(DatabaseObjects.track1.getPlaycount(), responseTracks.getPlaycount());
        assertEquals(false, responseTracks.getOfflineAvailable());
        assertEquals(DatabaseObjects.track1.getPerformer(), responseTracks.getPerformer());
        assertEquals(DatabaseObjects.track1.getPublicationDate(), responseTracks.getPublicationDate());
        assertEquals(DatabaseObjects.track1.getTitle(), responseTracks.getTitle());
    }

    @Test
    public void getTracksFromPlaylist(){
        // Arrange
        int playlistID = 10;

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        trackDAO.setDataSource(connectionMock);

        // Act
        ArrayList<Track> responseTracks= null;
        try {
            responseTracks = trackDAO.getTracksFromPlaylist(playlistID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Assert
        assertEquals(DatabaseObjects.track1.getId(), responseTracks.get(0).getId());
        assertEquals(DatabaseObjects.track1.getDescription(), responseTracks.get(0).getDescription());
        assertEquals(DatabaseObjects.track1.getDuration(), responseTracks.get(0).getDuration());
        assertEquals(DatabaseObjects.track1.getPlaycount(), responseTracks.get(0).getPlaycount());
        assertEquals(false, responseTracks.get(0).getOfflineAvailable());
        assertEquals(DatabaseObjects.track1.getPerformer(), responseTracks.get(0).getPerformer());
        assertEquals(DatabaseObjects.track1.getPublicationDate(), responseTracks.get(0).getPublicationDate());
        assertEquals(DatabaseObjects.track1.getTitle(), responseTracks.get(0).getTitle());


        assertEquals(DatabaseObjects.track3.getId(), responseTracks.get(1).getId());
        assertEquals(DatabaseObjects.track3.getDescription(), responseTracks.get(1).getDescription());
        assertEquals(DatabaseObjects.track3.getDuration(), responseTracks.get(1).getDuration());
        assertEquals(DatabaseObjects.track3.getPlaycount(), responseTracks.get(1).getPlaycount());
        assertEquals(true, responseTracks.get(1).getOfflineAvailable());
        assertEquals(DatabaseObjects.track3.getPerformer(), responseTracks.get(1).getPerformer());
        assertEquals(DatabaseObjects.track3.getPublicationDate(), responseTracks.get(1).getPublicationDate());
        assertEquals(DatabaseObjects.track3.getTitle(), responseTracks.get(1).getTitle());

    }

    @Test
    public void getTracksNotInPlaylist(){
        // Arrange
        int playlistID = 10;

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        trackDAO.setDataSource(connectionMock);

        // Act
        ArrayList<Track> responseTracks= null;
        try {
            responseTracks = trackDAO.getTracksNotInPlaylist(playlistID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Assert
        assertEquals(DatabaseObjects.track2.getId(), responseTracks.get(0).getId());
        assertEquals(DatabaseObjects.track2.getDescription(), responseTracks.get(0).getDescription());
        assertEquals(DatabaseObjects.track2.getDuration(), responseTracks.get(0).getDuration());
        assertEquals(DatabaseObjects.track2.getPlaycount(), responseTracks.get(0).getPlaycount());
        assertEquals(false, responseTracks.get(0).getOfflineAvailable());
        assertEquals(DatabaseObjects.track2.getPerformer(), responseTracks.get(0).getPerformer());
        assertEquals(DatabaseObjects.track2.getPublicationDate(), responseTracks.get(0).getPublicationDate());
        assertEquals(DatabaseObjects.track2.getTitle(), responseTracks.get(0).getTitle());

    }

    @Test
    public void addTracksToPlaylist(){
        // Arrange
        int playlistID = 10;
        int trackID = 2;

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        trackDAO.setDataSource(connectionMock);

        // Act
        try {
            trackDAO.addTrackToPlaylist(playlistID, trackID, true);
        } catch (SQLException | NoRowsAreEffectedException throwables) {
            throwables.printStackTrace();
        }

        //check if it worked:
        String sql= "SELECT * FROM track_in_playlist where Playlist_id = "+playlistID+" AND Track_id = "+trackID;
        try (Connection connection = connectionBuilder.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            int amount =1;
            //Assert
            while(resultSet.next()){
                assertTrue(amount< 2);
                amount++;
            }
        } catch (Exception e) {
        }
    }

    @Test
    public void addTracksToPlaylistErrorTest(){
        // Arrange
        int playlistID = 10;
        int trackID = 3;

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        trackDAO.setDataSource(connectionMock);

        // Act
        //Assert
        assertThrows(
                SQLException.class,
                () -> trackDAO.addTrackToPlaylist(playlistID, trackID, true)
        );


    }


    @Test
    public void deleteTrackFromPlaylist(){
        // Arrange
        int playlistID = 10;
        int trackID = 1;

        //mock
        DataSource connectionMock = mock(DataSource.class);
        try {
            when(connectionMock.getConnection()).thenReturn(databaseConnection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        trackDAO.setDataSource(connectionMock);

        // Act
        try {
            trackDAO.deleteTrackFromPlaylist(playlistID, trackID);
        } catch (SQLException | NoRowsAreEffectedException throwables) {
            throwables.printStackTrace();
        }

        //check if it worked:
        String sql= "SELECT * FROM track_in_playlist where Playlist_id = "+playlistID+" AND Track_id = "+trackID;
        try (Connection connection = connectionBuilder.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            //Assert
            while(resultSet.next()){
                fail();
            }
        } catch (Exception e) {
        }
    }
}
