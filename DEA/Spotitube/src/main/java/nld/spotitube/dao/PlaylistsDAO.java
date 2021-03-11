package nld.spotitube.dao;

import nld.spotitube.domain.Playlist;
import nld.spotitube.domain.Playlists;
import nld.spotitube.domain.Track;
import nld.spotitube.service.dto.PlaylistDTO;

import javax.annotation.Resource;
import javax.enterprise.inject.Default;
import javax.lang.model.type.ArrayType;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Default
public class PlaylistsDAO implements IPlaylistsDAO {

    @Resource(name = "jdbc/Spotitube")
    DataSource dataSource;

    @Override
    public Playlists getPlaylists(){
        String sql = "select * from Playlist";

        try (Connection connection = dataSource.getConnection()){

            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            Playlists playlists = new Playlists();
            ArrayList <Playlist> PlaylistsList= new ArrayList<Playlist>();

            int length = 0;
            while (resultSet.next()){

                ArrayList<Track> trackList = new ArrayList<Track>();
                String sql2 = "SELECT track.id, track.Title, track.Performer, track.OfflineAvailable, track.Duration," +
                        " track.Album, track.PublicationDate, track.Description, track.playcount " +
                        "FROM track_in_playlist JOIN track ON `Track_id` = track.id WHERE Playlist_id = ?";
                try(Connection connection2 = dataSource.getConnection()) {

                    PreparedStatement statement2 = connection2.prepareStatement(sql2);
                    statement2.setInt(1, resultSet.getInt("id"));
                    ResultSet resultSet2= statement2.executeQuery();

                    while (resultSet2.next()){
                        Track track = new Track();
                        track.setId(resultSet2.getInt("id"));
                        track.setTitle(resultSet2.getString("title"));
                        track.setPerformer(resultSet2.getString("performer"));
                        track.setDuration(resultSet2.getInt("Duration"));
                        track.setAlbum(resultSet2.getString("album"));
                        track.setPlaycount(resultSet2.getInt("playcount"));
                        track.setPublicationDate(resultSet2.getString("publicationdate"));
                        track.setDescription(resultSet2.getString("Description"));
                        track.setOfflineAvailable(resultSet2.getBoolean("OfflineAvailable"));
                        trackList.add(track);

                        length+=resultSet2.getInt("Duration");
                    }

                    Playlist playlist = new Playlist(resultSet.getInt("id"),resultSet.getString("Naam"),resultSet.getBoolean("Eigenaar"), trackList);
                    PlaylistsList.add(playlist);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }


            playlists.setPlaylists(PlaylistsList);
            playlists.setLength(length);
            return playlists;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;

    }

    @Override
    public void addPlaylist(PlaylistDTO playlistDTO){
        String sql = "INSERT INTO Playlist (Naam, Eigenaar) Values (?,?)";

        String name = playlistDTO.name;
        //todo fix owner system.
        boolean owner = true ; //playlistDTO.owner;

        try (Connection connection = dataSource.getConnection()){

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setBoolean(2, owner);
            int affectedRows = statement.executeUpdate();

            //!!Important!! todo think about error handling
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
