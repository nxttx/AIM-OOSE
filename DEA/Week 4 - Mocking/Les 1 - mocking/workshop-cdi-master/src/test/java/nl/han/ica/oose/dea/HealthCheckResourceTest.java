package nl.han.ica.oose.dea;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HealthCheckResourceTest {
    private HealthCheckResource healthCheckResource;


    @BeforeEach
    public void setup() {
        healthCheckResource = new HealthCheckResource();
    }


    @Test
    public void HappyFlowTest() {
        var actual = "Up & Running";
        String predict = healthCheckResource.healthy();

        assertEquals(predict, actual);

    }

    @Test
    public void alternativeFlowTest() {

    }
}
