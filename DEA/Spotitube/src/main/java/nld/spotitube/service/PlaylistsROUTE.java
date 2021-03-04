package nld.spotitube.service;

import nld.spotitube.dao.IPlaylistsDAO;
import nld.spotitube.dao.PlaylistsDAO;
import nld.spotitube.domain.Playlist;
import nld.spotitube.domain.Playlists;
import nld.spotitube.domain.Track;
import nld.spotitube.service.dto.PlaylistDTO;
import nld.spotitube.service.dto.PlaylistsDTO;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("playlists")
public class PlaylistsROUTE {

    private IPlaylistsDAO PlaylistsDAO;

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPlaylists(){
        Playlists playlists = PlaylistsDAO.getPlaylists();


        PlaylistsDTO playlistsDTO = new PlaylistsDTO();
        PlaylistDTO playlistA = new PlaylistDTO();
        playlistA.id=0;
        playlistA.name ="Death metal";
        playlistA.owner= 1;
        playlistA.tracks = new ArrayList<Track>();
        PlaylistDTO playlistB = new PlaylistDTO();
        playlistB.id=1;
        playlistB.name ="Pop";
        playlistB.owner= 1;
        playlistB.tracks = new ArrayList<Track>();
        playlistsDTO.playlists = new ArrayList<PlaylistDTO>();
        playlistsDTO.playlists.add(playlistA);
        playlistsDTO.playlists.add(playlistB);
        playlistsDTO.length = 0;

//        return Response.status(200).entity(playlistsDTO).build();
        return Response.status(200).entity(playlists).build(); // prob doesnt work becouse every thing is protected
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


    //For unittests
    @Inject
    public void setPlaylistsDAO(PlaylistsDAO playlistsDAO) {
        this.PlaylistsDAO = playlistsDAO;
    }

}
