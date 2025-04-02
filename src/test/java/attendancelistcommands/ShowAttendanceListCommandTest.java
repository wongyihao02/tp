package attendancelistcommands;

import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.initializeAttendanceFile;
import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.initializeTutorialClasses;
import static attendancelistcommands.handyfuncs.AttendanceListCommandsTestHandyFuncs.captureSystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import attendance.AttendanceFile;
import attendance.AttendanceList;
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
        AttendanceList attendanceList = attendanceFile.getAttendanceByNameAndWeek(1, "T01");
        ShowAttendanceListCommand command = new ShowAttendanceListCommand();
        command.execute(input, attendanceFile);
        String output = outputStream.toString().trim();

        assertTrue(output.contains("Attendance List for tutorial T01 week 1:"));
        assertTrue(output.contains("Roselle Gustave Bonaparte(A333): Absent"));
    }
}
