package nld.spotitube.service;

import nld.spotitube.dao.ITrackDAO;
import nld.spotitube.dao.TrackDAO;
import nld.spotitube.domain.Track;
import nld.spotitube.service.dto.TracksDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("tracks")
public class TracksROUTE {

    private ITrackDAO TrackDAO = new TrackDAO();

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTracks(@QueryParam("token") int token, @QueryParam("forPlaylist") int forPlaylist) {

        if (forPlaylist == 1) {
            return Response.status(200).build();
        }
        ArrayList<Track> tracks = TrackDAO.getAllTracks();
        //build tracks to tracks dto and every track to trackdto
        //Love you, you are the best! You can do this!!!

        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.Tracks=tracks;
        tracksDTO.Tracks =new ArrayList<Track>();

        return Response.status(200).entity(tracks).build();
    }

    //For unittests
    @Inject
    public void setTrackDAO(TrackDAO TrackDAO) {
        this.TrackDAO = TrackDAO;
    }
}
