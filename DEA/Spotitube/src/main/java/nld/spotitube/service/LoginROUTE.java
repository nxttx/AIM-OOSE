package nld.spotitube.service;

import com.google.gson.JsonSyntaxException;
import nld.spotitube.dao.IUserDAO;
import nld.spotitube.dao.UserDAO;
import nld.spotitube.exceptions.NoRowsAreEffectedException;
import nld.spotitube.exceptions.UserNoNameException;
import nld.spotitube.service.dto.DTOconverter;
import nld.spotitube.service.dto.TokenDTO;
import nld.spotitube.service.dto.UserDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("login")
public class LoginROUTE {

    private IUserDAO userDAO = new UserDAO();

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response Login(String body) throws NoRowsAreEffectedException, SQLException {//todo unittests
        //build body to object
        UserDTO userDTO;
        try {
            userDTO = DTOconverter.JSONToUserDTO(body);
        }catch(UserNoNameException | JsonSyntaxException E){
            return Response.status(400).build();
        }

        if(userDAO.CheckUserExists(userDTO.user, userDTO.password)){ // user exists
            String Token = String.valueOf(System.currentTimeMillis());
            userDAO.SetToken(userDTO.user, Token);
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.token=Token;
            tokenDTO.user=userDTO.user;
            return Response.status(200).entity(tokenDTO).build();
        }else{// user !exists
            return Response.status(401).build();
        }



    }

    @Inject
    public void setUserDAO(UserDAO LoginDOA) {
        this.userDAO = LoginDOA;
    }
}
