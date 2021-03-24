package dao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TrackDAO {

    TestConnection connectionBuilder =new TestConnection();
    Connection databaseConnection;
    @BeforeAll
    private void beforeAll(){
        try {
            databaseConnection = connectionBuilder.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @BeforeEach
    private void beforeEach(){
        connectionBuilder.restoreDatabase();
    }

    @Test
    public void getAllTracks(){

    }

}
