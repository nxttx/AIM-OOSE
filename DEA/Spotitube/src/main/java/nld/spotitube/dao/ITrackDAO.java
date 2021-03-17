package nld.spotitube.dao;

import nld.spotitube.domain.Track;
import nld.spotitube.service.dto.TrackDTO;

import java.util.ArrayList;

public interface ITrackDAO {
    ArrayList<Track> getAllTracks();

    Track getTrack(int id);

    ArrayList<Track> getTracksFromPlaylist(int id);

    ArrayList<Track> getTracksNotInPlaylist(int id);

    void addTrackToPlaylist(int playlistId, TrackDTO newTrack);
}
