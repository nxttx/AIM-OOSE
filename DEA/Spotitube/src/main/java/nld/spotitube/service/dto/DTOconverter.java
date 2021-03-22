package nld.spotitube.service.dto;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import nld.spotitube.domain.Playlists;
import nld.spotitube.domain.Track;
import nld.spotitube.exceptions.PlaylistNoNameException;
import nld.spotitube.exceptions.TrackNoTitleException;
import nld.spotitube.exceptions.UserNoNameException;

import java.util.ArrayList;

public class DTOconverter {
    private static final Gson JSON = new Gson();

    public static UserDTO JSONToUserDTO(String JSONObject) throws UserNoNameException, JsonSyntaxException{//todo unittests
        UserDTO userDTO;
        userDTO = JSON.fromJson(JSONObject, UserDTO.class);
        if (userDTO.user == null) {
            throw new UserNoNameException();
        }
        return userDTO;
    }

    public static TrackDTO JSONToTrackDTO(String JSONObject) throws TrackNoTitleException, JsonSyntaxException{ //todo unittests
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

    public static PlaylistsDTO PlaylistsToPlaylistsDTO(Playlists playlists){
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.length = playlists.getLength();
        ArrayList<PlaylistDTO> playlistList = new ArrayList<PlaylistDTO>();
        playlists.getPlaylists().forEach(playlist -> {
            var newPlaylist = new PlaylistDTO();
            newPlaylist.name = playlist.getName();
            newPlaylist.id = playlist.getId();
            newPlaylist.owner = playlist.getOwner();
            ArrayList<TrackDTO> trackList = new ArrayList<TrackDTO>();
            playlist.getTracks().forEach(track -> {
                TrackDTO newTrack =DTOconverter.TrackToTrackDTO(track);
                trackList.add(newTrack);
            });

            newPlaylist.tracks = trackList;
            playlistList.add(newPlaylist);
        });
        playlistsDTO.playlists = playlistList;
        return playlistsDTO;
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

    public static TracksDTO trackListToTracksDTO(ArrayList<Track> Tracklist){
        ArrayList<TrackDTO> newTrackList = new ArrayList<TrackDTO>();
        Tracklist.forEach(track -> {
            TrackDTO newTrack =DTOconverter.TrackToTrackDTO(track);
            newTrackList.add(newTrack);
        });

        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.tracks = newTrackList;
        return tracksDTO;
    }
}
