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
import command.attendancelistcommands.DeleteStudentCommentCommand;
import command.attendancelistcommands.ViewStudentCommentsCommand;
import tutorial.TutorialClassList;


public class DeleteStudentCommentTest {

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
        String[] input = {"T01,1,Roselle Gustave Bonaparte,A333", "T01,1,Kim Dokja,A003"};
        String[] newComments = {"chinese;clown", "korean;Reader"};
        ViewStudentCommentsCommand command = new ViewStudentCommentsCommand();
        CommentOnStudentCommand command1 = new CommentOnStudentCommand();
        DeleteStudentCommentCommand command2 = new DeleteStudentCommentCommand();
        outputStream = captureSystemOut();
        command1.execute(input[0] + "//" + newComments[0], attendanceFile);
        command.execute(input[0], attendanceFile);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("1. Savant transmigrator"));
        assertTrue(output.contains("2. chinese"));
        assertTrue(output.contains("3. clown"));

        outputStream = captureSystemOut();
        command2.execute(input[0] + "//1", attendanceFile);
        command.execute(input[0], attendanceFile);
        output = outputStream.toString().trim();
        assertTrue(output.contains("1. chinese"));
        assertTrue(output.contains("2. clown"));


        outputStream = captureSystemOut();
        command1.execute(input[1] + "//" + newComments[1], attendanceFile);
        command.execute(input[1], attendanceFile);
        output = outputStream.toString().trim();
        assertTrue(output.contains("1. ugly squid"));
        assertTrue(output.contains("2. korean"));
        assertTrue(output.contains("3. Reader"));

        outputStream = captureSystemOut();
        command2.execute(input[1] + "//1", attendanceFile);
        command2.execute(input[1] + "//2", attendanceFile);
        command.execute(input[1], attendanceFile);
        output = outputStream.toString().trim();
        assertTrue(output.contains("1. korean"));

    }

    @Test
    public void testNoComments() {
        String[] input = {"T02,1,Han sooyung,A490//1", "T01,1,Alice Tan,A001//1",
                          "T01,2,Roselle Gustave Bonaparte,A333//1", "T02,1,Roselle Gustave Bonaparte,A333//3"};

        for (String s : input) {
            outputStream = captureSystemOut();
            DeleteStudentCommentCommand command = new DeleteStudentCommentCommand();
            command.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Comment to be deleted was not present"));
        }
    }


    @Test
    public void testNonCorrectNumInputs() {
        String[] input = {"T01,99", "1", "12fvc", "T02,22//comment123", "9//ased",
                          "abs,11,wong,A001", "123,222,fasc,222,4411,asdqewded" + "//comment"};
        for (String s : input) {
            outputStream = captureSystemOut();
            DeleteStudentCommentCommand command = new DeleteStudentCommentCommand();
            command.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid mark attendance command, "
                    + "please specify a valid attendancelist with a tutorial id, week and valid student id and name"));
        }
    }

    @Test
    public void testDontHaveTheStudentInputs() {
        String[] input = {"T01,2,Wong Yi Hao,A002//1", "T02,3,Klein,A000//1", "T01,3,Aseop,A220//1",
                          "T02,2,Imhotep,A103//1", "T02,1,Aardvark,A117//1",
                          "T03,1,Roselle Gustave Bonaparte,A333//1"};
        for (String s : input) {
            outputStream = captureSystemOut();
            DeleteStudentCommentCommand command = new DeleteStudentCommentCommand();
            command.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid mark attendance command, "
                    + "please specify a valid attendancelist with a tutorial id, week and valid student id and name"));
        }
    }

    @Test
    void testDontHaveAttendanceList() {
        String[] input = {"T01,10,Roselle Gustave Bonaparte,A333//1", "T01,11,Kim Dokja,A003//1",
                          "T03,2,Kim Dokja,A003//2", "T10,1,Roselle Gustave Bonaparte,A333//1",
                             "T02,8,Roselle Gustave Bonaparte,A333//2"};
        for (String s : input) {
            outputStream = captureSystemOut();
            DeleteStudentCommentCommand command = new DeleteStudentCommentCommand();
            command.execute(s, attendanceFile);
            String output = outputStream.toString().trim();
            assertTrue(output.contains("Invalid mark attendance command, "
                    + "please specify a valid attendancelist with a tutorial id, week and valid student id and name"));

        }
    }

    @Test
    void testSecondInputNonNumWrong() {
        String input = "T01,sba,Kim Dokja,A003//2";
        DeleteStudentCommentCommand command = new DeleteStudentCommentCommand();
        command.execute(input, attendanceFile);
        String output = outputStream.toString().trim();
        assertTrue(output.contains("second parameter and comment number has to be numbers only"));

        input = "T01,1,Kim Dokja,A003//abs";
        command.execute(input, attendanceFile);
        output = outputStream.toString().trim();
        assertTrue(output.contains("second parameter and comment number has to be numbers only"));

    }
}
