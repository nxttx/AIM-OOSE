package aim.oose.dea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {
    private Calculator calculator;

    @BeforeEach
    public void setup() {
        calculator = new Calculator();
    }

    @Test
    public void multiplyTest() {
        int x = 3;
        int y = 3;
        float expected = 9; // 3*3=9

        float result = calculator.multiply(x, y);
        assertEquals(expected, result);
    }

    @Test
    public void addTest() {
        assertEquals(10, calculator.add(5, 5));
        //not happy:
        assertNotEquals(11, calculator.add(5, 5));
    }

    @Test
    public void subtractTest() {
        assertEquals(0, calculator.subtract(5, 5));
        assertEquals(3, calculator.subtract(5, 2));
    }

    @Test
    public void divideHappyFlowTest() {
        assertEquals(10 / 2, calculator.divide(10, 2));
        assertEquals(50 / 5, calculator.divide(50, 5));
    }

    @Test
    public void divideBadFlowTest() {
        // divide by zero
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.divide(10, 0)
        );
    }
}
