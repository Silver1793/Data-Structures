package project4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the Binary to Hex method.
 *
 * @author Harrison Douglass
 */
public class BinaryToHexTest {

	   /**
     * Test inputting null as the function argument.
     * Expected result: IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void nullArgument() {
        Converter.binaryToHex(null);
    }

    /**
     * Test inputting a nonsense string as the function argument.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput1() {
        Converter.binaryToHex("sadfljsghasjlghiuwn");
    }

    /**
     * Test inputting a hex string as the function argument.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput2() {
        Converter.binaryToHex("0xABFDE383");
    }

    /**
     * Test inputting a binary string that is too long as the function argument.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput3() {
        Converter.binaryToHex("0b10101010101010101010101010010101010101010101010010101010101010100101010101010101010010");
    }

    /**
     * Test inputting a nonsense string that starts with the 0b prefix as the function argument.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput4() {
        Converter.binaryToHex("0bGGJHSDLlsadf");
    }

    /**
     * Test inputting the binary representation of zero ("0b0").
     * Expected result: 0x0
     */
    @Test
    public void testZero() {
        assertEquals("0x0", Converter.binaryToHex("0b0"));
    }

    /**
     * Test inputting the binary representation of the max integer value.
     * Expected result: 0x7FFFFFFF
     */
    @Test
    public void testMaxInt() {
        assertEquals("0x7FFFFFFF", Converter.binaryToHex("0b1111111111111111111111111111111"));
    }

    /**
     * Test inputting the binary representation of 1 ("0b1")
     * Expected result: 0x1
     */
    @Test
    public void testOne() {
        assertEquals("0x1", Converter.binaryToHex("0b1"));
    }

    // All of the "arbitrary" cases have been chosen such that all hexadecimal characters show up at least once in one of the four cases.
    // Special thanks to https://www.rapidtables.com/convert/number/binary-to-hex.html

    /**
     * Test inputting a valid, arbitrary binary sequence.
     * Expected result: 0x929
     */
    @Test
    public void testArbitraryValue1() {
        assertEquals("0x929", Converter.binaryToHex("0b100100101001"));
    }

    /**
     * Test inputting a second valid, arbitrary binary sequence.
     * Expected result: 0xC1B43
     */
    @Test
    public void testArbitraryValue2() {
        assertEquals("0xC1B43", Converter.binaryToHex("0b11000001101101000011"));
    }

    /**
     * Test inputting a third valid, arbitrary binary sequence.
     * Expected result: 0x4E3247
     */
    @Test
    public void testArbitraryValue3() {
        assertEquals("0x4E3247", Converter.binaryToHex("0b10011100011001001000111"));
    }

    /**
     * Test inputting a fourth valid, arbitrary binary sequence.
     * Expected result: 0xA68D5
     */
    @Test
    public void testArbitraryValue4() {
        assertEquals("0xA68D5", Converter.binaryToHex("0b10100110100011010101"));
    }
}
