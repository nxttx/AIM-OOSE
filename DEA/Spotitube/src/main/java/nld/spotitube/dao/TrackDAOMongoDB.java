package nld.spotitube.dao;

import nld.spotitube.domain.Track;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;

@Alternative
public class TrackDAOMongoDB implements ITrackDAO {

    @Override
    public ArrayList<Track> getAllTracks() {
        return null;
    }

    @Override
    public Track getTrack(int id) {
        // we should go to the database here, but we won't do that until tomorrow
        Track track =  new Track();
        track.setId(id);
//        track.setName("Robert");
//        track.setDark(false);
//        track.setBsn("235896");

        return track;
    }

    @Override
    public ArrayList<Track> getTracksFromPlaylist(int id){
        return null;
    }
}
