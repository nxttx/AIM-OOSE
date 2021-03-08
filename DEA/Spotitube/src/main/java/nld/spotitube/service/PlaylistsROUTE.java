package nld.spotitube.service;

import nld.spotitube.dao.IPlaylistsDAO;
import nld.spotitube.dao.ITrackDAO;
import nld.spotitube.dao.PlaylistsDAO;
import nld.spotitube.dao.TrackDAO;
import nld.spotitube.domain.Playlists;
import nld.spotitube.domain.Track;
import nld.spotitube.service.dto.PlaylistDTO;
import nld.spotitube.service.dto.PlaylistsDTO;
import nld.spotitube.service.dto.TrackDTO;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("playlists")
public class PlaylistsROUTE {

    private IPlaylistsDAO PlaylistsDAO = new PlaylistsDAO();
    private ITrackDAO TrackDAO = new TrackDAO();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(){
        Playlists playlists = PlaylistsDAO.getPlaylists();
        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        playlistsDTO.length = playlists.getLength();
        ArrayList<PlaylistDTO> playlistList = new ArrayList<PlaylistDTO>();
        playlists.getPlaylists().forEach(playlist -> {
            var newPlaylist = new PlaylistDTO();
            newPlaylist.name = playlist.getName();
            newPlaylist.id= playlist.getId();
            newPlaylist.owner = playlist.getOwner();
            ArrayList<TrackDTO> trackList = new ArrayList<TrackDTO>();
            playlist.getTracks().forEach(track ->{
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

                trackList.add(newTrack);
            });

            newPlaylist.tracks = trackList;
            playlistList.add(newPlaylist);
        });
        playlistsDTO.playlists = playlistList;

        return Response.status(200).entity(playlistsDTO).build();
    }

//    @DELETE
//    @Path("/")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response deletePlaylists(@QueryParam("token") int token){
//
//        return Response.status(200).build();
//    }
//
//    @POST
//    @Path("/")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response postPlaylists(@QueryParam("token") int token){
//        PlaylistDTO newPlaylist = new PlaylistDTO();
//        return Response.status(200).entity(newPlaylist).build();
//    }
//
//    @PUT
//    @Path("/")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response putPlaylists(@QueryParam("token") int token){
//
//        return Response.status(200).build();
//    }

    @GET
    @Path("/{id}/tracks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylistTracks(@PathParam("id") int id) {
        ArrayList<Track> tracks = TrackDAO.getTracksFromPlaylist(id);
        //build tracks to tracks dto

        return Response.status(200).entity(tracks).build();
    }


    //For unittests
    @Inject
    public void setPlaylistsDAO(PlaylistsDAO playlistsDAO) {
        this.PlaylistsDAO = playlistsDAO;
    }

    @Inject
    public void setTrackDAO(TrackDAO TrackDAO) {
        this.TrackDAO = TrackDAO;
    }
}
