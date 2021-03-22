package nld.spotitube.service;

import nld.spotitube.dao.IUserDAO;
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

    @Override
    public void filter(ContainerRequestContext requestContext) {//todo unittests

        var uriInfo = requestContext.getUriInfo();
        var path = uriInfo.getPath();
        var parameters = uriInfo.getQueryParameters();
        String token = parameters.getFirst("token");
        try {
            if (path.equals("login") || userDAO.CheckTokenExists(token)) {
                //nothing
            } else {
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
}