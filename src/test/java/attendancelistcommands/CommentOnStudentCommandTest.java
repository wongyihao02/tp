package attendancelistcommands;

import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.captureSystemOut;
import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.initializeAttendanceFile;
import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.initializeTutorialClasses;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import attendance.AttendanceFile;
import command.attendancelistcommands.CommentOnStudentCommand;
import command.attendancelistcommands.ViewStudentCommentsCommand;
import tutorial.TutorialClassList;

public class CommentOnStudentCommandTest {

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
        String[] input = {"T01, 1,Roselle Gustave Bonaparte ,A333", "T01,1,Kim Dokja,A003"};
        String[] newComments = {"chinese; clown ", "korean;Reader  "};
        ViewStudentCommentsCommand command = new ViewStudentCommentsCommand();
        CommentOnStudentCommand command1 = new CommentOnStudentCommand();
        outputStream = captureSystemOut();
        command1.execute(input[0] + "//" + newComments[0], attendanceFile);
        command.execute(input[0], attendanceFile);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("1. Savant transmigrator"));
        assertTrue(output.contains("2. chinese"));
        assertTrue(output.contains("3. clown"));
        assertTrue(output.contains("End of list"));
        outputStream = captureSystemOut();
        command1.execute(input[1] + "//" + newComments[1], attendanceFile);
        command.execute(input[1], attendanceFile);
        output = outputStream.toString().trim();
        assertTrue(output.contains("1. ugly squid"));
        assertTrue(output.contains("2. korean"));
        assertTrue(output.contains("3. Reader"));
        assertTrue(output.contains("End of list"));

    }

    @Test
    public void testNoComments() {
        String[] input = {"T02,1,Han sooyung,A490", "T01,1,Alice Tan,A001",
                          "T01,2,Roselle Gustave Bonaparte,A333", "T02,1,Roselle Gustave Bonaparte,A333"};

        for (String s : input) {
            outputStream = captureSystemOut();
            CommentOnStudentCommand command = new CommentOnStudentCommand();
            command.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid mark attendance command, "
                    + "please specify a valid attendancelist with a tutorial id, week and valid student id and name"));
        }
    }


    @Test
    public void testNonCorrectNumInputs() {
        String[] input = {"T01,99", "1", "12fvc", "T02,22//comment123", "9//ased",
                          "abs,11,wong,A001", "123,222,fasc,222,4411,asdqewded" + "//comment"};
        for (String s : input) {
            outputStream = captureSystemOut();
            CommentOnStudentCommand command = new CommentOnStudentCommand();
            command.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid mark attendance command, "
                    + "please specify a valid attendancelist with a tutorial id, week and valid student id and name"));
        }
    }

    @Test
    public void testDontHaveTheStudentInputs() {
        String[] input = {"T01,2,Wong Yi Hao,A002//comment", "T02,3,Klein,A000//asd",
                          "T01,3,Aseop,A220//dsa", "T02,2,Imhotep,A103//asdaa",
                          "T02,1,Aardvark,A117//s", "T03,1,Roselle Gustave Bonaparte,A333//a"};
        for (String s : input) {
            outputStream = captureSystemOut();
            CommentOnStudentCommand command = new CommentOnStudentCommand();
            command.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid mark attendance command, "
                    + "please specify a valid attendancelist with a tutorial id, week and valid student id and name"));
        }
    }

    @Test
    void testDontHaveAttendanceList() {
        String[] input = {"T01,10,Roselle Gustave Bonaparte,A333//comment2", "T01,11,Kim Dokja,A003//comment3",
                          "T03,2,Kim Dokja,A003//abds", "T10,1,Roselle Gustave Bonaparte,A333//sadad",
                          "T02,8,Roselle Gustave Bonaparte,A333//abdse"};
        for (String s : input) {
            outputStream = captureSystemOut();
            CommentOnStudentCommand command = new CommentOnStudentCommand();
            command.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid mark attendance command, "
                    + "please specify a valid attendancelist with a tutorial id, week and valid student id and name"));

        }
    }

    @Test
    void testSecondInputNonNumWrong() {
        String input = "T01,sba,Kim Dokja,A003//comment";
        CommentOnStudentCommand command = new CommentOnStudentCommand();
        command.execute(input, attendanceFile);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("second parameter has to be numbers only"));

    }
}
