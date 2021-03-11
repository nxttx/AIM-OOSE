package service;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import nld.spotitube.dao.TrackDAO;
import nld.spotitube.domain.Track;
import nld.spotitube.service.TracksROUTE;

import nld.spotitube.service.dto.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TracksROUTETest {

    private TracksROUTE tracksROUTE;

    @BeforeEach
    public void setUp() {
        tracksROUTE = new TracksROUTE();
    }

    @Test
    public void getTracksBasicFlow() {
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
        when(trackDAOMock.getAllTracks()).thenReturn(tracks);
        tracksROUTE.setTrackDAO(trackDAOMock);

        // Act
        Response response = tracksROUTE.getTracks(1,0);
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
    public void getTracksAlternativeFlow() {
        // Arrange
        int statuscodeExpected = 200;
        int playlistnr=1;

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
        when(trackDAOMock.getTracksNotInPlaylist(playlistnr)).thenReturn(tracks);
        tracksROUTE.setTrackDAO(trackDAOMock);

        // Act
        Response response = tracksROUTE.getTracks(1,playlistnr);
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
}


/*
        // Arrange
        int statuscodeExpected = 200;

        //mock
        TrackDAO trackDAOMock = mock(TrackDAO.class);
        when(trackDAOMock.getTracksFromPlaylist(0)).thenReturn(tracks);
        tracksROUTE.setTrackDAO(trackDAOMock);

        // Act
        Response response = tracksROUTE.getTracks(1,0);
        TracksDTO responseTracks = (TracksDTO) response.getEntity();

        // Assert
        assertEquals(statuscodeExpected, response.getStatus());

        //test content
 */