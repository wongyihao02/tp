package attendancelistcommands;

import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.captureSystemOut;
import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.initializeAttendanceFile;
import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.initializeTutorialClasses;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import attendance.AttendanceFile;
import command.attendancelistcommands.CreateNewAttendanceListCommand;
import command.attendancelistcommands.ShowAttendanceListCommand;
import tutorial.TutorialClassList;

public class CreateNewAttendanceListCommandTest {

    private AttendanceFile attendanceFile;
    private TutorialClassList tutorialClassList;
    private ByteArrayOutputStream outputStream;
    private ArrayList<Object> tutAtten;

    @BeforeEach
    public void setup() {
        attendanceFile = initializeAttendanceFile();
        tutorialClassList = initializeTutorialClasses();
        outputStream = captureSystemOut();
        ArrayList<Object> tutAtten1 = new ArrayList<Object>();
        tutAtten1.add(tutorialClassList);
        tutAtten1.add(attendanceFile);
        tutAtten = tutAtten1;
    }

    @Test
    public void testNormalInput() {
        String input = "T01, 100";

        ShowAttendanceListCommand command = new ShowAttendanceListCommand();
        CreateNewAttendanceListCommand command1 = new CreateNewAttendanceListCommand();
        command1.execute(input, tutAtten);
        command.execute(input, attendanceFile);
        String output = outputStream.toString().trim();

        assertTrue(output.contains("Attendance List for tutorial T01 week 100:"));
        assertTrue(output.contains("Roselle Gustave Bonaparte(A333): Absent"));
        assertTrue(output.contains("Kim Dokja(A003): Absent"));
        assertFalse(output.contains("Han sooyung(A490): Absent"));
        assertTrue(output.contains("End of list"));
    }

    @Test
    void testOneInputWrong() {

        String[] input = {"T01", "1", "12fvc", "T02", "9"};
        for (String s : input) {
            outputStream = captureSystemOut();
            CreateNewAttendanceListCommand command1 = new CreateNewAttendanceListCommand();
            command1.execute(s, tutAtten);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid List all students in attendanceList command, "
                    + "please specify a valid attendancelist with a valid tutorial id and a valid week"));

        }

    }

    @Test
    void testSecondInputNonNumWrong() {
        String input = "T01,sba";
        CreateNewAttendanceListCommand command1 = new CreateNewAttendanceListCommand();
        command1.execute(input, tutAtten);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("second parameter has to be numbers only"));


    }

    @Test
    void testTooManyInputsWrong() {
        String[] input = {"T01,1,1,1", "1239c,12313,111", "T03,1,2", "T10,2,1", "9,3,1"};
        for (String s : input) {
            outputStream = captureSystemOut();
            CreateNewAttendanceListCommand command1 = new CreateNewAttendanceListCommand();
            command1.execute(s, tutAtten);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid List all students in attendanceList command, "
                    + "please specify a valid attendancelist with a valid tutorial id and a valid week"));

        }
    }

    @Test
    void createExistingAttendanceList() {
        String[] input = {"T01,1", "T01,2", "T01,3", "T02,3", "T02,6", "T03,1"};
        for (String s : input) {
            outputStream = captureSystemOut();
            CreateNewAttendanceListCommand command1 = new CreateNewAttendanceListCommand();
            command1.execute(s, tutAtten);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Attendance list for the week already exists"));

        }
    }

    @Test
    void testNegativeWeek() {
        String[] input = {"T01,-1", "T01,-2", "T01,-3", "T02,-3", "T02,-6", "T03,-1"};
        for (String s : input) {
            outputStream = captureSystemOut();
            CreateNewAttendanceListCommand command1 = new CreateNewAttendanceListCommand();
            command1.execute(s, tutAtten);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid List all students in attendanceList command, "
                    + "please specify a valid attendancelist with a valid tutorial id and a valid week"));

        }
    }
}
