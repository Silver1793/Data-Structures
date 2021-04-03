package project4;

import static org.junit.Assert.*;
import org.junit.Test;

/*

To compile this code execute:  

javac -cp .:/usr/share/java/junit-4.12.jar project4/ConverterTest.java  

To run this code execute: 

java -cp .:/usr/share/java/junit-4.12.jar:/usr/share/java/hamcrest-core.jar org.junit.runner.JUnitCore project4.ConverterTest  

*/

public class ConverterTest {
	Converter Converter = new Converter();

    //Add tests for your own method here. 

    //Work with your group and your section leader to make sure you are a set of complete tests and 
    // that you thought of all possibilities and edge cases. 
    //Group members: Jonason Wu, Ali Boukind, Pragya Parthasarathy

    /*
    IllegalArgumentException - if the parameter is null
    NumberFormatException - if the hexadecimal string passed to the function is invalid
    ArithmeticException - when the hexadecimal string parameter is greater than 0x7FFFFFFF (the largest value that can be represented as an int)
    */

    @Test
    public void hexToDecimal_IllegalArgumentException(){
        try {
            String hex = null;
            Converter.hexToDecimal(hex);
            fail("No exception thrown for null parameter");         
        }
        catch (IllegalArgumentException ex) {
            //passes
        }
        catch (Exception ex){
            fail("Unknown exception thrown.");
        }
    }


    @Test
    public void hexToDecimal_NumberFormatException (){
        try{
            String value2 = "#234j234"; // cant start w #
            Converter.hexToDecimal(value2);
            fail("can't start with #.");
            
        } catch (NumberFormatException e) {
            // pass 
        } catch (Exception e) {
            fail("invalid exception thrown.");
        }
    }

    @Test
    public void hexToDecimal_NumberFormatException2 (){
        try{
            //String value2 = "#234j234765jghgj8768"; // too long
            String value2 = "0x234234765"; // too long
            Converter.hexToDecimal(value2);
            fail("too long.");
            
        } catch (NumberFormatException e) {
            // pass 
        } catch (Exception e) {
            fail("invalid exception thrown.");
        }
    }


    
    @Test
    public void hexToDecimal_ArithmeticException1(){
        try{
            String hex2 = "0xFFFFFFFF"; 
            Converter.hexToDecimal(hex2);
            fail("No exception thrown for paramater greater than 0x7FFFFFFF");
        }
        catch (ArithmeticException ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Invalid exception thrown.");
        } 
    }

    @Test
    public void hexToDecimal_ArithmeticException(){
        try{
            String hex1 = "0x80000000";
            Converter.hexToDecimal(hex1);
            fail("No exception thrown for paramater greater than 0x7FFFFFFF");
        }
        catch (ArithmeticException ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Invalid exception thrown.");
        }
        
    }

    @Test
    public void hexToDecimal_normalCase(){
        try{
            String hex = "0x000000"; 
            int tmp;
            tmp = Converter.hexToDecimal(hex);
            assertEquals("The hex is unsuccessfully converted to decimal", 0, tmp);
        }
        catch (Exception ex){
            fail("This should have passed");
        }

    }

    @Test
    public void hexToDecimal_normalCase1(){
        try{
            String hex = "0x000001";
            int tmp;
            tmp = Converter.hexToDecimal(hex);
            assertEquals("The hex is incorrectly converted to decimal", 1, tmp);
        }
        catch (Exception ex){
            fail("This should have passed");
        }

    }

    @Test
    public void hexToDecimal_normalCase2(){
        try{
            String hex = "0x7FFFFFFF";
            int tmp;
            tmp = Converter.hexToDecimal(hex);
            assertEquals("The hex is incorrectly converted to decimal", 2147483647, tmp);
        }
        catch (Exception ex){
            fail("This should have passed");
        }

    }
    @Test
    public void hexToDecimal_normalCase3(){
        try{
            String hex = "0x1";
            int tmp;
            tmp = Converter.hexToDecimal(hex);
            assertEquals("The hex is incorrectly converted to decimal", 1, tmp);
        }
        catch (Exception ex){
            fail("This should have passed");
        }

    }

    @Test
    public void hexToDecimal_normalCase4(){
        try{
            String hex = "0x11";
            int tmp;
            tmp = Converter.hexToDecimal(hex);
            assertEquals("The hex is incorrectly converted to decimal", 17, tmp);
        }
        catch (Exception ex){
            fail("This should have passed");
        }

    }

