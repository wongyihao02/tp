package Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Utility class for parsing and formatting date-time strings.
 * This class helps convert input date formats into a readable format and validates the format of date-time strings.
 */
public class DateTimeFormatTool{

    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
    /**
     * Parses a date-time string into a formatted string.
     * If the input is "now", it returns the current date in the format "dd MMM yyyy".
     *
     * @param dateTimeStr The date-time string to be parsed.
     * @return The formatted date string in the format "dd MMM yyyy".
     */
    public static String parseDateTime(String dateTimeStr) {
        if ("now".equalsIgnoreCase(dateTimeStr)) {
            LocalDate currentDate = LocalDate.now();
            return currentDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        } else {
            LocalDate date = LocalDate.parse(dateTimeStr, inputFormatter);
            return date.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        }
    }

    /**
     * Checks if the provided date-time string has a valid format.
     *
     * @param dateTimeStr The date-time string to be validated.
     * @return true if the date-time string is valid; false otherwise.
     */
    public static boolean isValidDateTimeFormat(String dateTimeStr) {
        try {
            parseDateTime(dateTimeStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}