package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class TestConnection {
    //https://docs.oracle.com/javase/tutorial/jdbc/basics/connecting.html
    private String userName = "root";
    private String password = "root";
    private String dbms = "mysql";
    private String serverName = "localhost";
    private int portNumber = 3306;
    private String database = "spotitube";

    public Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", this.userName);
        connectionProps.put("password", this.password);

        if (this.dbms.equals("mysql")) {
            conn = DriverManager.getConnection(
                    "jdbc:" + this.dbms + "://" +
                            this.serverName +
                            ":" + this.portNumber + "/"+this.database,
                    connectionProps);
        }
        return conn;
    }

    public void restoreDatabase() {
        ArrayList<String> sqlList = new ArrayList<>();
        sqlList.add(
                "DROP TABLE IF EXISTS `track_in_playlist`;\n");
        sqlList.add(
                "CREATE TABLE IF NOT EXISTS `track_in_playlist` (\n" +
                        "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `Playlist_id` int(11) NOT NULL,\n" +
                        "  `Track_id` int(11) NOT NULL,\n" +
                        "  `OfflineAvailable` tinyint(1) NOT NULL DEFAULT 0,\n" +
                        "  PRIMARY KEY (`id`),\n" +
                        "  UNIQUE KEY `Playlist_id` (`Playlist_id`,`Track_id`),\n" +
                        "  KEY `fk_track` (`Track_id`)\n" +
                        ") ");

        sqlList.add(
                "INSERT INTO `track_in_playlist` (`id`, `Playlist_id`, `Track_id`, `OfflineAvailable`) VALUES\n" +
                        "(11, "+DatabaseObjects.playlist1.getId()+", "+DatabaseObjects.track1.getId()+", 0),\n" +
                        "(12, "+DatabaseObjects.playlist1.getId()+", "+DatabaseObjects.track3.getId()+", 1);\n");


        sqlList.add("\n" +
                "DROP TABLE IF EXISTS `playlist`;\n" );
        sqlList.add(
                "CREATE TABLE IF NOT EXISTS `playlist` (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `Naam` varchar(50) NOT NULL,\n" +
                "  `Eigenaar` int(11) NOT NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  KEY `FK_playlist_user` (`Eigenaar`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;\n");

        sqlList.add("INSERT INTO `playlist` (`id`, `Naam`, `Eigenaar`) VALUES\n" +
                "("+DatabaseObjects.playlist1.getId()+", '"+DatabaseObjects.playlist1.getName()+"', "+DatabaseObjects.user1.getId()+"),\n" +
                "("+DatabaseObjects.playlist2.getId()+", '"+DatabaseObjects.playlist2.getName()+"', "+DatabaseObjects.user1.getId()+"),\n" +
                "("+DatabaseObjects.playlist3.getId()+", '"+DatabaseObjects.playlist3.getName()+"', "+DatabaseObjects.user1.getId()+"),\n" +
                "("+DatabaseObjects.playlist4.getId()+", '"+DatabaseObjects.playlist4.getName()+"', "+DatabaseObjects.user2.getId()+");\n");

        sqlList.add(
                "DROP TABLE IF EXISTS `track`;\n" );
        sqlList.add(
                        "CREATE TABLE IF NOT EXISTS `track` (\n" +
                        "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `Performer` varchar(75) NOT NULL,\n" +
                        "  `Title` varchar(75) NOT NULL,\n" +
                        "  `Duration` int(11) NOT NULL,\n" +
                        "  `Album` varchar(75) DEFAULT NULL,\n" +
                        "  `PublicationDate` date DEFAULT NULL,\n" +
                        "  `Description` varchar(255) DEFAULT NULL,\n" +
                        "  `playcount` int(11) DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`id`)\n" +
                        ")");

        sqlList.add(
                "INSERT INTO `track` (`id`, `Performer`, `Title`, `Duration`, `Album`, `PublicationDate`, `Description`, `playcount`) VALUES\n" +
                        "("+DatabaseObjects.track1.getId()+", '"+DatabaseObjects.track1.getPerformer()+"', '"+DatabaseObjects.track1.getTitle()+"',"+DatabaseObjects.track1.getDuration()+", '"+DatabaseObjects.track1.getAlbum()+"', "+DatabaseObjects.track1.getPublicationDate()+","+DatabaseObjects.track1.getDescription()+","+DatabaseObjects.track1.getPlaycount()+"),\n" +
                        "("+DatabaseObjects.track2.getId()+", '"+DatabaseObjects.track2.getPerformer()+"', '"+DatabaseObjects.track2.getTitle()+"',"+DatabaseObjects.track2.getDuration()+", '"+DatabaseObjects.track2.getAlbum()+"', '"+DatabaseObjects.track2.getPublicationDate()+"','"+DatabaseObjects.track2.getDescription()+"',"+DatabaseObjects.track2.getPlaycount()+"),\n" +
                        "("+DatabaseObjects.track3.getId()+", '"+DatabaseObjects.track3.getPerformer()+"', '"+DatabaseObjects.track3.getTitle()+"',"+DatabaseObjects.track3.getDuration()+", '"+DatabaseObjects.track3.getAlbum()+"', "+DatabaseObjects.track3.getPublicationDate()+","+DatabaseObjects.track3.getDescription()+","+DatabaseObjects.track3.getPlaycount()+");\n");

        sqlList.add(
                "DROP TABLE IF EXISTS `users`;\n");
        sqlList.add(
                        "CREATE TABLE IF NOT EXISTS `users` (\n" +
                        "  `id` int(11) NOT NULL,\n" +
                        "  `Username` varchar(50) NOT NULL,\n" +
                        "  `UserPassword` varchar(255) NOT NULL,\n" +
                        "  `Token` varchar(64) DEFAULT NULL,\n" +
                        "  `dateOfToken` date DEFAULT NULL,\n" +
                        "  PRIMARY KEY (`id`)\n" +
                        ")");

        sqlList.add(
                "INSERT INTO `users` (`id`, `Username`, `UserPassword`, `Token`, `dateOfToken`) VALUES\n" +
                        "('"+DatabaseObjects.user1.getId()+"', '"+DatabaseObjects.user1.getUsername()+"', '"+DatabaseObjects.user1.getUserpassword()+"', '"+DatabaseObjects.user1.getToken()+"', NULL),\n" +
                        "('"+DatabaseObjects.user2.getId()+"', '"+DatabaseObjects.user2.getUsername()+"', '"+DatabaseObjects.user2.getUserpassword()+"', '"+DatabaseObjects.user2.getToken()+"', NULL);\n");

        sqlList.add(
                "ALTER TABLE `playlist`\n" +
                        "  ADD CONSTRAINT `FK_playlist_user` FOREIGN KEY (`Eigenaar`) REFERENCES `users` (`id`);\n");

        sqlList.add(
                "ALTER TABLE `track_in_playlist`\n" +
                        "  ADD CONSTRAINT `fk_Playlist_id` FOREIGN KEY (`Playlist_id`) REFERENCES `playlist` (`id`) ON DELETE CASCADE,\n" +
                        "  ADD CONSTRAINT `fk_track` FOREIGN KEY (`Track_id`) REFERENCES `track` (`id`);\n");
        sqlList.add(
                "COMMIT;");

        sqlList.forEach((sql) -> {
            try (Connection connection = this.getConnection()) {
                PreparedStatement statement = connection.prepareStatement(sql);
                boolean resultSet = statement.execute();


            } catch (Exception e) {

            }
        });
    }
}
