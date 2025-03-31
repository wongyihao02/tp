package util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DateTimeFormatterUtilTest {

    @Test
    public void testParseDate_valid_shouldReturnCorrectDate() {
        LocalDate date = DateTimeFormatterUtil.parseDate("25/12/2023");
        assertNotNull(date);
        assertEquals(2023, date.getYear());
        assertEquals(12, date.getMonthValue());
        assertEquals(25, date.getDayOfMonth());
    }

    @Test
    public void testParseDate_invalid_shouldReturnNull() {
        assertNull(DateTimeFormatterUtil.parseDate("2023-12-25"));  // wrong format
        assertNull(DateTimeFormatterUtil.parseDate("not-a-date"));
    }

    @Test
    public void testFormatDate_shouldReturnCorrectString() {
        LocalDate date = LocalDate.of(2023, 12, 25);
        String formatted = DateTimeFormatterUtil.formatDate(date);
        assertEquals("25/12/2023", formatted);
    }

    @Test
    public void testIsValidDate() {
        assertTrue(DateTimeFormatterUtil.isValidDate("01/01/2024"));
        assertFalse(DateTimeFormatterUtil.isValidDate("2024-01-01"));  // wrong format
        assertFalse(DateTimeFormatterUtil.isValidDate("abc"));
    }

    @Test
    public void testParseTime_valid_shouldReturnCorrectTime() {
        LocalTime time = DateTimeFormatterUtil.parseTime("13:45");
        assertNotNull(time);
        assertEquals(13, time.getHour());
        assertEquals(45, time.getMinute());
    }

    @Test
    public void testParseTime_invalid_shouldReturnNull() {
        assertNull(DateTimeFormatterUtil.parseTime("1:45 PM")); // 12-hour format not supported
        assertNull(DateTimeFormatterUtil.parseTime("25:00"));   // invalid hour
    }

    @Test
    public void testFormatTime_shouldReturnCorrectString() {
        LocalTime time = LocalTime.of(9, 5);
        String formatted = DateTimeFormatterUtil.formatTime(time);
        assertEquals("09:05", formatted);
    }

    @Test
    public void testIsValidTime() {
        assertTrue(DateTimeFormatterUtil.isValidTime("00:00"));
        assertTrue(DateTimeFormatterUtil.isValidTime("23:59"));
        assertFalse(DateTimeFormatterUtil.isValidTime("12:60"));  // invalid minute
        assertFalse(DateTimeFormatterUtil.isValidTime("not-time"));
    }

    @Test
    public void testParseDateTime_valid_shouldReturnCorrectDateTime() {
        LocalDateTime dateTime = DateTimeFormatterUtil.parseDateTime("31/12/2023 23:59");
        assertNotNull(dateTime);
        assertEquals(2023, dateTime.getYear());
        assertEquals(12, dateTime.getMonthValue());
        assertEquals(31, dateTime.getDayOfMonth());
        assertEquals(23, dateTime.getHour());
        assertEquals(59, dateTime.getMinute());
    }

    @Test
    public void testParseDateTime_invalid_shouldReturnNull() {
        assertNull(DateTimeFormatterUtil.parseDateTime("2023-12-31T23:59")); // wrong format
        assertNull(DateTimeFormatterUtil.parseDateTime("not-a-datetime"));
    }

    @Test
    public void testFormatDateTime_shouldReturnCorrectString() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 1, 1, 8, 30);
        String formatted = DateTimeFormatterUtil.formatDateTime(dateTime);
        assertEquals("01/01/2024 08:30", formatted);
    }

    @Test
    public void testIsValidDateTime() {
        assertTrue(DateTimeFormatterUtil.isValidDateTime("01/01/2024 00:00"));
        assertFalse(DateTimeFormatterUtil.isValidDateTime("2024-01-01T00:00"));
        assertFalse(DateTimeFormatterUtil.isValidDateTime("blah blah"));
    }
}
