package nld.spotitube.dao;

import nld.spotitube.domain.Track;
import nld.spotitube.exceptions.NoRowsAreEffectedException;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Default
public class TrackDAO implements ITrackDAO {

    @Resource(name = "jdbc/Spotitube")
    DataSource dataSource;

    @Override
    public ArrayList<Track> getAllTracks() throws SQLException {
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
                track.setOfflineAvailable(false);
                tracks.add(track);
            }
            return tracks;
        } catch (SQLException exception) {
            throw exception;
        }

    }

    @Override
    public Track getTrack(int id) throws SQLException {
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
                track.setOfflineAvailable(false);
                return track;
            }
        } catch (SQLException exception) {
            throw exception;
        }
        return null;
    }

    @Override
    public ArrayList<Track> getTracksFromPlaylist(int id) throws SQLException {
        String sql = "Select t.id, Performer, Title, i.OfflineAvailable, Duration, Album, PublicationDate, Description, " +
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
            throw exception;
        }
    }

    @Override
    public ArrayList<Track> getTracksNotInPlaylist(int id) throws SQLException {
        String sql = "Select * from track where NOT track.id IN(" +
                "    select t.id from track t join track_in_playlist i on t.id = i.Track_id Where i.Playlist_id = ?)";

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
                track.setOfflineAvailable(false);
                tracks.add(track);
            }

            return tracks;

        } catch (SQLException exception) {
            throw exception;
        }
    }

    @Override
    public void addTrackToPlaylist(int playlistId, int trackid,boolean OfflineAvailable) throws NoRowsAreEffectedException, SQLException {
        String sql = "INSERT INTO track_in_playlist(Track_id, Playlist_id, OfflineAvailable) VALUES  (?, ?,?)";

        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, trackid);
            statement.setInt(2, playlistId);
            statement.setBoolean(3, OfflineAvailable);
            int affectedRows = statement.executeUpdate();
            if(affectedRows <1){
                throw new NoRowsAreEffectedException();
            }
        } catch (SQLException exception) {
            throw exception;
        }

    }

    @Override
    public void deleteTrackFromPlaylist(int playlistId, int trackID) throws NoRowsAreEffectedException, SQLException {
        String sql = "DELETE FROM track_in_playlist WHERE Track_id = ? AND Playlist_id = ?";

        try (Connection connection = dataSource.getConnection()){
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, trackID);
            statement.setInt(2, playlistId);
            int affectedRows = statement.executeUpdate();
            if(affectedRows <1){
                throw new NoRowsAreEffectedException();
            }
        } catch (SQLException exception) {
            throw exception;
        }

    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
