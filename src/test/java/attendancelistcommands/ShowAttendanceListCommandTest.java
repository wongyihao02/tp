package attendancelistcommands;

import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.initializeAttendanceFile;
import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.initializeTutorialClasses;
import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.captureSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;

import attendance.AttendanceFile;
import command.attendancelistcommands.ShowAttendanceListCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import tutorial.TutorialClassList;

public class ShowAttendanceListCommandTest {

    private AttendanceFile attendanceFile;
    private TutorialClassList tutorialClassList;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    public void setup() {
        attendanceFile = initializeAttendanceFile();
        tutorialClassList = initializeTutorialClasses();
        outputStream = captureSystemOut();
    }

    @Test
    public void testNormalInput() {
        String input = "T01,1";
        ShowAttendanceListCommand command = new ShowAttendanceListCommand();
        command.execute(input, attendanceFile);
        String output = outputStream.toString().trim();

        assertTrue(output.contains("Attendance List for tutorial T01 week 1:"));
        assertTrue(output.contains("Roselle Gustave Bonaparte(A333): Absent"));
        assertTrue(output.contains("Kim Dokja(A003): Absent"));
        assertFalse(output.contains("Han sooyung(A490): Absent"));
        assertTrue(output.contains("end of list"));
    }

    @Test
    void testEmptyList() {
        String input = "T03,1";
        ShowAttendanceListCommand command = new ShowAttendanceListCommand();
        command.execute(input, attendanceFile);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Attendance List for tutorial T03 week 1:"));
        assertTrue(output.contains("No student in attendance list"));
    }

    @Test
    void testemptyInput() {
        String input = "";
        ShowAttendanceListCommand command = new ShowAttendanceListCommand();
        command.execute(input, attendanceFile);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Invalid List all students in attendanceList command, please specify a valid attendancelist with a valid tutorial id and a valid week"));
    }

    @Test
    void testOneInputWrong() {
        String[] input = {"T01", "1", "12fvc", "T10", "9"};
        for (String s : input) {
            ShowAttendanceListCommand command = new ShowAttendanceListCommand();
            command.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid List all students in attendanceList command, please specify a valid attendancelist with a valid tutorial id and a valid week"));

        }
    }

    @Test
    void testDontHaveTutInput() {
        String input = "";
        ShowAttendanceListCommand command = new ShowAttendanceListCommand();
        command.execute(input, attendanceFile);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Invalid List all students in attendanceList command, please specify a valid attendancelist with a valid tutorial id and a valid week"));
    }

}
