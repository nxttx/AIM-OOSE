package nld.spotitube.dao;

import nld.spotitube.domain.Track;
import nld.spotitube.exceptions.NoRowsAreEffectedException;
import nld.spotitube.service.dto.TrackDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ITrackDAO {
    ArrayList<Track> getAllTracks() throws SQLException;

    Track getTrack(int id) throws SQLException;

    ArrayList<Track> getTracksFromPlaylist(int id) throws SQLException;

    ArrayList<Track> getTracksNotInPlaylist(int id) throws SQLException;

    void addTrackToPlaylist(int playlistId, int trackid, boolean OfflineAvailable) throws NoRowsAreEffectedException, SQLException;

    void deleteTrackFromPlaylist(int playlistId, int trackID) throws NoRowsAreEffectedException, SQLException;
}
