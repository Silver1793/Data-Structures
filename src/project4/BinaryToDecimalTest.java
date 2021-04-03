package project4;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test cases for the Binary to Decimal method.
 *
 * @author Harrison Douglass
 */
public class BinaryToDecimalTest {
	/**
     * Test inputting null as the function argument.
     * Expected result: IllegalArgumentException
     */
    @Test(expected = IllegalArgumentException.class)
    public void nullArgument() {
        Converter.binaryToDecimal(null);
    }

    /**
     * Test inputting a random nonsense string as the function argument.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput1() {
        Converter.binaryToDecimal("sadfljsghasjlghiuwn");
    }

    /**
     * Test inputting a hex string as the function argument.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput2() {
        Converter.binaryToDecimal("0xABFDE383");
    }

    /**
     * Test inputting a binary string that is too long as the function argument.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput3() {
        Converter.binaryToDecimal("0b10101010101010101010101010010101010101010101010010101010101010100101010101010101010010");
    }

    /**
     * Test inputting a binary string that is *just barely* too long as the function argument.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput4() {
        Converter.binaryToDecimal("0b10101010101010101010101010010101");
    }

    /**
     * Test inputting a nonsense string that starts with the 0b prefix as the function argument.
     * Expected result: NumberFormatException
     */
    @Test(expected = NumberFormatException.class)
    public void invalidInput5() {
        Converter.binaryToDecimal("0bGGJHSDLlsadf");
    }

    /**
     * Test inputting the binary representation of zero ("0b0").
     * Expected result: 0
     */
    @Test
    public void testZero() {
        assertEquals(0, Converter.binaryToDecimal("0b0"));
    }

    /**
     * Test inputting the binary representation of the max integer value.
     * Expected result: 2147483647 (or Integer.MAX_VALUE)
     */
    @Test
    public void testMaxInt() {
        assertEquals(Integer.MAX_VALUE, Converter.binaryToDecimal("0b1111111111111111111111111111111"));
    }

    /**
     * Test inputting the binary representation of 1 ("0b1")
     * Expected result: 1
     */
    @Test
    public void testOne() {
        assertEquals(1, Converter.binaryToDecimal("0b1"));
    }

    // Special thanks to https://www.rapidtables.com/convert/number/binary-to-decimal.html

    /**
     * Test inputting a valid, arbitrary binary sequence.
     * Expected result: 2345
     */
    @Test
    public void testArbitraryValue1() {
        assertEquals(2345, Converter.binaryToDecimal("0b100100101001"));
    }

    /**
     * Test inputting a second valid, arbitrary binary sequence.
     * Expected result: 793411
     */
    @Test
    public void testArbitraryValue2() {
        assertEquals(793411, Converter.binaryToDecimal("0b11000001101101000011"));
    }

    /**
     * Test inputting a third valid, arbitrary binary sequence.
     * Expected result: 5124679
     */
    @Test
    public void testArbitraryValue3() {
        assertEquals(5124679, Converter.binaryToDecimal("0b10011100011001001000111"));
    }
}
