package nld.spotitube.service.ExceptionMappers;

import nld.spotitube.exceptions.TrackNoTitleException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class TrackNoTitleExceptionMapper implements ExceptionMapper<TrackNoTitleException> {


    @Override
    public Response toResponse(TrackNoTitleException e) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
