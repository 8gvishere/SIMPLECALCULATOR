import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CalculatorUITest {

    private final CalculatorUI calculator = new CalculatorUI();

    @Test
    public void testAddition() {
        assertEquals(7.0, calculator.calculate(3.0, 4.0, "+"), 0.0001);
    }

    @Test
    public void testSubtraction() {
        assertEquals(-1.0, calculator.calculate(3.0, 4.0, "-"), 0.0001);
    }

    @Test
    public void testMultiplication() {
        assertEquals(12.0, calculator.calculate(3.0, 4.0, "*"), 0.0001);
    }

    @Test
    public void testDivision() {
        assertEquals(2.0, calculator.calculate(6.0, 3.0, "/"), 0.0001);
    }

    @Test
    public void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.calculate(6.0, 0.0, "/"));
    }

    @Test
    public void testInvalidOperator() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(6.0, 3.0, "%"));
    }

    @Test
    public void testLargeNumberAddition() {
        assertEquals(3000000.0, calculator.calculate(1000000.0, 2000000.0, "+"), 0.0001);
    }

    @Test
    public void testFloatingPointAddition() {
        assertEquals(4.0, calculator.calculate(1.5, 2.5, "+"), 0.0001);
    }

    @Test
    public void testNegativeNumbersSubtraction() {
        assertEquals(-5.0, calculator.calculate(-2.0, 3.0, "-"), 0.0001);
    }

    @Test
    public void testNegativeMultiplication() {
        assertEquals(-6.0, calculator.calculate(-2.0, 3.0, "*"), 0.0001);
    }
}