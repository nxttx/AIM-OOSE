package dao;

import nld.spotitube.domain.Playlist;
import nld.spotitube.domain.Track;
import nld.spotitube.domain.User;

import java.util.ArrayList;

public class DatabaseObjects {
    public static Track track1 = buildTrack(1, "Song for someone", "U2", 350, "The cost",
                                            0, null, null, false);

    public static Track track2 = buildTrack(2, "The cost", "The Flames", 423, "The cost",
                                            37, "2006-03-19", "Description", false);

    public static Track track3 = buildTrack(3, "Ocean and a rock", "Lisa Hannigan", 337, "Sea sew",
                                            0, null, null, false);

    public static ArrayList<Track> tracklistForPop = trackListBuilder(track1, track3);

    public static User user1 = buildUser1();
    public static User user2 = buildUser2();

    public static Playlist playlist1 = new Playlist(10, "Pop", true, tracklistForPop);
    public static Playlist playlist2 = new Playlist(11, "Rock", true, null);
    public static Playlist playlist3 = new Playlist(14, "death metal", true, null);
    public static Playlist playlist4 = new Playlist(15, "House", false, null);

    private static User buildUser1() {
        User newUser1 = new User();
        newUser1.setId(1);
        newUser1.setUsername("UserA");
        newUser1.setUserpassword("Pass");
        newUser1.setToken("1616515808998");
        return newUser1;
    }

    private static User buildUser2() {
        User newUser1 = new User();
        newUser1.setId(2);
        newUser1.setUsername("meron");
        newUser1.setUserpassword("MySuperSecretPassword12341");
        newUser1.setToken("1616515774503");
        return newUser1;
    }

    private static Track buildTrack(int id, String title, String performer, int duration, String album, int playcount, String publicationDate, String description, Boolean offlineAvailable) {
        Track newTrack = new Track();
        newTrack.setId(id);
        newTrack.setTitle(title);
        newTrack.setPerformer(performer);
        newTrack.setDuration(duration);
        newTrack.setAlbum(album);
        newTrack.setPlaycount(playcount);
        newTrack.setPublicationDate(publicationDate);
        newTrack.setDescription(description);
        newTrack.setOfflineAvailable(offlineAvailable);
        return newTrack;
    }

    private static ArrayList<Track> trackListBuilder(Track a, Track b){
        ArrayList<Track> trackList= new ArrayList<>();
        trackList.add(a);
        trackList.add(b);

        return trackList;

    }

}