    @Test
    public void hexToDecimal_normalCase5(){
        try{
            String hex = "0x0";
            int tmp;
            tmp = Converter.hexToDecimal(hex);
            assertEquals("The hex is incorrectly converted to decimal", 0, tmp);
        }
        catch (Exception ex){
            fail("This should have passed");
        }

    }
    @Test
    public void hexToDecimal_normalCase6(){
        try{
            String hex = "0x1F";
            int tmp;
            tmp = Converter.hexToDecimal(hex);
            assertEquals("The hex is incorrectly converted to decimal", 31, tmp);
        }
        catch (Exception ex){
            fail("This should have passed");
        }

    }
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
  //Add tests for your own method here. 
    
    //Work with your group and your section leader to make sure you are a set of complete tests and 
    // that you thought of all possibilities and edge cases. 


    //done
    /*
     * Test should pass if hexToDecimal method is called with a null
     * pointer and the method returns an IllegalArgumentException
     */
    @Test
    public void hexToDecimal_IllegalArgumentException_nullInput() {
        try{
            int converted = Converter.hexToDecimal(null);
            fail("No exception thrown for null argument.");
        }
        catch (IllegalArgumentException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }


    //done
    /*
     * Test should pass if hexToDecimal method is called with a blank
     * string and the method returns an NumberFormatException
     */
    @Test
    public void hexToDecimal_NumberFormatException_emptyStr() {
        try{
            int converted = Converter.hexToDecimal("");
            fail("No exception thrown for empty argument.");
        }
        catch (NumberFormatException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }


    //done
    /*
     * Test should pass if hexToDecimal method is called with a blank
     * string containing only whitespace and the method returns an NumberFormatException
     */
    @Test
    public void hexToDecimal_NumberFormatException_whiteSpace() {
        try{
            int converted = Converter.hexToDecimal("  ");
            fail("No exception thrown for empty spaces as argument.");
        }
        catch (NumberFormatException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }
    
    
    //done
    /*
     * Test should pass if hexToDecimal method is called with
     * string containing special characters and the method returns an NumberFormatException
     */
    @Test
    public void hexToDecimal_NumberFormatException_nonsenseCharacters() {
        try{
            int converted = Converter.hexToDecimal("!|?#@");
            fail("No exception thrown for special characters as argument.");
        }
        catch (NumberFormatException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    
    //done
    /*
     * Test should pass if hexToDecimal method is called with a hex
     * string that is LARGER than 0x7FFFFFFF and the method returns 
     * an NumberFormatException. String passed is 0x80000000 which is
     * one bit larger than 0x7FFFFFFF.
     */
    @Test
    public void hexToDecimal_ArithmeticException_Invalid_large_input_ArithmeticException() {
        try{
            int converted = Converter.hexToDecimal("0x80000000");
            fail("No ArithmeticException thrown for string hex " +
                    "value of over 0x7FFFFFFF. Given string: '0x80000000'");
        }
        catch (ArithmeticException ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }
    
    //done
    /*
     * Test should pass if hexToDecimal method is called with a hex
     * string that is LARGER than 0x7FFFFFFF and the method returns 
     * an NumberFormatException. String passed is 0x80000001 which is
     * one bit larger than 0x7FFFFFFF.
     */
    @Test
    public void hexToDecimal_ArithmeticException_Invalid_large_input_ArithmeticException2() {
        try{
            int converted = Converter.hexToDecimal("0x80000000");
            fail("No ArithmeticException thrown for string hex " +
                    "value of over 0x7FFFFFFF. Given string: '0x80000001'");
        }
        catch (ArithmeticException ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    //done
    /*
     * Test should pass if hexToDecimal method is called with a hex
     * string that is LARGER than 0x7FFFFFFF and the method returns 
     * an NumberFormatException. String passed is 0x90000000 which is
     * one bit larger than 0x7FFFFFFF.
     */
    @Test
    public void hexToDecimal_ArithmeticException_Invalid_large_input_ArithmeticException3() {
        try{
            int converted = Converter.hexToDecimal("0x80000000");
            fail("No ArithmeticException thrown for string hex " +
                    "value of over 0x7FFFFFFF. Given string: '0x90000000'");
        }
        catch (ArithmeticException ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    //done
    /*
     * Test should pass if hexToDecimal method is called with a hex
     * string that is LARGER than 0x7FFFFFFF and the method returns 
     * an NumberFormatException. String passed is 0x90000001 which is
     * one bit larger than 0x7FFFFFFF.
     */
    @Test
    public void hexToDecimal_ArithmeticException_Invalid_large_input_ArithmeticException4() {
        try{
            int converted = Converter.hexToDecimal("0x80000000");
            fail("No ArithmeticException thrown for string hex " +
                    "value of over 0x7FFFFFFF. Given string: '0x90000001'");
        }
        catch (ArithmeticException ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    //done
    /*
     * Test should pass if hexToDecimal method is called with a hex
     * string that is EQUAL than 0x7FFFFFFF and the method returns 
     * Integer.MAX_VALUE.
     * NO EXCEPTION SHOULD BE THROWN
     */
    @Test
    public void hexToDecimal_VALID_large_input() {
        try{
            int converted = Converter.hexToDecimal("0x7FFFFFFF");
            //System.out.println(converted); possibly not needed just to test this case?
            if (converted != Integer.MAX_VALUE){
                fail("Expected output should be Integer.MAX_VALUE");
            }
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }
    

    //done
    /*
     * assuming that the input is valid.
     * test should pass if the method returns the correct value based on given tests  
     */ 
    @Test 
    public void hexToDecimal_validInput(){
        try{
            int correct = 1;
            int test = Converter.hexToDecimal("0x00000001");
            //checks for equality
            assertEquals("Correct result should be 1 but returned " + Integer.toString(test), correct, test); //message is in case it is invalid
            //desired behavior, test passed            
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test 
    public void hexToDecimal_validInput1(){
        try{
            int correct = 11259375;
            int test = Converter.hexToDecimal("0xABCDEF");
            //checks for equality
            assertEquals("Correct result should be 11259375 but returned " + Integer.toString(test), correct, test); //message is in case it is invalid
            //desired behavior, test passed            
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }
    
    @Test 
    public void hexToDecimal_validInput2(){
        try{
            int correct = 19088743 ;
            int test = Converter.hexToDecimal("0x1234567");
            //checks for equality
            assertEquals("Correct result should be 19088743  but returned " + Integer.toString(test), correct, test); //message is in case it is invalid
            //desired behavior, test passed            
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }
    
    @Test 
    public void hexToDecimal_validInput3(){
        try{
            int correct = 9043627  ;
            int test = Converter.hexToDecimal("0x89FEAB");
            //checks for equality
            assertEquals("Correct result should be 9043627   but returned " + Integer.toString(test), correct, test); //message is in case it is invalid
            //desired behavior, test passed            
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }
        
    



    //Checking other INVALID cases that throws an exception
    //first check for set of cases where there is an invalid LOWER CASE character a
    @Test
    public void hexToDecimal_CheckCase1_Invalid() {
        try{
            int converted = Converter.hexToDecimal("0xAAAAAAa");
            fail("No exception thrown for invalid LOWER CASE character a at the end of the string 0xAAAAAAAa.");
        }
        catch (NumberFormatException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    public void hexToDecimal_CheckCase2_Invalid() {
        try{
            int converted = Converter.hexToDecimal("0xAAAAaAA");
            fail("No exception thrown for invalid LOWER CASE character a in the middle of the string 0xAAAAAaAA.");
        }
        catch (NumberFormatException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }
    
     

    @Test
    public void hexToDecimal_CheckCase3_Invalid() {
        try{
            int converted = Converter.hexToDecimal("0xaAAAAAA ");
            fail("No exception thrown for invalid LOWER CASE character a at the beginning of the string 0xaAAAAAAA.");
        }
        catch (NumberFormatException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    public void hexToDecimal_CheckCase4_Invalid() {
        try{
            int converted = Converter.hexToDecimal("0xaaaaaaa ");
            fail("No exception thrown for invalid LOWER CASE character a for the whole of the string 0xaaaaaaa.");
        }
        catch (NumberFormatException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    public void hexToDecimal_CheckCase5_VALID() {
        try{
            int correct = 178956970;
            int test = Converter.hexToDecimal("0xAAAAAAA");
            //checks for equality
            assertEquals("Correct result should be 178956970  but returned " + Integer.toString(test), correct, test); //message is in case it is invalid
            //desired behavior, test passed            
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }



    //then check for set of cases where there is an invalid UPPER CASE character G
     @Test
    public void hexToDecimal_UpperCheckCase1_Invalid() {
        try{
            int converted = Converter.hexToDecimal("0xAAAAAAG");
            fail("No exception thrown for invalid UPPER CASE character G at the end of the string 0xAAAAAAG.");
        }
        catch (NumberFormatException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    public void hexToDecimal_UpperCheckCase2_Invalid() {
        try{
            int converted = Converter.hexToDecimal("0xAAAAGAA");
            fail("No exception thrown for invalid UPPER CASE character G in the middle of the string 0xAAAAGAA.");
        }
        catch (NumberFormatException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }
    
     

    @Test
    public void hexToDecimal_UpperCheckCase3_Invalid() {
        try{
            int converted = Converter.hexToDecimal("0xAAGGGAA");
            fail("No exception thrown for invalid UPPER CASE character G at the beginning of the string 0xAAGGGAA .");
        }
        catch (NumberFormatException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    public void hexToDecimal_UpperCheckCase4_Invalid() {
        try{
            int converted = Converter.hexToDecimal("0xGGGGGGG");
            fail("No exception thrown for invalid UPPER CASE character G for the whole of the string 0xGGGGGGG.");
        }
        catch (NumberFormatException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    public void hexToDecimal_UpperCheckCase5_VALID() {
        try{
            int correct = 19114957;
            int test = Converter.hexToDecimal("0x0123ABCD");
            //checks for equality
            assertEquals("Correct result should be 19114957 but returned " + Integer.toString(test), correct, test); //message is in case it is invalid
            //desired behavior, test passed            
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }


    //then check for set of cases where there is an invalid and valid FORMAT to the input
    @Test
    public void hexToDecimal_FormatCheck_invalidStart() {
        try{
            int converted = Converter.hexToDecimal("0123ABCD");
            fail("No exception thrown for input without a \"0x\" start as argument.");
        }
        catch (NumberFormatException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test
    public void hexToDecimal_FormatCheck_ArgTooLong() {
        try{
            int converted = Converter.hexToDecimal("0x0123ABCDD");
            fail("No exception thrown for input longer than 10 characters as argument.");
        }
        catch (NumberFormatException  ex) {
            //desired behavior, test passed
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }


    @Test 
    public void hexToDecimal_FormatCheck_Valid1(){
        try{
            int correct = 82143113;
            int test = Converter.hexToDecimal("0x4E56789");
            //checks for equality
            assertEquals("Correct result should be 82143113 but returned " + Integer.toString(test), correct, test); //message is in case it is invalid
            //desired behavior, test passed            
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    //the following are also formating checks but 
    //they are valid strings shorter than 10 characters
    @Test 
    public void hexToDecimal_FormatCheck_Valid2(){
        try{
            int correct = 10;
            int test = Converter.hexToDecimal("0xA");
            //checks for equality
            assertEquals("Correct result should be 82143113 but returned " + Integer.toString(test), correct, test); //message is in case it is invalid
            //desired behavior, test passed            
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    @Test 
    public void hexToDecimal_FormatCheck_Valid3(){
        try{
            int correct = 7;
            int test = Converter.hexToDecimal("0x7");
            //checks for equality
            assertEquals("Correct result should be 7 but returned " + Integer.toString(test), correct, test); //message is in case it is invalid
            //desired behavior, test passed            
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    //this is an interesting pass case because it is 8 which
    //represents 00000008 and not 80000000
    //hence it should pass
    @Test 
    public void hexToDecimal_FormatCheck_Valid4(){
        try{
            int correct = 8;
            int test = Converter.hexToDecimal("0x8");
            //checks for equality
            assertEquals("Correct result should be 8 but returned " + Integer.toString(test), correct, test); //message is in case it is invalid
            //desired behavior, test passed            
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    //this is an interesting pass case because 
    //of the same reason as the previos test
    //this is 00000009 and not 90000000 so should pass
    @Test 
    public void hexToDecimal_FormatCheck_Valid5(){
        try{
            int correct = 9;
            int test = Converter.hexToDecimal("0x9");
            //checks for equality
            assertEquals("Correct result should be 9 but returned " + Integer.toString(test), correct, test); //message is in case it is invalid
            //desired behavior, test passed            
        }
        catch (Exception ex) {
            fail("Unexpected exception thrown.");
        }
    }

    
}