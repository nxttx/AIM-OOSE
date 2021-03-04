package nld.spotitube.service.dto;

import nld.spotitube.domain.Track;

import java.util.List;

public class PlaylistDTO {
    public Integer id;
    public String name;
    public int owner;
    public List<Track> tracks;
}
