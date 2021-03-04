package nld.spotitube.dao;

import nld.spotitube.domain.Playlist;
import nld.spotitube.domain.Playlists;
import nld.spotitube.domain.Track;

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

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            Playlists playlists = new Playlists();
            ArrayList <Playlist> PlaylistsList= new ArrayList<Playlist>();
            while (resultSet.next()){


                ArrayList<Track> trackList = new ArrayList<Track>();


                String sql2 = "SELECT track.Title, track.Performer, track.OfflineAvailable, track.Duration," +
                        " track.Album, track.PublicationDate, track.Description, track.playcount " +
                        "FROM track_in_playlist JOIN track ON `Track_id` = track.id WHERE Playlist_id = ?";
                try {
                    Connection connection2 = dataSource.getConnection();
                    PreparedStatement statement2 = connection2.prepareStatement(sql2);
                    statement2.setInt(1, resultSet.getInt("id"));
                    ResultSet resultSet2= statement2.executeQuery();


                    while (resultSet2.next()){
                        Track track = new Track();
                        trackList.add(track);

                    }
                    Playlist playlist = new Playlist(resultSet.getInt("id"),resultSet.getString("Naam"),resultSet.getInt("Eigenaar"), trackList);
                    PlaylistsList.add(playlist);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }

            }
            playlists.setPlaylists(PlaylistsList);
            return playlists;

        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return null;

    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
