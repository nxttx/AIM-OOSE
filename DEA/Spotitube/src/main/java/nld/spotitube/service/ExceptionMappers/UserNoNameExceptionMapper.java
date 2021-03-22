package nld.spotitube.service.ExceptionMappers;

import nld.spotitube.exceptions.UserNoNameException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UserNoNameExceptionMapper implements ExceptionMapper<UserNoNameException> {


    @Override
    public Response toResponse(UserNoNameException e) {
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
