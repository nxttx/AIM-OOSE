package nld.spotitube.service.dto;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import nld.spotitube.domain.Track;
import nld.spotitube.exceptions.PlaylistNoNameException;
import nld.spotitube.exceptions.TrackNoTitleException;

import javax.ws.rs.core.Response;

public class DTOconverter {
    private static final Gson JSON = new Gson();

    public static TrackDTO JSONToTrackDTO(String JSONObject) throws TrackNoTitleException, JsonSyntaxException{
        TrackDTO newTrack;
            newTrack = JSON.fromJson(JSONObject, TrackDTO.class);
            if (newTrack.title == null) {
                throw new TrackNoTitleException();
            }
        return newTrack;
    }

    public static PlaylistDTO JSONToPlaylistDTO(String JSONObject) throws PlaylistNoNameException, JsonSyntaxException {
        PlaylistDTO newPlaylist;
        newPlaylist = JSON.fromJson(JSONObject, PlaylistDTO.class);
        if (newPlaylist.name == null) {
            throw new PlaylistNoNameException();
        }
        return newPlaylist;
    }

    public static TrackDTO TrackToTrackDTO(Track track){
        var newTrack = new TrackDTO();
        newTrack.album = track.getAlbum();
        newTrack.id = track.getId();
        newTrack.description = track.getDescription();
        newTrack.duration = track.getDuration();
        newTrack.playcount = track.getPlaycount();
        newTrack.offlineAvailable = track.getOfflineAvailable();
        newTrack.performer = track.getPerformer();
        newTrack.publicationDate = track.getPublicationDate();
        newTrack.title = track.getTitle();
        return newTrack;
    }
}
