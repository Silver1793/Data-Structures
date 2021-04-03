package project4;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test cases for the Decimal to Binary method.
 *
 * @author Harrison Douglass, Rira Shimizu, Crona Xu
 * This version of the code has been adapted by Harrison Douglass from another version
 * of this code originally written for Lab 9 by all of the authors listed above.
 */
public class DecimalToBinaryTest {

    /**
     * Test inputting -1 (largest negative number) into the function.
     * Expected result: null
     */
    @Test
    public void testNegativeOne() {
        assertNull(Converter.decimalToBinary(-1));
    }

    /**
     * Test inputting the minimum Integer value into the function.
     * Expected result: null
     */
    @Test
    public void testNegativeMinVal() {
        assertNull(Converter.decimalToBinary(Integer.MIN_VALUE));
    }

    /**
     * Test inputting an arbitrary negative number into the function.
     * Expected result: null
     */
    @Test
    public void testNegativeArbitraryVal() {
        assertNull(Converter.decimalToBinary(-148759));
    }

    /**
     * Test inputting zero into the function.
     * Expected result: 0b0
     */
    @Test
    public void testZero() {
        assertEquals("0b0", Converter.decimalToBinary(0));
    }

    /**
     * Test inputting the max Integer value into the function.
     * Expected result: 0b1111111111111111111111111111111
     */
    @Test
    public void testMaxInt() {
        assertEquals("0b1111111111111111111111111111111", Converter.decimalToBinary(Integer.MAX_VALUE));
    }

    /**
     * Test inputting 1 into the function.
     * Expected result: 0b1
     */
    @Test
    public void testOne() {
        assertEquals("0b1", Converter.decimalToBinary(1));
    }

    // Special thanks to https://www.rapidtables.com/convert/number/decimal-to-binary.html

    /**
     * Test inputting a valid, arbitrary sequence.
     * Expected result: 0b100100101001
     */
    @Test
    public void testArbitraryValue1() {
        assertEquals("0b100100101001", Converter.decimalToBinary(2345));
    }

    /**
     * Test inputting a second valid, arbitrary sequence.
     * Expected result: 0b10011100011001001000111
     */
    @Test
    public void testArbitraryValue2() {
        assertEquals("0b10011100011001001000111", Converter.decimalToBinary(5124679));
    }

}