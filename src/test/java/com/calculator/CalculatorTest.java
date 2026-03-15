package com.calculator;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

    // --- Existing tests ---

    @Test
    public void testFactorial() {
        assertEquals(120.0, Calculator.factorial(5), 0.001);
        assertEquals(1.0,   Calculator.factorial(0), 0.001);
    }

    @Test
    public void testFactorialNegative() {
        assertTrue(Double.isNaN(Calculator.factorial(-1)));
    }

    @Test
    public void testSquareRoot() {
        assertEquals(4.0, Calculator.sqrt(16.0), 0.001);
    }

    @Test
    public void testSquareRootNegative() {
        assertTrue(Double.isNaN(Calculator.sqrt(-9)));
    }

    @Test
    public void testNaturalLog() {
        assertEquals(0.0, Calculator.log(1.0), 0.001);
    }

    @Test
    public void testNaturalLogInvalid() {
        assertTrue(Double.isNaN(Calculator.log(0)));
        assertTrue(Double.isNaN(Calculator.log(-5)));
    }

    @Test
    public void testPower() {
        assertEquals(8.0, Calculator.power(2.0, 3.0), 0.001);
    }

    // --- New arithmetic tests ---

    @Test
    public void testAdd() {
        assertEquals(7.0,  Calculator.add(3.0, 4.0),  0.001);
        assertEquals(-1.0, Calculator.add(-3.0, 2.0), 0.001);
    }

    @Test
    public void testSubtract() {
        assertEquals(1.0,  Calculator.subtract(5.0, 4.0),  0.001);
        assertEquals(-7.0, Calculator.subtract(-3.0, 4.0), 0.001);
    }

    @Test
    public void testMultiply() {
        assertEquals(12.0, Calculator.multiply(3.0, 4.0),  0.001);
        assertEquals(0.0,  Calculator.multiply(0.0, 99.0), 0.001);
    }

    @Test
    public void testDivide() {
        assertEquals(2.5, Calculator.divide(5.0, 2.0), 0.001);
    }

    @Test
    public void testDivideByZero() {
        assertTrue(Double.isNaN(Calculator.divide(10.0, 0)));
    }
}
