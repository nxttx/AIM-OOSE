package service;

import nld.spotitube.dao.UserDAO;
import nld.spotitube.service.LoginROUTE;
import nld.spotitube.service.TracksROUTE;
import nld.spotitube.service.dto.TokenDTO;
import nld.spotitube.service.dto.TracksDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginROUTETest {
    private LoginROUTE loginROUTE;
    @BeforeEach
    public void setUp() {
        loginROUTE = new LoginROUTE();
    }


    @Test
    void login() {
        // Arrange
        int statusCodeExpected = 200;
        String userName = "userA";
        String userPassword = "PassphraseB";
        String userObject = "{\n" +
                "  \"user\":     \""+userName+"\", \n" +
                "  \"password\": \""+userPassword+"\"\n" +
                "}";

        //mock
        UserDAO userDAOMock = mock(UserDAO.class);
        try {
            when(userDAOMock.CheckUserExists(userName, userPassword)).thenReturn(true);
        }catch(Exception e){}
        loginROUTE.setUserDAO(userDAOMock);

        // Act
        Response response =null;
        try {
            response = loginROUTE.Login(userObject);
        }catch(Exception e){}
        TokenDTO responseToken = (TokenDTO) response.getEntity();

        // Assert
        assertEquals(statusCodeExpected, response.getStatus());

        //test content
        assertEquals(userName, responseToken.user);

    }


    @Test
    void loginWrongPassword() {
        // Arrange
        int statusCodeExpected = 401;
        String userName = "userA";
        String userPassword = "PassphraseB";
        String userObject = "{\n" +
                "  \"user\":     \""+userName+"\", \n" +
                "  \"password\": \""+userPassword+"\"\n" +
                "}";

        //mock
        UserDAO userDAOMock = mock(UserDAO.class);
        try {
            when(userDAOMock.CheckUserExists(userName, userPassword)).thenReturn(false);
        }catch(Exception e){}
        loginROUTE.setUserDAO(userDAOMock);

        // Act
        Response response =null;
        try {
            response = loginROUTE.Login(userObject);
        }catch(Exception e){}
        TokenDTO responseToken = (TokenDTO) response.getEntity();

        // Assert
        assertEquals(statusCodeExpected, response.getStatus());

    }

    @Test
    void loginExceptions() {
        // Arrange
        int statusCodeExpected = 400;
        String userName = "userA";
        String userPassword = "PassphraseB";

        String userObjectInvalid = "{\n" +
                "  \"user\":     \""+userName+"\" \n" +
                "  \"password\": \""+userPassword+"\"\n" +
                "}";
        String userObjectNoUser = "{\n" +
                "  \"us2345er\":     \""+userName+"\", \n" +
                "  \"password\": \""+userPassword+"\"\n" +
                "}";

        // Act
        Response responseInvalid =null;
        try {
            responseInvalid = loginROUTE.Login(userObjectInvalid);
        }catch(Exception e){}

        Response responseNoUser =null;
        try {
            responseNoUser = loginROUTE.Login(userObjectNoUser);
        }catch(Exception e){}

        // Assert
        assertEquals(statusCodeExpected, responseInvalid.getStatus());
        assertEquals(statusCodeExpected, responseNoUser.getStatus());

    }
}