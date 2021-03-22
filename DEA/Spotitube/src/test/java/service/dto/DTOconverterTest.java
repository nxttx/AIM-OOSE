package service.dto;

import com.google.gson.JsonSyntaxException;
import nld.spotitube.domain.Playlist;
import nld.spotitube.domain.Playlists;
import nld.spotitube.domain.Track;
import nld.spotitube.exceptions.PlaylistNoNameException;
import nld.spotitube.exceptions.TrackNoTitleException;
import nld.spotitube.exceptions.UserNoNameException;
import nld.spotitube.service.dto.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DTOconverterTest {

    @Test
    void JSONToUserDTOBasic() {
        //arrange
        String name = "Name1";
        String password ="Password1";

        String JsonObject = "{\"user\":\""+name+"\",\"password\": \""+ password+ "\" }";

        //Act
        UserDTO userDTO =null;
        try {
            userDTO = DTOconverter.JSONToUserDTO(JsonObject);
        } catch (UserNoNameException e) {
        }

        //Assert
        assertEquals(name, userDTO.user);
        assertEquals(password, userDTO.password);
    }

    @Test
    void JSONToUserDTOBad() {
        //arrange
        String name = "Name1";
        String password ="Password1";

        String jsonBodyNoName = "{\"sjdhf\":\""+name+"\",\"password\": \""+ password+ "\" }";


        String jsonBodyWithErrors = "{\"user\":\""+name+"\"\"password\": \""+ password+ "\" }";

        //Assert
        assertThrows(
                UserNoNameException.class,
                () -> DTOconverter.JSONToUserDTO(jsonBodyNoName)
        );
        assertThrows(
                JsonSyntaxException.class,
                () -> DTOconverter.JSONToUserDTO(jsonBodyWithErrors)
        );
    }

    @Test
    void JSONToTrackDTOBasic() {
        //arrange
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

        //Act
        TrackDTO newTrack =null;
        try {
            newTrack = DTOconverter.JSONToTrackDTO(jsonBody);
        } catch (TrackNoTitleException e) {
        }

        //Assert
        assertEquals(trackId, newTrack.id);
        assertEquals(trackTitle, newTrack.title);
        assertEquals(trackPerformer, newTrack.performer);
        assertEquals(trackDescription, newTrack.description);
        assertEquals(trackAlbum, newTrack.album);
        assertEquals(trackDuration, newTrack.duration);
        assertEquals(trackPlaycount, newTrack.playcount);
        assertEquals(trackPublicationDate, newTrack.publicationDate);
        assertEquals(trackOfflineAvailable, newTrack.offlineAvailable);

    }

    @Test
    void JSONToTrackDTOBad() {
        //arrange
        int trackId = 1;
        String trackTitle = "title";
        String trackPerformer = "Somebody";
        String trackDescription = "a description";
        String trackAlbum = "a album";
        int trackDuration = 1;
        int trackPlaycount = 1;
        String trackPublicationDate = "10-02-2121";
        boolean trackOfflineAvailable = false;

        String jsonBodyNoTitle = "{\n" +
                "  \"id\": "+ trackId+ ",\n" +
                "  \"qiwe\": \""+trackTitle +"\",\n" +
                "  \"performer\": \""+trackPerformer+"\",\n" +
                "  \"duration\": "+trackDuration+",\n" +
                "  \"album\": \""+trackAlbum+"\",\n" +
                "  \"playcount\": "+trackPlaycount+",\n" +
                "  \"publicationDate\": \""+trackPublicationDate+"\",\n" +
                "  \"description\": \""+trackDescription+"\",\n" +
                "  \"offlineAvailable\": "+trackOfflineAvailable+"\n" +
                "}";
        String jsonBodyWithErrors = "{\n" +
                "  \"id\": "+ trackId+ ",\n" +
                "  \"title\": \""+trackTitle +"\",\n" +
                "  \"performer\": \""+trackPerformer+"\",\n" +
                "  \"duration\": "+trackDuration+"\n" +
                "  \"album\": \""+trackAlbum+"\",\n" +
                "  \"playcount\": "+trackPlaycount+",\n" +
                "  \"publicationDate\": \""+trackPublicationDate+"\",\n" +
                "  \"description\": \""+trackDescription+"\",\n" +
                "  \"offlineAvailable\": "+trackOfflineAvailable+"\n" +
                "}";


        //Assert
        assertThrows(
                TrackNoTitleException.class,
                () -> DTOconverter.JSONToTrackDTO(jsonBodyNoTitle)
        );
        assertThrows(
                JsonSyntaxException.class,
                () -> DTOconverter.JSONToTrackDTO(jsonBodyWithErrors)
        );

    }

    @Test
    void JSONToPlaylistDTOBasic() {
        int id = -1;
        String name = "test";
        boolean owner = false;
        String objectJson = "{\"id\":"+id+",\"name\":\""+name+"\",\"owner\":\""+owner+"\"}";

        //Act
        PlaylistDTO playlistDTO =null;
        try {
            playlistDTO = DTOconverter.JSONToPlaylistDTO(objectJson);
        } catch (PlaylistNoNameException e) {
        }

        //Assert
        assertEquals(id, playlistDTO.id);
        assertEquals(name, playlistDTO.name);
        assertEquals(owner, playlistDTO.owner);

    }

    @Test
    void JSONToPlaylistDTOBad() {
        //arrange
        int id = -1;
        String name = "test";
        boolean owner = false;

        String jsonBodyNoName = "{\"id\":"+id+",\"qwer\":\""+name+"\",\"owner\":\""+owner+"\"}";
        String jsonBodyWithErrors = "{\"id\":"+id+",\"name\":\""+name+"\"\"owner\":\""+owner+"\"}";


        //Assert
        assertThrows(
                PlaylistNoNameException.class,
                () -> DTOconverter.JSONToPlaylistDTO(jsonBodyNoName)
        );
        assertThrows(
                JsonSyntaxException.class,
                () -> DTOconverter.JSONToPlaylistDTO(jsonBodyWithErrors)
        );

    }

    @Test
    void playlistsToPlaylistsDTO() {
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

        PlaylistsDTO playlistsDTO = DTOconverter.PlaylistsToPlaylistsDTO(playlistsResult);

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
    void trackToTrackDTO() {
        int trackId = 1;
        String trackTitle = "title";
        String trackPerformer = "Somebody";
        String trackDescription = "a description";
        String trackAlbum = "a album";
        int trackDuration = 1;
        int trackPlaycount = 1;
        String trackPublicationDate = "10-02-2121";
        boolean trackOfflineAvailable = false;

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

        TrackDTO trackDTO = DTOconverter.TrackToTrackDTO(track);

        assertEquals(track.getId(), trackDTO.id);
        assertEquals(track.getDescription(), trackDTO.description);
        assertEquals(track.getDuration(), trackDTO.duration);
        assertEquals(track.getPlaycount(), trackDTO.playcount);
        assertEquals(track.getOfflineAvailable(), trackDTO.offlineAvailable);
        assertEquals(track.getPerformer(), trackDTO.performer);
        assertEquals(track.getPublicationDate(), trackDTO.publicationDate);
        assertEquals(track.getTitle(), trackDTO.title);


    }

    @Test
    void trackListToTracksDTO() {
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

        TracksDTO responseTracks = DTOconverter.trackListToTracksDTO(tracks);

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