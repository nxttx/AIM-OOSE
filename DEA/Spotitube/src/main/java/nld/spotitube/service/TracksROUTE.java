package nld.spotitube.service;

import nld.spotitube.dao.ITrackDAO;
import nld.spotitube.dao.TrackDAO;
import nld.spotitube.domain.Track;
import nld.spotitube.service.dto.DTOconverter;
import nld.spotitube.service.dto.TrackDTO;
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
    public Response getTracks(@QueryParam("token") String token, @QueryParam("forPlaylist") int forPlaylist ) {
        ArrayList<Track> tracks;
        if (forPlaylist>0) {
            tracks = TrackDAO.getTracksNotInPlaylist(forPlaylist);
        }else {
            tracks = TrackDAO.getAllTracks();
        }
        ArrayList<TrackDTO> trackList = new ArrayList<TrackDTO>();
        tracks.forEach(track ->{
            TrackDTO newTrack = DTOconverter.TrackToTrackDTO(track);
            trackList.add(newTrack);
        });

        TracksDTO tracksDTO = new TracksDTO();
        tracksDTO.tracks =trackList;

        return Response.status(200).entity(tracksDTO).build();
    }

    //For unittests
    @Inject
    public void setTrackDAO(TrackDAO TrackDAO) {
        this.TrackDAO = TrackDAO;
    }
}
