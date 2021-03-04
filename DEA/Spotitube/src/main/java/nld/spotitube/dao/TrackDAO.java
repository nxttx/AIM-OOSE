package nld.spotitube.dao;

import nld.spotitube.domain.Track;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.sql.DataSource;
import java.sql.*;

@Default
public class TrackDAO implements ITrackDAO {



    @Resource(name = "jdbc/Spotitube")
    DataSource dataSource;

    @Override
    public Track getTrack(int id){
        String sql = "select * from track where customerId = ?";

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Track Track = new Track();
                Track.setId(resultSet.getInt("id"));
                return Track;
            }

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;

    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
