package nld.spotitube.service.dto;

import java.util.List;

public class PlaylistDTO {
    public Integer id;
    public String name;
    public boolean owner;
    public List<TrackDTO> tracks;
}
