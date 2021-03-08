import nld.spotitube.dao.PlaylistsDAO;
import nld.spotitube.domain.Playlist;
import nld.spotitube.domain.Track;
import nld.spotitube.service.PlaylistsROUTE;
import nld.spotitube.domain.Playlists;
import nld.spotitube.service.dto.PlaylistsDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class PlaylistsROUTETest {

    private PlaylistsROUTE playlists;

    @BeforeEach
    public void setUp(){
        playlists = new PlaylistsROUTE();
    }

    @Test
    public void getPlaylists(){
        // Arrange
        int statuscodeExpected = 200;
        int playlistAId = 0;
        int playlistBId = 1;
        String playlistAName= "Death metal";
        String playlistBName= "Pop";
        int playlistAOwner= 1;
        int playlistBOwner= 1;
        int trackId=1;
        String trackTitle= "title";
        String trackPerformer=  "Somebody";
        String trackDescription = "a description";
        String trackAlbum = "a album";
        int trackDuration= 1;
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
        Playlist playlistB = new Playlist(playlistBId,playlistBName, playlistBOwner, Tracks);

        var playLists = new ArrayList<Playlist>();
        playLists.add(playlistA);
        playLists.add(playlistB);

        playlistsResult.setPlaylists(playLists);
        playlistsResult.setLength(0);

        PlaylistsDAO playlistsDAOMock = mock(PlaylistsDAO.class);
        when(playlistsDAOMock.getPlaylists()).thenReturn(playlistsResult);
        playlists.setPlaylistsDAO(playlistsDAOMock);


        // Act
        Response response = playlists.getPlaylists();
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


}
