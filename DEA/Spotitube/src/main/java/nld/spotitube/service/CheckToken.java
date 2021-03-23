package nld.spotitube.service;

import nld.spotitube.dao.IUserDAO;
import nld.spotitube.dao.PlaylistsDAO;
import nld.spotitube.dao.UserDAO;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.sql.SQLException;

@PreMatching
@Provider
public class CheckToken implements ContainerRequestFilter {

    private IUserDAO userDAO = new UserDAO();
    private PlaylistsDAO playlistsDAO = new PlaylistsDAO();

    @Override
    public void filter(ContainerRequestContext requestContext) {

        var uriInfo = requestContext.getUriInfo();
        var path = uriInfo.getPath();
        var pathSegments = uriInfo.getPathSegments();
        var parameters = uriInfo.getQueryParameters();
        String token = parameters.getFirst("token");

        try {
            if (path.equals("login")) {
                //nothing
            } else if(userDAO.CheckTokenExists(token)){
                // check if http method is get, else check if user is allowed to change that playlist.
                if(requestContext.getMethod().equals("GET")){
                    //nothing
                }else{
                    //check if user is allowed to change that playlist
                    var firstURIParameter = pathSegments.get(0).getPath();
                    if(firstURIParameter.equals("playlists")){
                        var SecondURIParameter = pathSegments.get(1).getPath();
                        var playlistOwnerToken = playlistsDAO.getTokenOfOwner(Integer.parseInt(SecondURIParameter));
                        if(token.equals(playlistOwnerToken)){
                            //nothing
                        }else{
                            requestContext.abortWith(Response.status(
                                    Response.Status.UNAUTHORIZED).build());
                        }
                    }else{
                        //nothing
                    }
                }
            }else {
                requestContext.abortWith(Response.status(
                        Response.Status.UNAUTHORIZED).build());
            }
        } catch (SQLException throwables) {
            requestContext.abortWith(Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR).build());
        }
    }



    @Inject
    public void setUserDAO(UserDAO LoginDOA) {
        this.userDAO = LoginDOA;
    }

    @Inject
    public void setPlaylistDAO(PlaylistsDAO playlistsDAO) {
        this.playlistsDAO = playlistsDAO;
    }
}