package com.calculator;
import static org.junit.Assert.*;
import org.junit.Test;

public class CalculatorTest {
    @Test
    public void testFactorial() {
        assertEquals(120.0, Calculator.factorial(5), 0.001);
    }
    
    @Test
    public void testSquareRoot() {
        assertEquals(4.0, Math.sqrt(16.0), 0.001);
    }
}
