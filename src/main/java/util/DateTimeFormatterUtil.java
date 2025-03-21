package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeFormatterUtil {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    // --- Date Methods ---

    public static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DATE_FORMAT);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Expected format: dd/MM/yyyy");
            return null;
        }
    }

    public static String formatDate(LocalDate date) {
        return date.format(DATE_FORMAT);
    }

    public static boolean isValidDate(String dateStr) {
        try {
            LocalDateTime.parse(dateStr, DATE_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // --- Time Methods ---

    public static LocalTime parseTime(String timeStr) {
        try {
            return LocalTime.parse(timeStr, TIME_FORMAT);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Expected format: HH:mm");
            return null;
        }
    }

    public static String formatTime(LocalTime time) {
        return time.format(TIME_FORMAT);
    }

    public static boolean isValidTime(String timeStr) {
        try {
            LocalDateTime.parse(timeStr, TIME_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // --- DateTime Methods ---

    public static LocalDateTime parseDateTime(String dateTimeStr) {
        try {
            return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMAT);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid datetime format. Expected format: dd/MM/yyyy HH:mm");
            return null;
        }
    }


    public static String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMAT);
    }

    public static boolean isValidDateTime(String dateTimeStr) {
        try {
            LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMAT);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
