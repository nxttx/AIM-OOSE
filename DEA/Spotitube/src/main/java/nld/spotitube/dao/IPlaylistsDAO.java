package nld.spotitube.dao;

import nld.spotitube.domain.Playlists;
import nld.spotitube.service.dto.PlaylistDTO;

public interface IPlaylistsDAO {
    Playlists getPlaylists();
    void addPlaylist(PlaylistDTO playlistDTO);

    void deletePlaylist(int id);

    void updatePlaylist(PlaylistDTO playlistDTO);
}
