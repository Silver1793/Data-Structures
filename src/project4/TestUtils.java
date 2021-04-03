package project4;

/**
 * Utility functions for unit tests.
 *
 * NOTE: None of the code in this file can possibly be used as a solution for any
 * of the problems in Project 4. ALL of the code in this project uses loops, and
 * as a result, is INVALID for use in Project 4. I have engineered these functions
 * SPECIFICALLY to use loops as not to distribute any code that could be used to
 * reveal any solutions to problems found in Project 4.
 *
 * @author Harrison Douglass
 */
public class TestUtils {

    /**
     * Check if a given string is a valid binary string.
     * @param s The string to check.
     * @return True if the given string is a valid binary string, false otherwise.
     */
    public static boolean isValidBinary(String s) {
        // Cannot be longer than 33 characters (0b and 31 hex characters)
        if (s.length() > 33) return false;

        // String must start with "0b"
        if (!s.startsWith("0b")) return false;

        // Check each character from 2 to the end of the string. All must be 0 or 1.
        for (int i = 2; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(c == '0' || c == '1')) return false;
        }

        // If it passes all checks, return true.
        return true;
    }

    /**
     * Check if a given string is a valid hex string.
     * @param s The string to check.
     * @return True if the given string is a valid binary string, false otherwise.
     */
    public static boolean isValidHex(String s) {
        // Cannot be longer than 10 characters (0x and 8 hex characters)
        if (s.length() > 10) return false;

        // String must start with "0x"
        if (!s.startsWith("0x")) return false;

        // Check each character from 2 to the end of the string. All must be 0 <= char <=9 or A <= char <=F.
        for (int i = 2; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!(('0' <= c && c <= '9') || ('A' <= c && c <= 'F'))) return false;
        }

        // If it passes all checks, return true.
        return true;
    }

    /**
     * Remove all leading zeroes from a given binary or hex string.
     * @param s The hex or binary string from which to remove all leading zeroes.
     * @return A copy of the given hex or binary string without any leading zeroes.
     */
    public static String removeLeadingZeroes(String s) {
        if (isValidBinary(s) || isValidHex(s)) {
            // Iterate until non-zero character is found, then return snipped string.
            for (int i = 2; i < s.length(); i++) {
                if (s.charAt(i) != '0') return s.substring(0, 2) + s.substring(i);
            }
            // If all characters in a string are zero, return 0_0 (where _ is x or b)
            return s.substring(0, 2) + "0";
        } else throw new IllegalArgumentException("Remove leading zeroes did not receive a valid binary or hex string.");
        // (If not valid hex or binary, throw an exception)
    }

}