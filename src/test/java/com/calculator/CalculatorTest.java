package com.calculator;

import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {

    // 1. Factorial Test
    @Test
    public void testFactorial() {
        assertEquals("Factorial of 5 should be 120", 120.0, Calculator.factorial(5), 0.001);
        assertEquals("Factorial of 0 should be 1", 1.0, Calculator.factorial(0), 0.001);
    }
    
    // 2. Square Root Test
    @Test
    public void testSquareRoot() {
        assertEquals("Square root of 16 should be 4", 4.0, Calculator.sqrt(16.0), 0.001);
    }

    // 3. Natural Logarithm Test
    @Test
    public void testNaturalLog() {
        assertEquals("ln(1) should be 0", 0.0, Calculator.log(1.0), 0.001);
    }

    // 4. Power Function Test
    @Test
    public void testPower() {
        assertEquals("2 raised to 3 should be 8", 8.0, Calculator.power(2.0, 3.0), 0.001);
    }
}
