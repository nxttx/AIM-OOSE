package nld.spotitube.dao;

import nld.spotitube.domain.Track;

import javax.enterprise.inject.Alternative;

@Alternative
public class TrackDAOMongoDB implements ITrackDAO {
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
}
