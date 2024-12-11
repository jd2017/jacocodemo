package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DemoTest {
    @Test
    public void testAdd() {
        Demo calculator = new Demo();
        assertEquals(5, calculator.add(2, 3), "2 + 3 should equal 5");
    }

    @Test
    public void testSubtract() {
        Demo mathOps = new Demo();
        assertEquals(1, mathOps.subtract(3, 2));
    }

    @Test
    public void testMultiply() {
        Demo mathOps = new Demo();
        assertEquals(6, mathOps.multiply(2, 3));
    }

    @Test
    public void testDivide() {
        Demo mathOps = new Demo();
        assertEquals(2.0, mathOps.divide(4, 2));
    }

    @Test
    public void testDivideByZero() {
        Demo mathOps = new Demo();
        assertThrows(ArithmeticException.class, () -> mathOps.divide(4, 0));
    }

    @Test
    public void testIsEven() {
        Demo mathOps = new Demo();
        assertTrue(mathOps.isEven(4));
        assertFalse(mathOps.isEven(5));
    }


}
