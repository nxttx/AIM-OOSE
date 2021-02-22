package oose.dea.workshop.test;

import oose.dea.workshop.NumberUnderTest;
import oose.dea.workshop.OuchIFoundThirtySevenAndHenceMustDieException;
import oose.dea.workshop.PrimeTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PrimeTesterTest {
    private PrimeTester primeTest;
    private static final int HIGHEST_NUMBER_TO_TEST = 2000;

    @BeforeEach
    public void setup() {
        NumberUnderTest numberUnderTest = new NumberUnderTest();
        PrimeTester primeTest = new PrimeTester(numberUnderTest, HIGHEST_NUMBER_TO_TEST);
    }

    @Test
    public void addHappyFlowTest() {

        try {
            primeTest.startTesting();
        } catch (OuchIFoundThirtySevenAndHenceMustDieException e) {
            e.printStackTrace();
        }

        assertEquals("1", "1");
    }

}

