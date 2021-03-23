package service;

import nld.spotitube.dao.PlaylistsDAO;
import nld.spotitube.dao.TrackDAO;
import nld.spotitube.domain.Playlist;
import nld.spotitube.domain.Track;
import nld.spotitube.exceptions.PlaylistNoNameException;
import nld.spotitube.service.PlaylistsROUTE;
import nld.spotitube.domain.Playlists;
import nld.spotitube.service.dto.PlaylistsDTO;
import nld.spotitube.service.dto.TracksDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class PlaylistsROUTETest {

    private PlaylistsROUTE playlists;

    @BeforeEach
    public void setUp() {
        playlists = new PlaylistsROUTE();
    }

    @Test
    public void getPlaylists() {
        // Arrange
        int statuscodeExpected = 200;
        int playlistAId = 0;
        int playlistBId = 1;
        String playlistAName = "Death metal";
        String playlistBName = "Pop";
        boolean playlistAOwner = false;
        boolean playlistBOwner = true;
        int trackId = 1;
        String trackTitle = "title";
        String trackPerformer = "Somebody";
        String trackDescription = "a description";
        String trackAlbum = "a album";
        int trackDuration = 1;
        int trackPlaycount = 1;
        String trackPublicationDate = "10-02-2121";
        boolean trackOfflineAvailable = false;


        ArrayList Tracks = new ArrayList<Track>();
        Track track = new Track();
        track.setId(trackId);
        track.setTitle(trackTitle);
        track.setPerformer(trackPerformer);
        track.setDuration(trackDuration);
        track.setAlbum(trackAlbum);
        track.setPlaycount(trackPlaycount);
        track.setPublicationDate(trackPublicationDate);
        track.setDescription(trackDescription);
        track.setOfflineAvailable(trackOfflineAvailable);
        Tracks.add(track);


        Playlists playlistsResult = new Playlists();
        Playlist playlistA = new Playlist(playlistAId, playlistAName, playlistAOwner, Tracks);
        Playlist playlistB = new Playlist(playlistBId, playlistBName, playlistBOwner, Tracks);

        var playLists = new ArrayList<Playlist>();
        playLists.add(playlistA);
        playLists.add(playlistB);

        playlistsResult.setPlaylists(playLists);
        playlistsResult.setLength(0);

        PlaylistsDAO playlistsDAOMock = mock(PlaylistsDAO.class);
        try {
            when(playlistsDAOMock.getPlaylists("1")).thenReturn(playlistsResult);
        } catch (Exception e) {

        }
        playlists.setPlaylistsDAO(playlistsDAOMock);


        // Act
        Response response = null;
        try {
            response = playlists.getPlaylists("1");
        } catch (Exception e) {

        }
        PlaylistsDTO playlistsDTO = (PlaylistsDTO) response.getEntity();

        // Assert
        assertEquals(statuscodeExpected, response.getStatus());

        //test contents
        assertEquals(playLists.get(0).getId(), playlistsDTO.playlists.get(0).id);
        assertEquals(playLists.get(1).getId(), playlistsDTO.playlists.get(1).id);
        assertEquals(playLists.get(0).getName(), playlistsDTO.playlists.get(0).name);
        assertEquals(playLists.get(1).getName(), playlistsDTO.playlists.get(1).name);
        assertEquals(playLists.get(0).getOwner(), playlistsDTO.playlists.get(0).owner);
        assertEquals(playLists.get(1).getOwner(), playlistsDTO.playlists.get(1).owner);

        assertEquals(playLists.get(0).getTracks().get(0).getId(), playlistsDTO.playlists.get(0).tracks.get(0).id);
        assertEquals(playLists.get(0).getTracks().get(0).getDescription(), playlistsDTO.playlists.get(0).tracks.get(0).description);
        assertEquals(playLists.get(0).getTracks().get(0).getDuration(), playlistsDTO.playlists.get(0).tracks.get(0).duration);
        assertEquals(playLists.get(0).getTracks().get(0).getPlaycount(), playlistsDTO.playlists.get(0).tracks.get(0).playcount);
        assertEquals(playLists.get(0).getTracks().get(0).getOfflineAvailable(), playlistsDTO.playlists.get(0).tracks.get(0).offlineAvailable);
        assertEquals(playLists.get(0).getTracks().get(0).getPerformer(), playlistsDTO.playlists.get(0).tracks.get(0).performer);
        assertEquals(playLists.get(0).getTracks().get(0).getPublicationDate(), playlistsDTO.playlists.get(0).tracks.get(0).publicationDate);
        assertEquals(playLists.get(0).getTracks().get(0).getTitle(), playlistsDTO.playlists.get(0).tracks.get(0).title);


        assertEquals(playLists.get(1).getTracks().get(0).getId(), playlistsDTO.playlists.get(1).tracks.get(0).id);
        assertEquals(playLists.get(1).getTracks().get(0).getDescription(), playlistsDTO.playlists.get(1).tracks.get(0).description);
        assertEquals(playLists.get(1).getTracks().get(0).getDuration(), playlistsDTO.playlists.get(1).tracks.get(0).duration);
        assertEquals(playLists.get(1).getTracks().get(0).getPlaycount(), playlistsDTO.playlists.get(1).tracks.get(0).playcount);
        assertEquals(playLists.get(1).getTracks().get(0).getOfflineAvailable(), playlistsDTO.playlists.get(1).tracks.get(0).offlineAvailable);
        assertEquals(playLists.get(1).getTracks().get(0).getPerformer(), playlistsDTO.playlists.get(1).tracks.get(0).performer);
        assertEquals(playLists.get(1).getTracks().get(0).getPublicationDate(), playlistsDTO.playlists.get(1).tracks.get(0).publicationDate);
        assertEquals(playLists.get(1).getTracks().get(0).getTitle(), playlistsDTO.playlists.get(1).tracks.get(0).title);


    }

    @Test
    public void deletePlaylists() {
        // Arrange
        int statuscodeExpected = 201;
        final int PLAYLIST_NUMBER = 1;

        //Mock for getPlaylists

        int playlistAId = 0;
        int playlistBId = 1;
        String playlistAName = "Death metal";
        String playlistBName = "Pop";
        boolean playlistAOwner = false;
        boolean playlistBOwner = true;
        int trackId = 1;
        String trackTitle = "title";
        String trackPerformer = "Somebody";
        String trackDescription = "a description";
        String trackAlbum = "a album";
        int trackDuration = 1;
        int trackPlaycount = 1;
        String trackPublicationDate = "10-02-2121";
        boolean trackOfflineAvailable = false;


        ArrayList Tracks = new ArrayList<Track>();
        Track track = new Track();
        track.setId(trackId);
        track.setTitle(trackTitle);
        track.setPerformer(trackPerformer);
        track.setDuration(trackDuration);
        track.setAlbum(trackAlbum);
        track.setPlaycount(trackPlaycount);
        track.setPublicationDate(trackPublicationDate);
        track.setDescription(trackDescription);
        track.setOfflineAvailable(trackOfflineAvailable);
        Tracks.add(track);


        Playlists playlistsResult = new Playlists();
        Playlist playlistA = new Playlist(playlistAId, playlistAName, playlistAOwner, Tracks);
        Playlist playlistB = new Playlist(playlistBId, playlistBName, playlistBOwner, Tracks);

        var playLists = new ArrayList<Playlist>();
        playLists.add(playlistA);
        playLists.add(playlistB);

        playlistsResult.setPlaylists(playLists);
        playlistsResult.setLength(0);


        // mock
        PlaylistsDAO playlistsDAOMock = mock(PlaylistsDAO.class);
        try {
            doNothing().when(playlistsDAOMock).deletePlaylist(PLAYLIST_NUMBER);
        } catch (Exception e) {
        }
        playlists.setPlaylistsDAO(playlistsDAOMock);


        PlaylistsDAO playlistsDAOMock2 = mock(PlaylistsDAO.class);
        try {
            when(playlistsDAOMock2.getPlaylists("1")).thenReturn(playlistsResult);
        } catch (Exception e) {
        }
        playlists.setPlaylistsDAO(playlistsDAOMock2);

        // Act
        Response response = null;
        try {
            response = playlists.deletePlaylists("1", PLAYLIST_NUMBER);
        } catch (Exception e) {
        }
        PlaylistsDTO playlistsDTO = (PlaylistsDTO) response.getEntity();

        // Assert
        assertEquals(statuscodeExpected, response.getStatus());
        //test contents
        assertEquals(playLists.get(0).getId(), playlistsDTO.playlists.get(0).id);
        assertEquals(playLists.get(1).getId(), playlistsDTO.playlists.get(1).id);
        assertEquals(playLists.get(0).getName(), playlistsDTO.playlists.get(0).name);
        assertEquals(playLists.get(1).getName(), playlistsDTO.playlists.get(1).name);
        assertEquals(playLists.get(0).getOwner(), playlistsDTO.playlists.get(0).owner);
        assertEquals(playLists.get(1).getOwner(), playlistsDTO.playlists.get(1).owner);

        assertEquals(playLists.get(0).getTracks().get(0).getId(), playlistsDTO.playlists.get(0).tracks.get(0).id);
        assertEquals(playLists.get(0).getTracks().get(0).getDescription(), playlistsDTO.playlists.get(0).tracks.get(0).description);
        assertEquals(playLists.get(0).getTracks().get(0).getDuration(), playlistsDTO.playlists.get(0).tracks.get(0).duration);
        assertEquals(playLists.get(0).getTracks().get(0).getPlaycount(), playlistsDTO.playlists.get(0).tracks.get(0).playcount);
        assertEquals(playLists.get(0).getTracks().get(0).getOfflineAvailable(), playlistsDTO.playlists.get(0).tracks.get(0).offlineAvailable);
        assertEquals(playLists.get(0).getTracks().get(0).getPerformer(), playlistsDTO.playlists.get(0).tracks.get(0).performer);
        assertEquals(playLists.get(0).getTracks().get(0).getPublicationDate(), playlistsDTO.playlists.get(0).tracks.get(0).publicationDate);
        assertEquals(playLists.get(0).getTracks().get(0).getTitle(), playlistsDTO.playlists.get(0).tracks.get(0).title);


        assertEquals(playLists.get(1).getTracks().get(0).getId(), playlistsDTO.playlists.get(1).tracks.get(0).id);
        assertEquals(playLists.get(1).getTracks().get(0).getDescription(), playlistsDTO.playlists.get(1).tracks.get(0).description);
        assertEquals(playLists.get(1).getTracks().get(0).getDuration(), playlistsDTO.playlists.get(1).tracks.get(0).duration);
        assertEquals(playLists.get(1).getTracks().get(0).getPlaycount(), playlistsDTO.playlists.get(1).tracks.get(0).playcount);
        assertEquals(playLists.get(1).getTracks().get(0).getOfflineAvailable(), playlistsDTO.playlists.get(1).tracks.get(0).offlineAvailable);
        assertEquals(playLists.get(1).getTracks().get(0).getPerformer(), playlistsDTO.playlists.get(1).tracks.get(0).performer);
        assertEquals(playLists.get(1).getTracks().get(0).getPublicationDate(), playlistsDTO.playlists.get(1).tracks.get(0).publicationDate);
        assertEquals(playLists.get(1).getTracks().get(0).getTitle(), playlistsDTO.playlists.get(1).tracks.get(0).title);

    }

    @Test
    public void postPlaylists() {
        // Arrange
        int statuscodeExpected = 201;
        String objectJson = "{\"id\":-1,\"name\":\"test\",\"owner\":\"1\"}";

        //Mock for getPlaylists:

        int playlistAId = 0;
        int playlistBId = 1;
        String playlistAName = "Death metal";
        String playlistBName = "Pop";
        boolean playlistAOwner = false;
        boolean playlistBOwner = true;
        int trackId = 1;
        String trackTitle = "title";
        String trackPerformer = "Somebody";
        String trackDescription = "a description";
        String trackAlbum = "a album";
        int trackDuration = 1;
        int trackPlaycount = 1;
        String trackPublicationDate = "10-02-2121";
        boolean trackOfflineAvailable = false;


        ArrayList Tracks = new ArrayList<Track>();
        Track track = new Track();
        track.setId(trackId);
        track.setTitle(trackTitle);
        track.setPerformer(trackPerformer);
        track.setDuration(trackDuration);
        track.setAlbum(trackAlbum);
        track.setPlaycount(trackPlaycount);
        track.setPublicationDate(trackPublicationDate);
        track.setDescription(trackDescription);
        track.setOfflineAvailable(trackOfflineAvailable);
        Tracks.add(track);


        Playlists playlistsResult = new Playlists();
        Playlist playlistA = new Playlist(playlistAId, playlistAName, playlistAOwner, Tracks);
        Playlist playlistB = new Playlist(playlistBId, playlistBName, playlistBOwner, Tracks);

        var playLists = new ArrayList<Playlist>();
        playLists.add(playlistA);
        playLists.add(playlistB);

        playlistsResult.setPlaylists(playLists);
        playlistsResult.setLength(0);

        // mock
        PlaylistsDAO playlistsDAOMock = mock(PlaylistsDAO.class);
        try {
            doNothing().when(playlistsDAOMock).addPlaylist("", "");
        } catch (Exception e) {
        }
        playlists.setPlaylistsDAO(playlistsDAOMock);


        PlaylistsDAO playlistsDAOMock2 = mock(PlaylistsDAO.class);
        try {
            when(playlistsDAOMock2.getPlaylists("1")).thenReturn(playlistsResult);
        } catch (Exception e) {

        }
        playlists.setPlaylistsDAO(playlistsDAOMock2);

        // Act
        Response response = null;
        try {
            response = playlists.postPlaylists("1", objectJson);
        } catch (Exception e) {

        }
        PlaylistsDTO playlistsDTO = (PlaylistsDTO) response.getEntity();

        // Assert
        assertEquals(statuscodeExpected, response.getStatus());
        //test contents
        assertEquals(playLists.get(0).getId(), playlistsDTO.playlists.get(0).id);
        assertEquals(playLists.get(1).getId(), playlistsDTO.playlists.get(1).id);
        assertEquals(playLists.get(0).getName(), playlistsDTO.playlists.get(0).name);
        assertEquals(playLists.get(1).getName(), playlistsDTO.playlists.get(1).name);
        assertEquals(playLists.get(0).getOwner(), playlistsDTO.playlists.get(0).owner);
        assertEquals(playLists.get(1).getOwner(), playlistsDTO.playlists.get(1).owner);

        assertEquals(playLists.get(0).getTracks().get(0).getId(), playlistsDTO.playlists.get(0).tracks.get(0).id);
        assertEquals(playLists.get(0).getTracks().get(0).getDescription(), playlistsDTO.playlists.get(0).tracks.get(0).description);
        assertEquals(playLists.get(0).getTracks().get(0).getDuration(), playlistsDTO.playlists.get(0).tracks.get(0).duration);
        assertEquals(playLists.get(0).getTracks().get(0).getPlaycount(), playlistsDTO.playlists.get(0).tracks.get(0).playcount);
        assertEquals(playLists.get(0).getTracks().get(0).getOfflineAvailable(), playlistsDTO.playlists.get(0).tracks.get(0).offlineAvailable);
        assertEquals(playLists.get(0).getTracks().get(0).getPerformer(), playlistsDTO.playlists.get(0).tracks.get(0).performer);
        assertEquals(playLists.get(0).getTracks().get(0).getPublicationDate(), playlistsDTO.playlists.get(0).tracks.get(0).publicationDate);
        assertEquals(playLists.get(0).getTracks().get(0).getTitle(), playlistsDTO.playlists.get(0).tracks.get(0).title);


        assertEquals(playLists.get(1).getTracks().get(0).getId(), playlistsDTO.playlists.get(1).tracks.get(0).id);
        assertEquals(playLists.get(1).getTracks().get(0).getDescription(), playlistsDTO.playlists.get(1).tracks.get(0).description);
        assertEquals(playLists.get(1).getTracks().get(0).getDuration(), playlistsDTO.playlists.get(1).tracks.get(0).duration);
        assertEquals(playLists.get(1).getTracks().get(0).getPlaycount(), playlistsDTO.playlists.get(1).tracks.get(0).playcount);
        assertEquals(playLists.get(1).getTracks().get(0).getOfflineAvailable(), playlistsDTO.playlists.get(1).tracks.get(0).offlineAvailable);
        assertEquals(playLists.get(1).getTracks().get(0).getPerformer(), playlistsDTO.playlists.get(1).tracks.get(0).performer);
        assertEquals(playLists.get(1).getTracks().get(0).getPublicationDate(), playlistsDTO.playlists.get(1).tracks.get(0).publicationDate);
        assertEquals(playLists.get(1).getTracks().get(0).getTitle(), playlistsDTO.playlists.get(1).tracks.get(0).title);

    }


    @Test
    public void postPlaylistsAlternativeFlowWrongDataType() {
        // Arrange

        String objectJson = "{\"id\":\"-1a\",\"hjgdszfk\":\"test\",\"owner\":\"1\"}";


        // Assert
        assertThrows(
                com.google.gson.JsonSyntaxException.class,
                () -> playlists.postPlaylists("1", objectJson)
        );


    }

    @Test
    public void postPlaylistsAlternativeFlowWrongName() {
        // Arrange
        String objectJson = "{\"hjgdszfk\":\"test\",\"owner\":\"1\"}";

        // Assert
        assertThrows(
                PlaylistNoNameException.class,
                () -> playlists.postPlaylists("1", objectJson)
        );
    }


    @Test
    public void putPlaylists() {
        // Arrange
        int statuscodeExpected = 200;
        String objectJson = "{\"id\":1,\"name\":\"test\",\"owner\":\"1\"}";

        //Mock for getPlaylists:

        int playlistAId = 0;
        int playlistBId = 1;
        String playlistAName = "Death metal";
        String playlistBName = "Pop";
        boolean playlistAOwner = false;
        boolean playlistBOwner = true;
        int trackId = 1;
        String trackTitle = "title";
        String trackPerformer = "Somebody";
        String trackDescription = "a description";
        String trackAlbum = "a album";
        int trackDuration = 1;
        int trackPlaycount = 1;
        String trackPublicationDate = "10-02-2121";
        boolean trackOfflineAvailable = false;


        ArrayList Tracks = new ArrayList<Track>();
        Track track = new Track();
        track.setId(trackId);
        track.setTitle(trackTitle);
        track.setPerformer(trackPerformer);
        track.setDuration(trackDuration);
        track.setAlbum(trackAlbum);
        track.setPlaycount(trackPlaycount);
        track.setPublicationDate(trackPublicationDate);
        track.setDescription(trackDescription);
        track.setOfflineAvailable(trackOfflineAvailable);
        Tracks.add(track);


        Playlists playlistsResult = new Playlists();
        Playlist playlistA = new Playlist(playlistAId, playlistAName, playlistAOwner, Tracks);
        Playlist playlistB = new Playlist(playlistBId, playlistBName, playlistBOwner, Tracks);

        var playLists = new ArrayList<Playlist>();
        playLists.add(playlistA);
        playLists.add(playlistB);

        playlistsResult.setPlaylists(playLists);
        playlistsResult.setLength(0);

        // mock
        PlaylistsDAO playlistsDAOMock = mock(PlaylistsDAO.class);
        try {
            doNothing().when(playlistsDAOMock).updatePlaylist("", 1);
        } catch (Exception e) {

        }
        playlists.setPlaylistsDAO(playlistsDAOMock);

        PlaylistsDAO playlistsDAOMock2 = mock(PlaylistsDAO.class);
        try {
            when(playlistsDAOMock2.getPlaylists("1")).thenReturn(playlistsResult);
        } catch (Exception e) {
        }
        playlists.setPlaylistsDAO(playlistsDAOMock2);

        // Act
        Response response = null;
        try {
            response = playlists.putPlaylists("1", 1, objectJson);
        } catch (Exception e) {

        }
        PlaylistsDTO playlistsDTO = (PlaylistsDTO) response.getEntity();

        // Assert
        assertEquals(statuscodeExpected, response.getStatus());
        //test contents
        assertEquals(playLists.get(0).getId(), playlistsDTO.playlists.get(0).id);
        assertEquals(playLists.get(1).getId(), playlistsDTO.playlists.get(1).id);
        assertEquals(playLists.get(0).getName(), playlistsDTO.playlists.get(0).name);
        assertEquals(playLists.get(1).getName(), playlistsDTO.playlists.get(1).name);
        assertEquals(playLists.get(0).getOwner(), playlistsDTO.playlists.get(0).owner);
        assertEquals(playLists.get(1).getOwner(), playlistsDTO.playlists.get(1).owner);

        assertEquals(playLists.get(0).getTracks().get(0).getId(), playlistsDTO.playlists.get(0).tracks.get(0).id);
        assertEquals(playLists.get(0).getTracks().get(0).getDescription(), playlistsDTO.playlists.get(0).tracks.get(0).description);
        assertEquals(playLists.get(0).getTracks().get(0).getDuration(), playlistsDTO.playlists.get(0).tracks.get(0).duration);
        assertEquals(playLists.get(0).getTracks().get(0).getPlaycount(), playlistsDTO.playlists.get(0).tracks.get(0).playcount);
        assertEquals(playLists.get(0).getTracks().get(0).getOfflineAvailable(), playlistsDTO.playlists.get(0).tracks.get(0).offlineAvailable);
        assertEquals(playLists.get(0).getTracks().get(0).getPerformer(), playlistsDTO.playlists.get(0).tracks.get(0).performer);
        assertEquals(playLists.get(0).getTracks().get(0).getPublicationDate(), playlistsDTO.playlists.get(0).tracks.get(0).publicationDate);
        assertEquals(playLists.get(0).getTracks().get(0).getTitle(), playlistsDTO.playlists.get(0).tracks.get(0).title);


        assertEquals(playLists.get(1).getTracks().get(0).getId(), playlistsDTO.playlists.get(1).tracks.get(0).id);
        assertEquals(playLists.get(1).getTracks().get(0).getDescription(), playlistsDTO.playlists.get(1).tracks.get(0).description);
        assertEquals(playLists.get(1).getTracks().get(0).getDuration(), playlistsDTO.playlists.get(1).tracks.get(0).duration);
        assertEquals(playLists.get(1).getTracks().get(0).getPlaycount(), playlistsDTO.playlists.get(1).tracks.get(0).playcount);
        assertEquals(playLists.get(1).getTracks().get(0).getOfflineAvailable(), playlistsDTO.playlists.get(1).tracks.get(0).offlineAvailable);
        assertEquals(playLists.get(1).getTracks().get(0).getPerformer(), playlistsDTO.playlists.get(1).tracks.get(0).performer);
        assertEquals(playLists.get(1).getTracks().get(0).getPublicationDate(), playlistsDTO.playlists.get(1).tracks.get(0).publicationDate);
        assertEquals(playLists.get(1).getTracks().get(0).getTitle(), playlistsDTO.playlists.get(1).tracks.get(0).title);

    }

    @Test
    public void putPlaylistsAlternativeFlowWrongDataType() {
        // Arrange

        String objectJson = "{\"id\":\"-1a\",\"hjgdszfk\":\"test\",\"owner\":\"1\"}";

        // Assert
        assertThrows(
                com.google.gson.JsonSyntaxException.class,
                () -> playlists.putPlaylists("1", 1, objectJson)
        );

    }

    @Test
    public void putPlaylistsAlternativeFlowWrongName() {
        // Arrange
        String objectJson = "{\"hjgdszfk\":\"test\",\"owner\":\"1\"}";

        // Assert
        assertThrows(
                PlaylistNoNameException.class,
                () -> playlists.putPlaylists("1", 1, objectJson)
        );

    }

    @Test
    public void getPlaylistTracks() {
        // Arrange
        int statuscodeExpected = 200;
        int trackId = 1;
        String trackTitle = "title";
        String trackPerformer = "Somebody";
        String trackDescription = "a description";
        String trackAlbum = "a album";
        int trackDuration = 1;
        int trackPlaycount = 1;
        String trackPublicationDate = "10-02-2121";
        boolean trackOfflineAvailable = false;

        int trackId2 = 2;
        String trackTitle2 = "title2";
        String trackPerformer2 = "Somebody2";
        String trackDescription2 = "a description2";
        String trackAlbum2 = "a album2";
        int trackDuration2 = 2;
        int trackPlaycount2 = 0;
        String trackPublicationDate2 = "10-02-2121";
        boolean trackOfflineAvailable2 = true;


        ArrayList<Track> tracks = new ArrayList<Track>();
        Track track = new Track();
        track.setId(trackId);
        track.setTitle(trackTitle);
        track.setPerformer(trackPerformer);
        track.setDuration(trackDuration);
        track.setAlbum(trackAlbum);
        track.setPlaycount(trackPlaycount);
        track.setPublicationDate(trackPublicationDate);
        track.setDescription(trackDescription);
        track.setOfflineAvailable(trackOfflineAvailable);
        tracks.add(track);

        Track track2 = new Track();
        track2.setId(trackId2);
        track2.setTitle(trackTitle2);
        track2.setPerformer(trackPerformer2);
        track2.setDuration(trackDuration2);
        track2.setAlbum(trackAlbum2);
        track2.setPlaycount(trackPlaycount2);
        track2.setPublicationDate(trackPublicationDate2);
        track2.setDescription(trackDescription2);
        track2.setOfflineAvailable(trackOfflineAvailable2);
        tracks.add(track2);

        //mock
        TrackDAO trackDAOMock = mock(TrackDAO.class);
        try {
            when(trackDAOMock.getTracksFromPlaylist(1)).thenReturn(tracks);
        } catch (Exception e) {

        }
        playlists.setTrackDAO(trackDAOMock);

        // Act
        Response response = null;
        try {
            response = playlists.getPlaylistTracks("1", 1);
        } catch (Exception e) {

        }
        TracksDTO responseTracks = (TracksDTO) response.getEntity();

        // Assert
        assertEquals(statuscodeExpected, response.getStatus());

        //test content
        assertEquals(trackId, responseTracks.tracks.get(0).id);
        assertEquals(trackDescription, responseTracks.tracks.get(0).description);
        assertEquals(trackDuration, responseTracks.tracks.get(0).duration);
        assertEquals(trackPlaycount, responseTracks.tracks.get(0).playcount);
        assertEquals(trackOfflineAvailable, responseTracks.tracks.get(0).offlineAvailable);
        assertEquals(trackPerformer, responseTracks.tracks.get(0).performer);
        assertEquals(trackPublicationDate, responseTracks.tracks.get(0).publicationDate);
        assertEquals(trackTitle, responseTracks.tracks.get(0).title);


        assertEquals(trackId2, responseTracks.tracks.get(1).id);
        assertEquals(trackDescription2, responseTracks.tracks.get(1).description);
        assertEquals(trackDuration2, responseTracks.tracks.get(1).duration);
        assertEquals(trackPlaycount2, responseTracks.tracks.get(1).playcount);
        assertEquals(trackOfflineAvailable2, responseTracks.tracks.get(1).offlineAvailable);
        assertEquals(trackPerformer2, responseTracks.tracks.get(1).performer);
        assertEquals(trackPublicationDate2, responseTracks.tracks.get(1).publicationDate);
        assertEquals(trackTitle2, responseTracks.tracks.get(1).title);
    }

    @Test
    public void deleteTrackFromPlaylist() {
        // Arrange
        int statuscodeExpected = 200;
        int playlist = 2;

        int trackId = 1;
        String trackTitle = "title";
        String trackPerformer = "Somebody";
        String trackDescription = "a description";
        String trackAlbum = "a album";
        int trackDuration = 1;
        int trackPlaycount = 1;
        String trackPublicationDate = "10-02-2121";
        boolean trackOfflineAvailable = false;

        int trackId2 = 2;
        String trackTitle2 = "title2";
        String trackPerformer2 = "Somebody2";
        String trackDescription2 = "a description2";
        String trackAlbum2 = "a album2";
        int trackDuration2 = 2;
        int trackPlaycount2 = 0;
        String trackPublicationDate2 = "10-02-2121";
        boolean trackOfflineAvailable2 = true;


        ArrayList<Track> tracks = new ArrayList<Track>();
        Track track = new Track();
        track.setId(trackId);
        track.setTitle(trackTitle);
        track.setPerformer(trackPerformer);
        track.setDuration(trackDuration);
        track.setAlbum(trackAlbum);
        track.setPlaycount(trackPlaycount);
        track.setPublicationDate(trackPublicationDate);
        track.setDescription(trackDescription);
        track.setOfflineAvailable(trackOfflineAvailable);
        tracks.add(track);

        Track track2 = new Track();
        track2.setId(trackId2);
        track2.setTitle(trackTitle2);
        track2.setPerformer(trackPerformer2);
        track2.setDuration(trackDuration2);
        track2.setAlbum(trackAlbum2);
        track2.setPlaycount(trackPlaycount2);
        track2.setPublicationDate(trackPublicationDate2);
        track2.setDescription(trackDescription2);
        track2.setOfflineAvailable(trackOfflineAvailable2);
        tracks.add(track2);

        //mock
        TrackDAO trackDAOMock = mock(TrackDAO.class);
        try {
            doNothing().when(trackDAOMock).deleteTrackFromPlaylist(playlist,1);
            when(trackDAOMock.getTracksFromPlaylist(playlist)).thenReturn(tracks);
        } catch (Exception e) {
        }
        playlists.setTrackDAO(trackDAOMock);



        // Act
        Response response = null;
        try {
            response = playlists.deleteTrackFromPlaylist("1", playlist,4);
        } catch (Exception e) {

        }
        TracksDTO responseTracks = (TracksDTO) response.getEntity();

        assertEquals(statuscodeExpected,response.getStatus());

        //inners check
        assertEquals(trackId, responseTracks.tracks.get(0).id);
        assertEquals(trackTitle, responseTracks.tracks.get(0).title);
        assertEquals(trackPerformer, responseTracks.tracks.get(0).performer);
        assertEquals(trackDescription, responseTracks.tracks.get(0).description);
        assertEquals(trackAlbum, responseTracks.tracks.get(0).album);
        assertEquals(trackDuration, responseTracks.tracks.get(0).duration);
        assertEquals(trackPlaycount, responseTracks.tracks.get(0).playcount);
        assertEquals(trackPublicationDate, responseTracks.tracks.get(0).publicationDate);
        assertEquals(trackOfflineAvailable, responseTracks.tracks.get(0).offlineAvailable);

        assertEquals(trackId2, responseTracks.tracks.get(1).id);
        assertEquals(trackTitle2, responseTracks.tracks.get(1).title);
        assertEquals(trackPerformer2, responseTracks.tracks.get(1).performer);
        assertEquals(trackDescription2, responseTracks.tracks.get(1).description);
        assertEquals(trackAlbum2, responseTracks.tracks.get(1).album);
        assertEquals(trackDuration2, responseTracks.tracks.get(1).duration);
        assertEquals(trackPlaycount2, responseTracks.tracks.get(1).playcount);
        assertEquals(trackPublicationDate2, responseTracks.tracks.get(1).publicationDate);
        assertEquals(trackOfflineAvailable2, responseTracks.tracks.get(1).offlineAvailable);

    }

    @Test
    public void postTrackInPlaylist() {
        // Arrange
        int statuscodeExpected = 201;
        int playlist = 2;

        int trackId = 1;
        String trackTitle = "title";
        String trackPerformer = "Somebody";
        String trackDescription = "a description";
        String trackAlbum = "a album";
        int trackDuration = 1;
        int trackPlaycount = 1;
        String trackPublicationDate = "10-02-2121";
        boolean trackOfflineAvailable = false;

        String jsonBody = "{\n" +
                "  \"id\": "+ trackId+ ",\n" +
                "  \"title\": \""+trackTitle +"\",\n" +
                "  \"performer\": \""+trackPerformer+"\",\n" +
                "  \"duration\": "+trackDuration+",\n" +
                "  \"album\": \""+trackAlbum+"\",\n" +
                "  \"playcount\": "+trackPlaycount+",\n" +
                "  \"publicationDate\": \""+trackPublicationDate+"\",\n" +
                "  \"description\": \""+trackDescription+"\",\n" +
                "  \"offlineAvailable\": "+trackOfflineAvailable+"\n" +
                "}";



        ArrayList<Track> tracks = new ArrayList<Track>();
        Track track = new Track();
        track.setId(trackId);
        track.setTitle(trackTitle);
        track.setPerformer(trackPerformer);
        track.setDuration(trackDuration);
        track.setAlbum(trackAlbum);
        track.setPlaycount(trackPlaycount);
        track.setPublicationDate(trackPublicationDate);
        track.setDescription(trackDescription);
        track.setOfflineAvailable(trackOfflineAvailable);
        tracks.add(track);

        //mock
        TrackDAO trackDAOMock = mock(TrackDAO.class);
        try {
            doNothing().when(trackDAOMock).deleteTrackFromPlaylist(playlist,1);
            when(trackDAOMock.getTracksFromPlaylist(playlist)).thenReturn(tracks);
        } catch (Exception e) {
        }
        playlists.setTrackDAO(trackDAOMock);


        // Act
        Response response = null;
        try {
            response = playlists.postTrackInPlaylist("1", playlist,jsonBody);
        } catch (Exception e) {
        }
        TracksDTO responseTracks = (TracksDTO) response.getEntity();

        assertEquals(statuscodeExpected,response.getStatus());

        //inners check
        assertEquals(trackId, responseTracks.tracks.get(0).id);
        assertEquals(trackTitle, responseTracks.tracks.get(0).title);
        assertEquals(trackPerformer, responseTracks.tracks.get(0).performer);
        assertEquals(trackDescription, responseTracks.tracks.get(0).description);
        assertEquals(trackAlbum, responseTracks.tracks.get(0).album);
        assertEquals(trackDuration, responseTracks.tracks.get(0).duration);
        assertEquals(trackPlaycount, responseTracks.tracks.get(0).playcount);
        assertEquals(trackPublicationDate, responseTracks.tracks.get(0).publicationDate);
        assertEquals(trackOfflineAvailable, responseTracks.tracks.get(0).offlineAvailable);

    }

}
