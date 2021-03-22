package nld.spotitube.service;

import nld.spotitube.dao.IUserDAO;
import nld.spotitube.dao.UserDAO;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@PreMatching             // (1)
@Provider                // (2)
public class CheckToken implements ContainerRequestFilter {

    private IUserDAO userDAO = new UserDAO();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {//todo unittests

        var uriInfo = requestContext.getUriInfo();
        var path = uriInfo.getPath();
        var parameters = uriInfo.getQueryParameters();
        String token = parameters.getFirst("token");
        if (path.equals("login")) {
        //nothing
        } else if (userDAO.CheckTokenExists(token)) {
        //nothing
        } else {
            requestContext.abortWith(Response.status(
                    Response.Status.UNAUTHORIZED).build());
        }
    }

    @Inject
    public void setUserDAO(UserDAO LoginDOA) {
        this.userDAO = LoginDOA;
    }
}