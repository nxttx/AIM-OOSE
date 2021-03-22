package nld.spotitube.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import nld.spotitube.dao.IPlaylistsDAO;
import nld.spotitube.dao.ITrackDAO;
import nld.spotitube.dao.PlaylistsDAO;
import nld.spotitube.dao.TrackDAO;
import nld.spotitube.domain.Playlists;
import nld.spotitube.domain.Track;
import nld.spotitube.exceptions.PlaylistNoNameException;
import nld.spotitube.exceptions.TrackNoTitleException;
import nld.spotitube.service.dto.*;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("playlists")
public class PlaylistsROUTE {

    private IPlaylistsDAO PlaylistsDAO = new PlaylistsDAO();
    private ITrackDAO TrackDAO = new TrackDAO();
    private final Gson JSON = new Gson();

//    @GET
//    @Path("/helloworld/")
//    public Response helloworold() {
//        return Response.status(200).entity("hello wold ").build();
//    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists() {
        Playlists playlists = PlaylistsDAO.getPlaylists();
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

        return Response.status(200).entity(playlistsDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylists(@QueryParam("token") String token, @PathParam("id") int id) {
        PlaylistsDAO.deletePlaylist(id);
        /*
            get all playlists
            Using a work arround. This is maybe something to fix later by making it global. But for now it works.
        */
        Response response = getPlaylists();
        PlaylistsDTO playlistsDTO = (PlaylistsDTO) response.getEntity();
        return Response.status(201).entity(playlistsDTO).build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postPlaylists(@QueryParam("token") String token, String body) {
        //build body to object
        PlaylistDTO newPlaylist;
        try {
            newPlaylist = DTOconverter.JSONToPlaylistDTO(body);
        }catch(PlaylistNoNameException | JsonSyntaxException E){
            return Response.status(400).build();
        }
        //upload new playlist to database.

        PlaylistsDAO.addPlaylist(newPlaylist.name, token);

        /*
            get all playlists
            Using a work arround. This is maybe something to fix later by making it global. But for now it works.
        */
        Response response = getPlaylists();
        PlaylistsDTO playlistsDTO = (PlaylistsDTO) response.getEntity();

        return Response.status(201).entity(playlistsDTO).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putPlaylists(@QueryParam("token") String token, @PathParam("id") int id, String body) {
        //build body to object
        PlaylistDTO newPlaylist;
        try {
            newPlaylist = DTOconverter.JSONToPlaylistDTO(body);
        }catch(PlaylistNoNameException | JsonSyntaxException E){
            return Response.status(400).build();
        }
        //edit that playlist
        PlaylistsDAO.updatePlaylist(newPlaylist.name, newPlaylist.id);


        /*
            get all playlists
            Using a work arround. This is maybe something to fix later by making it global. But for now it works.
        */
        Response response = getPlaylists();
        PlaylistsDTO playlistsDTO = (PlaylistsDTO) response.getEntity();

        return Response.status(200).entity(playlistsDTO).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylistTracks(@QueryParam("token") String token, @PathParam("id") int id) {
        ArrayList<Track> tracks = TrackDAO.getTracksFromPlaylist(id);

        ArrayList<TrackDTO> trackList = new ArrayList<TrackDTO>();
        tracks.forEach(track -> {
            TrackDTO newTrack =DTOconverter.TrackToTrackDTO(track);
            trackList.add(newTrack);
        });

        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.tracks = trackList;

        return Response.status(200).entity(tracksDTO).build();
    }

    @DELETE
    @Path("/{id}/tracks/{trackID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, @PathParam("trackID") int trackID) {
        //todo unittests
        TrackDAO.deleteTrackFromPlaylist(playlistId, trackID);

        /*
            get all tracks of that playlist
            Using a work arround. This is maybe something to fix later by making it global. But for now it works.
        */
        Response response = getPlaylistTracks(token, playlistId);
        TracksDTO tracksDTO = (TracksDTO) response.getEntity();

        return Response.status(200).entity(tracksDTO).build();
    }


    @POST
    @Path("/{id}/tracks/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postTrackInPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, String body) {
        //todo unittests
        //build body to object
        TrackDTO newTrack;
        try {
            newTrack = DTOconverter.JSONToTrackDTO(body);
        }catch(TrackNoTitleException | JsonSyntaxException E){
            return Response.status(400).build();
        }
        //edit that playlist
        TrackDAO.addTrackToPlaylist(playlistId, newTrack.id);

        /*
            get all tracks of that playlist
            Using a work arround. This is maybe something to fix later by making it global. But for now it works.
        */
        Response response = getPlaylistTracks(token, playlistId);
        TracksDTO tracksDTO = (TracksDTO) response.getEntity();

        return Response.status(201).entity(tracksDTO).build();
    }

    @Inject
    public void setPlaylistsDAO(PlaylistsDAO playlistsDAO) {
        this.PlaylistsDAO = playlistsDAO;
    }

    @Inject
    public void setTrackDAO(TrackDAO TrackDAO) {
        this.TrackDAO = TrackDAO;
    }
}
