package project4;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test cases for the Hex to Binary method.
 *
 * @author Harrison Douglass
 */
public class HexToBinaryTest {

    /**
     * Test inputting null as the function argument.
     * Expected result: IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void nullArgument() {
        Converter.hexToBinary(null);
    }

    /**
     * Test inputting a random nonsense string as the function argument.
     * Expected result: NumberFormatException.
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput1() {
        Converter.hexToBinary("sadfljsghasjlghiuwn");
    }

    /**
     * Test inputting a binary string as the function argument.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput2() {
        Converter.hexToBinary("0b1010101010101010101010101001");
    }

    /**
     * Test inputting a hex string that is too long as the function argument.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput3() {
        Converter.hexToBinary("0xABFDE383EE7347ADE");
    }

    /**
     * Test inputting a hex string with invalid characters.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput4() {
        Converter.hexToBinary("0xGGJHSDL");
    }

    // All of the following cases assume that the binary is padded with no more than three zeroes.

    /**
     * Test inputting the hex representation of zero ("0x0").
     * Expected result: 0b0000
     */
    @Test
    public void testZero() {
        assertEquals("0b0", TestUtils.removeLeadingZeroes(Converter.hexToBinary("0x0")));
    }

    /**
     * Test inputting the hex representation of the max integer value.
     * Expected result: 0b1111111111111111111111111111111
     */
    @Test
    public void testMaxInt() {
        assertEquals("0b1111111111111111111111111111111", TestUtils.removeLeadingZeroes(Converter.hexToBinary("0x7FFFFFFF")));
    }

    /**
     * Test inputting the binary representation of 1 ("0x1")
     * Expected result: 0b1
     */
    @Test
    public void testOne() {
        assertEquals("0b1", TestUtils.removeLeadingZeroes(Converter.hexToBinary("0x1")));
    }

    // These "arbitrary" values have been chosen such that all sequences of four binary characters will appear in at least one of the four tests.
    // Special thanks to https://www.rapidtables.com/convert/number/hex-to-binary.html

    /**
     * Test inputting a valid, arbitrary hex sequence.
     * Expected result: 0b110010110001
     */
    @Test
    public void testArbitraryValue1() {
        assertEquals("0b110010110001", TestUtils.removeLeadingZeroes(Converter.hexToBinary("0xCB1")));
    }

    /**
     * Test inputting a second valid, arbitrary hex sequence.
     * Expected result: 0b11101110111001010100
     */
    @Test
    public void testArbitraryValue2() {
        assertEquals("0b11101110111001010100", TestUtils.removeLeadingZeroes(Converter.hexToBinary("0xEEE54")));
    }

    /**
     * Test inputting a third valid, arbitrary hex sequence.
     * Expected result: 0b1011101101111010101110000
     */
    @Test
    public void testArbitraryValue3() {
        assertEquals("0b1011101101111010101110000", TestUtils.removeLeadingZeroes(Converter.hexToBinary("0x176F570")));
    }

    /**
     * Test inputting a third valid, arbitrary hex sequence.
     * Expected result: 0b101010010011110110000010
     */
    @Test
    public void testArbitraryValue4() {
        assertEquals("0b101010010011110110000010", TestUtils.removeLeadingZeroes(Converter.hexToBinary("0xA93D82")));
    }

}