package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class IntegerCheckerTest {

    @Test
    public void testIsInteger_withValidIntegers_shouldReturnTrue() {
        assertTrue(IntegerChecker.isInteger("123"));
        assertTrue(IntegerChecker.isInteger("-456"));
        assertTrue(IntegerChecker.isInteger("0"));
    }

    @Test
    public void testIsInteger_withInvalidStrings_shouldReturnFalse() {
        assertFalse(IntegerChecker.isInteger("12.34"));     // Decimal
        assertFalse(IntegerChecker.isInteger("abc"));       // Letters
        assertFalse(IntegerChecker.isInteger("123abc"));    // Alphanumeric
        assertFalse(IntegerChecker.isInteger(""));          // Empty
        assertFalse(IntegerChecker.isInteger(" "));         // Space
        assertFalse(IntegerChecker.isInteger(null));        // Null
    }
}
