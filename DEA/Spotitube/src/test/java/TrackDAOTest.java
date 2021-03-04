import nld.spotitube.dao.TrackDAO;
import nld.spotitube.domain.Track;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class TrackDAOTest {

    @Test
    public void getJediTest(){
        try {
            /**** Arrange ****/
            String expectedSQL = "select * from track where customerId = ?";
            int idToTest = 76576;

            // setup Mocks
            DataSource dataSource = mock(DataSource.class);
            Connection connection = mock(Connection.class);
            PreparedStatement preparedStatement = mock(PreparedStatement.class);
            ResultSet resultSet = mock(ResultSet.class);

            // instruct Mocks
            when(dataSource.getConnection()).thenReturn(connection);
            when(connection.prepareStatement(expectedSQL)).thenReturn(preparedStatement);
            when(preparedStatement.executeQuery()).thenReturn(resultSet);
            when(resultSet.next()).thenReturn(false);

            // setup classes
            TrackDAO trackDAO = new TrackDAO();
            trackDAO.setDataSource(dataSource);

            /**** Act ****/
            Track track = trackDAO.getTrack(idToTest);

            /**** Assert ****/
            verify(connection).prepareStatement(expectedSQL);// TODO: test doesn't work properly
            verify(preparedStatement).setInt(1,idToTest);

            assertNull(track);

        }
        catch (Exception e){
            fail();
        }
    }
}
