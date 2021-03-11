package nld.spotitube.dao;

import nld.spotitube.domain.Track;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.sql.DataSource;
import javax.ws.rs.DELETE;
import java.sql.*;
import java.util.ArrayList;

@Default
public class TrackDAO implements ITrackDAO {

    @Resource(name = "jdbc/Spotitube")
    DataSource dataSource;

    @Override
    public ArrayList<Track> getAllTracks() {
        String sql = "select * from track";

        try (Connection connection = dataSource.getConnection())  {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            ArrayList<Track> tracks = new ArrayList<>();

            while (resultSet.next()) {
                Track track = new Track();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("Duration"));
                track.setAlbum(resultSet.getString("album"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setPublicationDate(resultSet.getString("publicationdate"));
                track.setDescription(resultSet.getString("Description"));
                track.setOfflineAvailable(resultSet.getBoolean("OfflineAvailable"));
                tracks.add(track);
            }
            return tracks;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;

    }

    @Override
    public Track getTrack(int id) {
        String sql = "select * from track where id = ?";

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Track track = new Track();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("Duration"));
                track.setAlbum(resultSet.getString("album"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setPublicationDate(resultSet.getString("publicationdate"));
                track.setDescription(resultSet.getString("Description"));
                track.setOfflineAvailable(resultSet.getBoolean("OfflineAvailable"));
                return track;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;

    }

    @Override
    public ArrayList<Track> getTracksFromPlaylist(int id) {
        String sql = "select t.id, Performer, Title, OfflineAvailable, Duration, Album, PublicationDate, Description, " +
                "playcount from track t join track_in_playlist i on t.id = i.Track_id where i.Playlist_id = ?";

        try (Connection connection = dataSource.getConnection()){

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Track> tracks = new ArrayList<>();

            while (resultSet.next()) {
                Track track = new Track();
                track.setId(resultSet.getInt("id"));
                track.setTitle(resultSet.getString("title"));
                track.setPerformer(resultSet.getString("performer"));
                track.setDuration(resultSet.getInt("Duration"));
                track.setAlbum(resultSet.getString("album"));
                track.setPlaycount(resultSet.getInt("playcount"));
                track.setPublicationDate(resultSet.getString("publicationdate"));
                track.setDescription(resultSet.getString("Description"));
                track.setOfflineAvailable(resultSet.getBoolean("OfflineAvailable"));
                tracks.add(track);
            }

            return tracks;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
