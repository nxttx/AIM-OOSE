package nld.spotitube.service.ExceptionMappers;

import nld.spotitube.exceptions.NoRowsAreEffectedException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoRowsAreEffectedExceptionMapper implements ExceptionMapper<NoRowsAreEffectedException> {


    @Override
    public Response toResponse(NoRowsAreEffectedException e) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
