package attendancelistcommands;

import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.captureSystemOut;
import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.initializeAttendanceFile;
import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.initializeTutorialClasses;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import attendance.AttendanceFile;
import command.attendancelistcommands.ViewStudentCommentsCommand;
import tutorial.TutorialClassList;


public class ViewStudentCommentsCommandTest {

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
    public void testViewStudentComments() {
        String[] input = {"T01, 1 ,Roselle Gustave Bonaparte,A333", "T01,1,Kim Dokja,A003"};
        ViewStudentCommentsCommand command = new ViewStudentCommentsCommand();
        outputStream = captureSystemOut();
        command.execute(input[0], attendanceFile);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Savant transmigrator"));
        outputStream = captureSystemOut();
        command.execute(input[1], attendanceFile);
        output = outputStream.toString().trim();
        assertTrue(output.contains("ugly squid"));
        assertTrue(output.contains("End of list"));

    }

    @Test
    public void testNoComments() {
        String[] input = {"T02,1,Han sooyung,A490", "T01,1,Alice Tan,A001",
                          "T01,2,Roselle Gustave Bonaparte,A333", "T02,1,Roselle Gustave Bonaparte,A333"};

        for (String s : input) {
            outputStream = captureSystemOut();
            ViewStudentCommentsCommand command = new ViewStudentCommentsCommand();
            command.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Selected student has no comments given to them"));
        }
    }


    @Test
    public void testNonCorrectNumInputs() {
        String[] input = {"T01,99", "1", "12fvc", "T02,22", "9", "abs,11,wong,A001", "123,222,fasc,222,4411,asdqewded"};
        for (String s : input) {
            outputStream = captureSystemOut();
            ViewStudentCommentsCommand command = new ViewStudentCommentsCommand();
            command.execute(s, attendanceFile);
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
            ViewStudentCommentsCommand command = new ViewStudentCommentsCommand();
            command.execute(s, attendanceFile);
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
            ViewStudentCommentsCommand command = new ViewStudentCommentsCommand();
            command.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid mark attendance command, "
                    + "please specify a valid attendancelist with a tutorial id, week and valid student id and name"));

        }
    }

    @Test
    void testSecondInputNonNumWrong() {
        String input = "T01,sba,Kim Dokja,A003";
        ViewStudentCommentsCommand command = new ViewStudentCommentsCommand();
        command.execute(input, attendanceFile);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("second parameter has to be numbers only"));

    }

}
