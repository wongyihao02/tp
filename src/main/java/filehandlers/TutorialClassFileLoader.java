package filehandlers;

import students.StudentList;
import tutorial.TutorialClass;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

public class TutorialClassFileLoader implements FileLoader<TutorialClass> {
    private final StudentListFileLoader studentListFileLoader = new StudentListFileLoader();

    @Override
    public TutorialClass loadFromFile(String path) {
        throw new UnsupportedOperationException("Use loadFromBlock(List<String>) instead.");
    }

    public TutorialClass loadFromBlock(List<String> blockLines) {
        String header = blockLines.get(0).substring(1).trim(); // Remove '#' and trim
        String[] meta = header.split(",", 4);
        String name = meta[0].trim();
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(meta[1].trim().toUpperCase());
        LocalTime start = LocalTime.parse(meta[2].trim());
        LocalTime end = LocalTime.parse(meta[3].trim());

        List<String> studentLines = blockLines.subList(1, blockLines.size());
        StudentList studentList = studentListFileLoader.loadFromLines(studentLines, name);

        TutorialClass tutorial = new TutorialClass();
        tutorial.setTutorialName(name);
        tutorial.setDayOfWeek(dayOfWeek);
        tutorial.setStartTime(start);
        tutorial.setEndTime(end);
        tutorial.setStudentList(studentList);

        return tutorial;
    }
}
