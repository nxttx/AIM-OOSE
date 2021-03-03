package nl.han.ica.oose.dea;


import nl.han.ica.oose.dea.services.dto.ItemDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemResourceTest {
    private ItemResource itemResource;


    @BeforeEach
    public void setup() {
        itemResource = new ItemResource();
    }


    @Test
    public void getTextItemsTest() {
        var actual = "bread, butter";
        String predict = itemResource.getTextItems();

        assertEquals(predict, actual);
    }

    @Test
    public void getJsonItemsTest() {
        var actual = new ArrayList<ItemDTO>();
        actual.add(new ItemDTO(1, "Bread", new String[]{"Breakfast, Lunch"}, "Delicious!"));
        actual.add(new ItemDTO(2, "Butter", new String[]{"Breakfast, Lunch"}, "Use it with bread"));
        actual.add(new ItemDTO(3, "Honey", new String[]{"Breakfast, Lunch"}, "Use it with bread"));
        int actualStatus = 200;
        Response response = itemResource.getJsonItems();
        ArrayList itemsRaw = (ArrayList) response.getEntity();
        ArrayList items = new ArrayList<ItemDTO>();
        itemsRaw.forEach((item)->{
            items.add((ItemDTO) item);
        });


        // TEST STATUSCODE

        assertEquals(items, actual);
    }

    @Test
    public void alternativeFlowTest() {

    }
}
