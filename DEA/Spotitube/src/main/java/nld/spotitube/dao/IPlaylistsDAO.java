package nld.spotitube.dao;

import nld.spotitube.domain.Playlists;
import nld.spotitube.service.dto.PlaylistDTO;

public interface IPlaylistsDAO {
    Playlists getPlaylists();

    void addPlaylist(String playlistName, String owner);

    void deletePlaylist(int id);

    void updatePlaylist(String playlistName, int PlatlistId);
}
