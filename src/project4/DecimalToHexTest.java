package project4;

import org.junit.Test;

import static org.junit.Assert.*;

public class DecimalToHexTest {

    /**
     * Test inputting -1 (largest negative number) into the function.
     * Expected result: null
     */
    @Test
    public void testNegativeOne() {
        assertNull(Converter.decimalToHex(-1));
    }

    /**
     * Test inputting the minimum Integer value into the function.
     * Expected result: null
     */
    @Test
    public void testNegativeMinVal() {
        assertNull(Converter.decimalToHex(Integer.MIN_VALUE));
    }

    /**
     * Test inputting an arbitrary negative number into the function.
     * Expected result: null
     */
    @Test
    public void testNegativeArbitraryVal() {
        assertNull(Converter.decimalToHex(-148759));
    }

    /**
     * Test inputting zero into the function.
     * Expected result: 0x0
     */
    @Test
    public void testHexZero() {
        assertEquals("0x0", Converter.decimalToHex(0));
    }

    /**
     * Test inputting the max Integer value into the function.
     * Expected result: 0x7FFFFFFF
     */
    @Test
    public void testHexMaxVal() {
        assertEquals("0x7FFFFFFF", Converter.decimalToHex(Integer.MAX_VALUE));
    }

    /**
     * Test inputting 1 into the function.
     * Expected result: 0x1
     */
    @Test
    public void testOne() {
        assertEquals("0x1", Converter.decimalToHex(1));
    }

    // Special thanks to https://www.rapidtables.com/convert/number/decimal-to-hex.html

    /**
     * Test inputting a valid, arbitrary sequence.
     * Expected result: 0x929
     */
    @Test
    public void testArbitraryValue1() {
        assertEquals("0x929", Converter.decimalToHex(2345));
    }

    /**
     * Test inputting a second valid, arbitrary sequence.
     * Expected result: 0x4E3247
     */
    @Test
    public void testArbitraryValue2() {
        assertEquals("0x4E3247", Converter.decimalToHex(5124679));
    }
}