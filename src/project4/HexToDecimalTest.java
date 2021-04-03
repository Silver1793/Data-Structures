package project4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the Hex to Decimal method.
 *
 * @author Harrison Douglass
 */
public class HexToDecimalTest {

    /**
     * Test inputting null as the function argument.
     * Expected result: IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void nullArgument() {
        Converter.hexToDecimal(null);
    }

    /**
     * Test inputting a random nonsense string as the function argument.
     * Expected result: NumberFormatException.
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput1() {
        Converter.hexToDecimal("sadfljsghasjlghiuwn");
    }

    /**
     * Test inputting a binary string as the function argument.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput2() {
        Converter.hexToDecimal("0b1010101010101010101010101001");
    }

    /**
     * Test inputting a hex string that is too long as the function argument.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput3() {
        Converter.hexToDecimal("0xABFDE383EE7347ADE");
    }

    /**
     * Test inputting a hex string with invalid characters.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput4() {
        Converter.hexToDecimal("0xGGJHSDL");
    }

    /**
     * Test inputting a valid hex string that is *just* over the limit.
     * Expected result: ArithmeticException
     */
    @Test(expected = ArithmeticException.class)
    public void invalidInputTooLarge1() {
        Converter.hexToDecimal("0x80000000");
    }

    /**
     * Test inputting a valid hex string that is well over the limit.
     * Expected result: ArithmeticException
     */
    @Test(expected = ArithmeticException.class)
    public void invalidInputTooLarge2() {
        Converter.hexToDecimal("0xFFFFFFFF");
    }

    /**
     * Test inputting the hex representation of zero ("0x0").
     * Expected result: 0
     */
    @Test
    public void testZero() {
        assertEquals(0, Converter.hexToDecimal("0x0"));
    }

    /**
     * Test inputting the hex representation of the max integer value.
     * Expected result: 2147483647 (or Integer.MAX_VALUE)
     */
    @Test
    public void testMaxInt() {
        assertEquals(Integer.MAX_VALUE, Converter.hexToDecimal("0x7FFFFFFF"));
    }

    /**
     * Test inputting the binary representation of 1 ("0x1")
     * Expected result: 1
     */
    @Test
    public void testOne() {
        assertEquals(1, Converter.hexToDecimal("0x1"));
    }

    // Special thanks to https://www.rapidtables.com/convert/number/hex-to-decimal.html

    /**
     * Test inputting a valid, arbitrary hex sequence.
     * Expected result: 3249
     */
    @Test
    public void testArbitraryValue1() {
        assertEquals(3249, Converter.hexToDecimal("0xCB1"));
    }

    /**
     * Test inputting a second valid, arbitrary hex sequence.
     * Expected result: 978516
     */
    @Test
    public void testArbitraryValue2() {
        assertEquals(978516, Converter.hexToDecimal("0xEEE54"));
    }

    /**
     * Test inputting a third valid, arbitrary hex sequence.
     * Expected result: 24573296
     */
    @Test
    public void testArbitraryValue3() {
        assertEquals(24573296, Converter.hexToDecimal("0x176F570"));
    }

}