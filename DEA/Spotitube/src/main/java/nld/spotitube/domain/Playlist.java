package nld.spotitube.domain;

import java.util.ArrayList;

public class Playlist {
    private Integer id;
    private String name;
    private boolean owner;
    private ArrayList<Track> tracks;

    public Playlist(Integer id, String name, boolean owner, ArrayList<Track> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public Integer getId() {
        return id;
    }

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getName() {
        return name;
    }

//    public void setName(String name) {
//        this.name = name;
//    }

    public boolean getOwner() {
        return owner;
    }

//    public void setOwner(int owner) {
//        this.owner = owner;
//    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

//    public void setTracks(ArrayList<Track> tracks) {
//        this.tracks = tracks;
//    }
}
