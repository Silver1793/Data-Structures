package project4;

public class ConverterTester {

	public static void main(String[] args) {
		Converter convert = new Converter();
		System.out.println("Binary to Hex Tests");
		System.out.println(convert.binaryToHex("0b0"));
		System.out.println("The print above should be 0x0");
		System.out.println(convert.binaryToHex("0b001"));
		System.out.println("The print above should be 0x1"); 
		System.out.println(convert.binaryToHex("0b11"));
		System.out.println("The print above should be 0x3"); 
		System.out.println(convert.binaryToHex("0b11001101"));
		System.out.println("The print statement above should be 0xCD");
		//System.out.println(convert.binaryToHex("0b101010111100110111101111000100100011010001010110011110001001"));
		//System.out.println("The print statemnet above should be 0xABCDEF123456789");
		try {
			System.out.println(convert.binaryToHex(null));
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("The statement above should print out an error with a corresponding statement");
		try {
			System.out.println(convert.binaryToHex("100"));
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("The statement above should print out an error with a corresponding statement");
		try {
			System.out.println(convert.binaryToHex(""));
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("The statement above should print out an error with a corresponding statement");
		try {
			System.out.println(convert.binaryToHex("0b"));
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("The statement above should print out an error with a corresponding statement");
		
		System.out.println("Binary to Decimal tests");
		System.out.println(convert.binaryToDecimal("0b0"));
		System.out.println("The statement above should print out 0");
		System.out.println(convert.binaryToDecimal("0b1"));
		System.out.println("The statement above should print out 1");
		System.out.println(convert.binaryToDecimal("0b10"));
		System.out.println("The statement above should print out 2");
		System.out.println(convert.binaryToDecimal("0b101001"));
		System.out.println("The statement above should print out 41");
		try {
			System.out.println(convert.binaryToDecimal(null));
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("The statement above should print out an error");
		try {
			System.out.println(convert.binaryToDecimal("0b"));
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("The statement above should print out an error");
		try {
			System.out.println(convert.binaryToDecimal(""));
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("The statement above should print out an error");
		try {
			System.out.println(convert.binaryToDecimal("100"));
		} catch (Exception e) {
			System.out.println(e);
		} 
		System.out.println("The statement above should print out an error");
		
		
		System.out.println("Decimal to Binary test");
		System.out.println(convert.decimalToBinary(10));
		System.out.println("The statement above should print 0b1010");
		System.out.println(convert.decimalToBinary(0));
		System.out.println("The statement above should print 0b0");
		System.out.println(convert.decimalToBinary(1000));
		System.out.println("The statement above should print 0b1111101000");
		System.out.println(convert.decimalToBinary(-1));
		System.out.println("The statement above should print null");
		
		System.out.println("Decimal to hex tests");
		System.out.println(convert.decimalToHex(100));
		System.out.println("The statement above should print 0x64");
		System.out.println(convert.decimalToHex(0));
		System.out.println("The statement above should print 0x0");
		System.out.println(convert.decimalToHex(1000));
		System.out.println("The statement above should print 0x3E8");
		System.out.println(convert.decimalToHex(201));
		System.out.println("The statement above should print 0xC9");
		System.out.println(convert.decimalToHex(2010));
		System.out.println("The statement above should print 0x7DA");
		System.out.println(convert.decimalToHex(9000));
		System.out.println("The statement above should print 0x2328");
		System.out.println(convert.decimalToHex(-1));
		System.out.println("The statement above should print null");
		
		System.out.println("Hex to Binary tests");
		System.out.println(convert.hexToBinary("0x0"));
		System.out.println("The statement above should print 0b0");
		
		System.out.println(convert.hexToBinary("0x0"));
		
		try {
			System.out.println(convert.binaryToDecimal("0b"));
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("The statement above should print out an error");
		try {
			System.out.println(convert.binaryToHex("0b"));
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println("The statement above should print out an error");
		System.out.println(convert.hexToBinary("0x"));
}
}
