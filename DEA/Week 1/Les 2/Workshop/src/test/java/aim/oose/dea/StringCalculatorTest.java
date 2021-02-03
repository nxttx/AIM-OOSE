package aim.oose.dea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StringCalculatorTest {
    private StringCalculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new StringCalculator();
    }


    @Test
    public void addHappyFlowTest() {
        assertEquals(0, calculator.add(""));
        assertEquals(1, calculator.add("1"));
        assertEquals(2, calculator.add("2"));

        assertEquals(4, calculator.add("4"));
        assertEquals(5, calculator.add("5"));
        assertEquals(3, calculator.add("1,2"));

        assertEquals(3, calculator.add("1\n2"));
        assertEquals(6, calculator.add("1,2\n3"));

        assertEquals(3, calculator.add("//[;]\n1;2"));
        assertEquals(6, calculator.add("//[,]\n1,2,3"));

        assertEquals(6, calculator.add("//[,,,]\n1,,,2,,,3"));

        assertEquals(6, calculator.add("//[,][;]\n1,2;3"));
    }

    @Test
    public void addUnHappyFlowTest() {
        assertEquals(6, calculator.add("//[,]\n1,2,3,1001"));
    }
}

