package project4;

/**
 * A class that converts binary strings to hexadecimal strings, and decimal ints. It also converts hexadecimal strings to binary string 
 * and decimal ints and converts decimal ints to binary strings and hexadecimal strings.
 * @author Richard
 *
 */
public class Converter {
	
	/**
	 * Changes a valid binary string into a integer of decimals that are equivalent. 
	 * @param binary is a binary string that starts with 0b 
	 * @throws NumberFormatExcpetion if the binary input has a character that is not a 0 or 1.
	 * @return The changeBinToDec method starting from the second character in the string if the character is a 0, or returns
	 * the changeBinToDec method starting from the second character adding 2 to the power of the length of the string if 
	 * the first character is a 1. 
	 * If the length of the input becomes 0, 0 is returned.
	 */
	private static int changeBinToDec(String binary) {
		if(binary.length() == 0) {
			return 0;
		}
		if((int)binary.charAt(0) >= 50)//This means that the input is either a lower case letter or a character that is G or higher which is not valid.
			throw new NumberFormatException("Input is not valid");
		else if(binary.substring(0,1).equals("1")) {
			return changeBinToDec(binary.substring(1)) + power(2, binary.length()-1);
		}
		else {
			return changeBinToDec(binary.substring(1));
		}
	}
	/**
	 * Changes a valid binary string into a hexadecimal string that are equivalent.
	 * @param binary is a binary string that starts with 0b  
	 * @return The changeBinToHex method with a 0 in front of the original input if the length of the input s not a factor of 4.
	 * Otherwise it returns the changeBinToHex method with the input without the last 4 characters and adds the result of 
	 * binaryConverter when the last 4 characters of the string are selected as an input. 
	 * If the length of the input becomes 0, 0x is returned.
	 */
	private static String changeBinToHex(String binary) {
		if(binary.length() == 0)
			return "0x";
		if(binary.length()%4 != 0)//Repeatedly adds 0s to the beginning of the string until the string length becomes a factor of 4
			return changeBinToHex("0"+binary);
			return changeBinToHex(binary.substring(0, binary.length()-4)) + binaryConverter(binary.substring(binary.length()-4));
	}
	/**
	 * Changes a valid decimal int into a binary string that are equivalent.
	 * @param decimal an int that will be converted into a corresponding binary string
	 * @return If decimal becomes 0, 0b is returned. Otherwise the method changeDecToBin is returned with decimal divided by 2
	 * and a string of the result of decimal module 2 is added.
	 */
	private static String changeDecToBin(int decimal) {
		if(decimal == 0)
			return "0b";
		else {
			return changeDecToBin(decimal/2)+"" + decimal%2;
		}
	}
	/**
	 * Changes a valid decimal int into a hexadecimal string that are equivalent.
	 * @param decimal an int that will be converted into a corresponding hexadecimal string
	 * @return 0x if decimal is 0. If decimal module 16 is less than 10, 
	 * the changeDecToHex method will be returned with decimal/16 as the input and decimal module 16 will be added to it.
	 * If decimal module 15 is between 10 and 16 inclusive, 
	 * the changeDecToHex method will be returned with decimal/16 as its input and the corresponding ASCII character of decimal module 16 + 55 will be added.
	 * Otherwise the method changeDecToHex will be returned with decimal divided by 16 as its input.
	 */
	private static String changeDecToHex(int decimal) {
		if(decimal == 0)
			return "0x";
		int n = decimal%16;
		if(n < 10) { //This case is if the character is a number
			return changeDecToHex(decimal/16) + n;
		}
		else if(n >=10 && n <= 16){ //This case is if the character is a letter
			return changeDecToHex(decimal/16) + ((char)(n - 10+65));
		}
		else {
			return changeDecToHex(decimal/16);
		}
	}
	/**
	 * Changes a valid hexadecimal string into a binary string that are equivalent.
	 * @param hex a hexadecimal string that will be converted into a corresponding binary string
	 * @throws NumberFormatException if a character other than the 16 valid ones for hexadecimals are inputed into the method.
	 * @throws IllegalArgumentException if a lower case letter is inputed into the method.
	 * @throws NumberFormatException if the input exceeds the greatest possible value for hexadecimals
	 * @return the changeHexToBin method with the input of hex variable without the last character and adds the result of hexConverter of the last character.
	 */
	private static String changeHexToBin(String hex) {
		if(hex.length() == 0)
			return "0b";
		if((int)(hex.charAt(0)) > 70)
			throw new NumberFormatException("Invalid inputs");
		if(Character.isLetter(hex.charAt(0)) && Character.isUpperCase(hex.charAt(0)) == false) //If a character is a letter, checks to see if the letter is lowercase or not
			throw new IllegalArgumentException("Input must have capital letter"); //Throws an exception if the character is lowercase
		if((int)(hex.charAt(0)) >= 55 && hex.length() > 8) {
			throw new NumberFormatException("Expression is greater than 7FFFFFFF");
		}
		else {
			return changeHexToBin(hex.substring(0,hex.length()-1)) + hexConverter(hex.substring(hex.length()-1));
		}
		
	}
	/**
	 * Changes a valid hexadecimal string into a decimal int that are equivalent.
	 * @param hex a hexadecimal string that will be converted into a corresponding decimal int
	 * @throws NumberFormatException if the length of the input is greater than 8.
	 * @throws NumberFormatException If a character in the input is a lowercase.
	 * @throws ArithemticException If the conversion of the input is greater than 7FFFFFFF
	 * @throws NumberFormatException If a character in the input is an invalid hexadecimal character
	 * @return 0 if the length of hex reaches 0. If the character of the hex is a letter, 
	 * The method will return the changeHexToDec method starting at the second character and add the integer value of the character - 55 times by 16 raised to the length of the input.
	 * Otherwise the method will return the changeHexToDec method starting at the second character and add the integer value of the character - 48 times by 16 raised to the length of the input.
	 */
	private static int changeHexToDec(String hex) {
		if(hex.length() == 0) {
			return 0;
		}
		if(hex.length() > 8) {
			throw new NumberFormatException("Length is too long");
		}
		if(Character.isLetter(hex.charAt(0)) && Character.isUpperCase(hex.charAt(0)) == false)
			throw new NumberFormatException("Input must have capital letter");
		if((int)(hex.charAt(0)) >= 56 && hex.length() >= 8) {
			throw new ArithmeticException("Expression is greater than 7FFFFFFF");
		}
		if((int)(hex.charAt(0)) > 70)
			throw new NumberFormatException("Invalid input");
		if(hex.substring(0,1).equals("0")) {
			return changeHexToDec(hex.substring(1));
		}
		else if(Character.isLetter(hex.charAt(0))){
			return changeHexToDec(hex.substring(1))+(int)(hex.charAt(0)-55)*power(16,hex.length()-1);
		}
		else
			return changeHexToDec(hex.substring(1))+(int)(hex.charAt(0)-48)*power(16,hex.length()-1);
	}
	/**
	 * A method that keeps track of each case and returns the hexadecimal based on the input.
	 * @param binary a binary string that contains 4 characters of 1 and 0. 
	 * @throws NumberFormatException If none of the cases were valid.
	 * @return a hexadecimal string based on the 4 character string that was taken in as the input
	 */
	private static String binaryConverter(String binary) {
		switch(binary)
		{
		case "0000":
			return "0";
		case "0001":
			return "1";
		case "0010":
			return "2";
		case "0011":
			return "3";
		case "0100":
			return "4";
		case "0101":
			return "5";
		case "0110":
			return "6";
		case "0111":
			return "7";
		case "1000":
			return "8";
		case "1001":
			return "9";
		case "1010":
			return "A";
		case "1011":
			return "B";
		case "1100":
			return "C";
		case "1101":
			return "D";
		case "1110":
			return "E";
		case "1111":
			return "F";
		default: throw new NumberFormatException("Input is not valid"); //If the input does not pass any of the cases, an exception will b e thrown.
		}
			
	}
	/**
	 * Takes in an input and will return the corresponding binary value for the input.
	 * @param input a string that is part of the input from the hexToBinary method.
	 * @return If the integer value - 48 of the input is 0, the method will return 0.
	 * Otherwise it will either add 1 or 0 to an empty string depending on if the input is divisible by 10, 8, 4, 2 and 1.
	 * The method will then return the string.
	 */
	private static String hexConverter(String input) {
		int n = (int)(input.charAt(0)-48); //Converts the string into its corresponding integer value.
		String add = "";
		if(n == 0)
			return "0000";
		if(n >10) //If the original string is a character, it will then be subtracted by an additional 7 after being converted to an int.
			n = n-7;
		if(n/8 > 0)
		{
			add += "1";
			n -= 8;
		}
		else {
			add+="0";
		}
		if(n/4 > 0)
		{
			add += "1";
			n -= 4;
		}
		else {
			add+="0";
		}
		if(n/2 > 0)
		{
			add += "1";
			n -= 2;
		}
		else {
			add+="0";
		}
		if(n/1 > 0)
		{
			add += "1";
			n -= 1;
		}
		else {
			add+="0";
		}
		return add;
	}
	/**
	 * A method that will raise a base to a certain exponent and return that value.
	 * @param base an int that will be the base for the power method
	 * @param exp an int variable that determines how many times the base value will be multiplied
	 * @return the power method with the same base but with the exp variable -1 and will also multiply this method by the base.
	 */
	private static int power(int base, int exp) {
		if(exp == 0)
			return 1;
		else {
			return power(base , exp-1)*base; //Keeps multiplying the base by itself repeatedly until the exponent reaches 0
		}
	}
	/**
	 * The wrapper function the the changeBinToDec method
	 * @param binary a binary string that will be checked for exceptions and be put into the changeBinToDec method
	 * @throws IllegalArgumentException if the input is null. Will print out an error message that says "Input cannot be null".
	 * @throws NumberFormatException if the first two characters in the string is not 0b or if the length is equal to or less than 2. Will print out an error message that says "Binary input is invalid".
	 * @throws NumberFormatException if the length of the binary string is greater than 33. Will print out an error message that says "Length of input is too long".
	 * @return the changeBinToDec method with the binary string as the input without the first two characters.
	 */
	public static int binaryToDecimal(String binary) {
		if(binary == null)
			throw new IllegalArgumentException("Input cannot be null");
		if(binary.length() <= 2 || !binary.substring(0,2).equals("0b")){
			throw new NumberFormatException("Binary input is invalid");
		}
		if(binary.length() > 33)
			throw new NumberFormatException("Length of input too long");
		return changeBinToDec(binary.substring(2)); //Removes the 0b from the String binary.
	}
	/**
	 * The wrapper function the the changeBinToHex method
	 * @param binary a binary string that will be checked for exceptions and be put into the changeBinToHex method 
	 * @throws IllegalArgumentException if the input is null. Will print out an error message that says "Input cannot be null".
	 * @throws NumberFormatException if the first two characters in the string is not 0b or if the length is equal to or less than 2. Will print out an error message that says "Binary input is invalid".
	 * @throws NumberFormatException if the length of the binary string is greater than 33. Will print out an error message that says "Length of input is too long".
	 * @return the changeBinToHex method with the binary string as the input without the first two characters.
	 */
	public static String binaryToHex(String binary) {
		if(binary == null)
			throw new IllegalArgumentException("Input cannot be null");
		if(binary.length() <= 2 || !binary.substring(0,2).equals("0b")) {
			throw new NumberFormatException("Binary input is invalid");
		}
		if(binary.length() > 33)
			throw new NumberFormatException("Length of input too long"); 
		return changeBinToHex(binary.substring(2)); //Removes the 0b from the String binary.
	}
	/**
	 * The wrapper function the the changeDecToBin method
	 * @param decimal a decimal int that will be checked for exceptions and be put into the changeDecToBin method
	 * @return null if the input is a negative or 0b0 if the decimal is 0. Otherwise will return the changeDecToBin method with decimal as the input.
	 */
	public static String decimalToBinary(int decimal) {
		if(decimal < 0)
			return null;
		if(decimal == 0)
			return "0b0";
		return changeDecToBin(decimal);
		}
	/**
	 * The wrapper function the the changeDecToHex method
	 * @param decimal a decimal int that will be checked for exceptions and be put into the changeDecToHex method
	 * @return null if the input is a negative or 0x0 if the decimal is 0. Otherwise will return the changeDecToHex method with decimal as the input.
	 */
	public static String decimalToHex(int decimal) {
		if(decimal < 0)
			return null;
		if(decimal == 0)
			return "0x0";
		return changeDecToHex(decimal);
	}
	/**
	 * The wrapper function the the changeHexToBin method
	 * @param hex a hexadecimal string that will be checked for exceptions and be put into the changeHexToBin method
	 * @throws IllegalArgumentException if the input is null.
	 * @throws NumberFormatException if the first two characters do not equal 0x.
	 * @return 0b0000 if the input is equal to 0x0. Otherwise will return the changeHexToBin method with the hex string as the input without the first two characters.
	 */
	public static String hexToBinary(String hex) {
		if(hex == null)
			throw new IllegalArgumentException("Input cannot be null");
		if(hex.length() <= 2)//Checks to see if the string is only 0x or doesn't have 0x at all. In both cases an exception will be thrown.
			throw new NumberFormatException("Input length cannot be less than or equal to 2");
		if(!hex.substring(0,2).equals("0x")) {
			throw new NumberFormatException("Input for hex is invalid");
		}
		if(hex.equals("0x0"))
			return "0b0000";
		return changeHexToBin(hex.substring(2)); //Removes the 0x from the String hex.
	}
	/**
	 * The wrapper function the the changeHexToDecimal method
	 * @param hex a hexadecimal string that will be checked for exceptions and be put into the changeHexToDecimal method.
	 * @throws IllegalArgumentException if the input is null and will print out the statement "Input cannot be null".
	 * @throws NumberFormatException if the length of the input is less than 2 and will print out the statement "Input length cannot be less than 2".
	 * @throws NumberFormatException if the first two characters of the hex string are not equals to 0x.
	 * @return Will return the changeHexToDec method with the hex string as the input without the first two characters.
	 */
	public static int hexToDecimal(String hex) {
		if(hex == null)
			throw new IllegalArgumentException("Input cannot be null");
		if(hex.length() <= 2)
			throw new NumberFormatException("Input length cannot be less than or equal to 2");
		if(!hex.substring(0,2).equals("0x")) {
			throw new NumberFormatException("Input for hex is invalid");
		}
		return changeHexToDec(hex.substring(2)); //Removes the 0x from the String hex.
	}
	}
