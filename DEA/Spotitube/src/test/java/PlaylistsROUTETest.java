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
        int length = 0;
        int playlistAId = 0;
        int playlistBId = 1;
        String playlistAName= "Death metal";
        String playlistBName= "Pop";
        int playlistAOwner= 1;
        int playlistBOwner= 1;

        ArrayList Tracks = new ArrayList<Track>();

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
        assertEquals(playLists.get(0).getId(), playlistsDTO.playlists.get(0).id);
        assertEquals(playLists.get(1).getId(), playlistsDTO.playlists.get(1).id);
        assertEquals(playLists.get(0).getName(), playlistsDTO.playlists.get(0).name);
        assertEquals(playLists.get(1).getName(), playlistsDTO.playlists.get(1).name);
        assertEquals(playLists.get(0).getOwner(), playlistsDTO.playlists.get(0).owner);
        assertEquals(playLists.get(1).getOwner(), playlistsDTO.playlists.get(1).owner);
        assertEquals(playLists.get(0).getTracks(), playlistsDTO.playlists.get(0).tracks);
        assertEquals(playLists.get(1).getTracks(), playlistsDTO.playlists.get(1).tracks);

    }


}
