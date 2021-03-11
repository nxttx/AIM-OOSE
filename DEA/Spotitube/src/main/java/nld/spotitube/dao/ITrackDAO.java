package nld.spotitube.dao;

import nld.spotitube.domain.Track;

import java.util.ArrayList;

public interface ITrackDAO {
    ArrayList<Track> getAllTracks();

    Track getTrack(int id);

    ArrayList<Track> getTracksFromPlaylist(int id);

    ArrayList<Track> getTracksNotInPlaylist(int id);


}
