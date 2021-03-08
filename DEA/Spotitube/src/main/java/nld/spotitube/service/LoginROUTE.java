package nld.spotitube.service;

import nld.spotitube.service.dto.TokenDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("login")
public class LoginROUTE {

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(){
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.token="1";
        tokenDTO.user="Meron Brouwer";
        return Response.status(200).entity(tokenDTO).build();

    }
}
