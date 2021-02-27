package nl.han.oose.dea.rest.services;

import nl.han.oose.dea.rest.services.dto.ItemDTO;

import javax.enterprise.inject.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/items")
public class ItemResource {

    private ItemService itemService;

    public ItemResource() {
        this.itemService = new ItemService();
    }

//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String healthy() {
//        return "bread, butter";
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ItemDTO> healthy() {
        return "[\"bread\", \"butter\"]";
    }
}