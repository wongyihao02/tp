package util;
/**
 * Utility class for checking whether a given string is a valid integer.
 * This class contains a method to verify if a string can be converted into an integer.
 */
public class IntegerChecker {
    /**
     * Checks if the provided input is a valid integer.
     *
     * @param input The string to check.
     * @return true if the input is a valid integer; false otherwise.
     */
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);  // Try converting to an integer
            return true;  // If successful, return true
        } catch (NumberFormatException e) {
            return false; // If an exception occurs, it's not an integer
        }
    }
}