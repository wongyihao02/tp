package attendancelistcommands;


import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.captureSystemOut;
import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.initializeAttendanceFile;
import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.initializeTutorialClasses;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import attendance.AttendanceFile;
import command.attendancelistcommands.MarkStudentAttendanceCommand;
import command.attendancelistcommands.ShowAttendanceListCommand;
import tutorial.TutorialClassList;

public class MarkStudentAttendanceCommandTest {

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
    public void testMarkAttendance() {
        String[] input = {"T01,1,Roselle Gustave Bonaparte,A333", "T01,1,Kim Dokja,A003",
                          "T02,3,Kim Dokja,A003", "T01,2,Roselle Gustave Bonaparte,A333"};


        ShowAttendanceListCommand command = new ShowAttendanceListCommand();
        MarkStudentAttendanceCommand command1 = new MarkStudentAttendanceCommand();
        command.execute("T01,1", attendanceFile);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Roselle Gustave Bonaparte(A333): Absent"));
        assertTrue(output.contains("Kim Dokja(A003): Absent"));
        outputStream = captureSystemOut();
        command1.execute(input[0], attendanceFile);
        command1.execute(input[1], attendanceFile);
        command.execute("T01,1", attendanceFile);
        output = outputStream.toString().trim();
        assertTrue(output.contains("Student marked Present"));
        assertTrue(output.contains("Roselle Gustave Bonaparte(A333): Present"));
        assertTrue(output.contains("Kim Dokja(A003): Present"));
        assertTrue(output.contains("End of list"));

        outputStream = captureSystemOut();
        command.execute("T02,3", attendanceFile);
        output = outputStream.toString().trim();
        assertTrue(output.contains("Kim Dokja(A003): Absent"));
        outputStream = captureSystemOut();
        command1.execute(input[2], attendanceFile);
        command.execute("T02,3", attendanceFile);
        output = outputStream.toString().trim();
        assertTrue(output.contains("Student marked Present"));
        assertTrue(output.contains("Kim Dokja(A003): Present"));
        assertTrue(output.contains("End of list"));


        outputStream = captureSystemOut();
        command.execute("T01,2", attendanceFile);
        output = outputStream.toString().trim();
        assertTrue(output.contains("Roselle Gustave Bonaparte(A333): Absent"));
        outputStream = captureSystemOut();
        command1.execute(input[3], attendanceFile);
        command.execute("T01,2", attendanceFile);
        output = outputStream.toString().trim();
        assertTrue(output.contains("Student marked Present"));
        assertTrue(output.contains("Roselle Gustave Bonaparte(A333): Present"));
        assertTrue(output.contains("End of list"));
    }

    @Test
    public void testNonCorrectNumInputs() {
        String[] input = {"T01,99", "1", "12fvc", "T02,22", "9", "abs,11,wong,A001", "123,222,fasc,222,4411,asdqewded"};
        for (String s : input) {
            outputStream = captureSystemOut();
            MarkStudentAttendanceCommand command1 = new MarkStudentAttendanceCommand();
            command1.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid mark attendance command, "
                    + "please specify a valid attendancelist with a tutorial id, week and valid student id and name"));
        }
    }

    @Test
    public void testDontHaveTheStudentInputs() {
        String[] input = {"T01,2,Wong Yi Hao,A002", "T02,3,Klein,A000", "T01,3,Aseop,A220",
                          "T02,2,Imhotep,A103", "T02,1,Aardvark,A117", "T03,1,Roselle Gustave Bonaparte,A333"};
        for (String s : input) {
            outputStream = captureSystemOut();
            MarkStudentAttendanceCommand command1 = new MarkStudentAttendanceCommand();
            command1.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid mark attendance command, "
                    + "please specify a valid attendancelist with a tutorial id, week and valid student id and name"));
        }
    }

    @Test
    void testDontHaveAttendanceList() {
        String[] input = {"T01,10,Roselle Gustave Bonaparte,A333", "T01,11,Kim Dokja,A003",
                          "T03,2,Kim Dokja,A003", "T10,1,Roselle Gustave Bonaparte,A333",
                          "T02,8,Roselle Gustave Bonaparte,A333"};
        for (String s : input) {
            outputStream = captureSystemOut();
            MarkStudentAttendanceCommand command1 = new MarkStudentAttendanceCommand();
            command1.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid mark attendance command, "
                    + "please specify a valid attendancelist with a tutorial id, week and valid student id and name"));

        }
    }

    @Test
    void testSecondInputNonNumWrong() {
        String input = "T01,sba,Kim Dokja,A003";
        MarkStudentAttendanceCommand command1 = new MarkStudentAttendanceCommand();
        command1.execute(input, attendanceFile);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("second parameter has to be numbers only"));


    }
}
