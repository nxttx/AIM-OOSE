package nld.spotitube.service;

import nld.spotitube.dao.IPlaylistsDAO;
import nld.spotitube.dao.ITrackDAO;
import nld.spotitube.dao.PlaylistsDAO;
import nld.spotitube.dao.TrackDAO;
import nld.spotitube.domain.Playlists;
import nld.spotitube.domain.Track;
import nld.spotitube.exceptions.NoRowsAreEffectedException;
import nld.spotitube.exceptions.PlaylistNoNameException;
import nld.spotitube.exceptions.TrackNoTitleException;
import nld.spotitube.service.dto.*;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("playlists")
public class PlaylistsROUTE {

    private IPlaylistsDAO PlaylistsDAO = new PlaylistsDAO();
    private ITrackDAO TrackDAO = new TrackDAO();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(@QueryParam("token") String token) throws SQLException {
        Playlists playlists = PlaylistsDAO.getPlaylists(token);
        PlaylistsDTO playlistsDTO = DTOconverter.PlaylistsToPlaylistsDTO(playlists);
        return Response.status(200).entity(playlistsDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePlaylists(@QueryParam("token") String token, @PathParam("id") int id) throws NoRowsAreEffectedException, SQLException {
        PlaylistsDAO.deletePlaylist(id);
        //get new playlists
        Playlists playlists = PlaylistsDAO.getPlaylists(token);
        PlaylistsDTO playlistsDTO = DTOconverter.PlaylistsToPlaylistsDTO(playlists);
        return Response.status(201).entity(playlistsDTO).build();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postPlaylists(@QueryParam("token") String token, String body) throws NoRowsAreEffectedException, PlaylistNoNameException, SQLException {
        //build body to object
        PlaylistDTO newPlaylist;
            newPlaylist = DTOconverter.JSONToPlaylistDTO(body);

        PlaylistsDAO.addPlaylist(newPlaylist.name, token);

        //get new playlists
        Playlists playlists = PlaylistsDAO.getPlaylists(token);
        PlaylistsDTO playlistsDTO = DTOconverter.PlaylistsToPlaylistsDTO(playlists);

        return Response.status(201).entity(playlistsDTO).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putPlaylists(@QueryParam("token") String token, @PathParam("id") int id, String body) throws NoRowsAreEffectedException, PlaylistNoNameException, SQLException {
        //build body to object
        PlaylistDTO newPlaylist;

            newPlaylist = DTOconverter.JSONToPlaylistDTO(body);

        //edit that playlist
        PlaylistsDAO.updatePlaylist(newPlaylist.name, newPlaylist.id);


        //get new playlists
        Playlists playlists = PlaylistsDAO.getPlaylists(token);
        PlaylistsDTO playlistsDTO = DTOconverter.PlaylistsToPlaylistsDTO(playlists);

        return Response.status(200).entity(playlistsDTO).build();
    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylistTracks(@QueryParam("token") String token, @PathParam("id") int id) throws SQLException {
        ArrayList<Track> tracks = TrackDAO.getTracksFromPlaylist(id);
        TracksDTO tracksDTO = DTOconverter.trackListToTracksDTO(tracks);

        return Response.status(200).entity(tracksDTO).build();
    }

    @DELETE
    @Path("/{id}/tracks/{trackID}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteTrackFromPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, @PathParam("trackID") int trackID) throws NoRowsAreEffectedException, SQLException {
        TrackDAO.deleteTrackFromPlaylist(playlistId, trackID);

        ArrayList<Track> tracks = TrackDAO.getTracksFromPlaylist(playlistId);
        TracksDTO tracksDTO = DTOconverter.trackListToTracksDTO(tracks);

        return Response.status(200).entity(tracksDTO).build();
    }


    @POST
    @Path("/{id}/tracks/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postTrackInPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, String body) throws NoRowsAreEffectedException, TrackNoTitleException, SQLException {
        //build body to object
        TrackDTO newTrack;

            newTrack = DTOconverter.JSONToTrackDTO(body);

        //edit that playlist
        TrackDAO.addTrackToPlaylist(playlistId, newTrack.id);

        ArrayList<Track> tracks = TrackDAO.getTracksFromPlaylist(playlistId);
        TracksDTO tracksDTO = DTOconverter.trackListToTracksDTO(tracks);

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
