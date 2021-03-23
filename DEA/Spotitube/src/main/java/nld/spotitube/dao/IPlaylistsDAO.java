package nld.spotitube.dao;

import nld.spotitube.domain.Playlists;
import nld.spotitube.exceptions.NoRowsAreEffectedException;
import nld.spotitube.service.dto.PlaylistDTO;

import java.sql.SQLException;

public interface IPlaylistsDAO {
    Playlists getPlaylists(String Token) throws SQLException;

    void addPlaylist(String playlistName, String owner) throws NoRowsAreEffectedException, SQLException;

    void deletePlaylist(int id) throws NoRowsAreEffectedException, SQLException;

    void updatePlaylist(String playlistName, int PlatlistId) throws NoRowsAreEffectedException, SQLException;

    String getTokenOfOwner(int playlistID)throws SQLException;
}
