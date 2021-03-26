package service.exceptionmappers;

import nld.spotitube.exceptions.NoRowsAreEffectedException;
import nld.spotitube.exceptions.PlaylistNoNameException;
import nld.spotitube.exceptions.TrackNoTitleException;
import nld.spotitube.exceptions.UserNoNameException;
import nld.spotitube.service.ExceptionMappers.*;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionMappersTest {

    @Test
    public void NoRowsAreEffectedExceptionMapper(){
        int statusCodeExpected = 400;

        var mapper = new NoRowsAreEffectedExceptionMapper();
        Response response =  mapper.toResponse(new NoRowsAreEffectedException());

        assertEquals(statusCodeExpected,response.getStatus());
    }


    @Test
    public void PlaylistNoNameExceptionMapper(){
        int statusCodeExpected = 400;

        var mapper = new PlaylistNoNameExceptionMapper();
        Response response =  mapper.toResponse(new PlaylistNoNameException());

        assertEquals(statusCodeExpected,response.getStatus());
    }

    @Test
    public void SQLExceptionMapper(){
        int statusCodeExpected = 500;

        var mapper = new SQLExceptionMapper();
        Response response =  mapper.toResponse(new SQLException());

        assertEquals(statusCodeExpected,response.getStatus());
    }


    @Test
    public void TrackNoTitleExceptionMapper(){
        int statusCodeExpected = 400;

        var mapper = new TrackNoTitleExceptionMapper();
        Response response =  mapper.toResponse(new TrackNoTitleException());

        assertEquals(statusCodeExpected,response.getStatus());
    }


    @Test
    public void UserNoNameExceptionMapper(){
        int statusCodeExpected = 400;

        var mapper = new UserNoNameExceptionMapper();
        Response response =  mapper.toResponse(new UserNoNameException());

        assertEquals(statusCodeExpected,response.getStatus());
    }
}
