package nld.spotitube.service.ExceptionMappers;

import nld.spotitube.exceptions.NoRowsAreEffectedException;
import nld.spotitube.exceptions.PlaylistNoNameException;

import javax.persistence.PersistenceException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PlaylistNoNameExceptionMapper implements ExceptionMapper<PlaylistNoNameException> {


    @Override
    public Response toResponse(PlaylistNoNameException e) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
