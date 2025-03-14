package Attendance;

import students.Student;
import Tutorial.TutorialClass;

import java.util.HashMap;
import java.util.Map;

public class AttendanceList {
    private Map<Student, String> attendanceMap;
    private int weekNumber;
    TutorialClass tutorialClass;

    public AttendanceList(TutorialClass tutorialClass, int weekNumber) {
        this.tutorialClass = tutorialClass;
        attendanceMap = new HashMap<>();
        for (Student student : tutorialClass.getStudentList().getStudents()) {
            attendanceMap.put(student, "Present");
        }

        this.weekNumber = weekNumber;
    }



    public void markPresent(Student student) {
        if (attendanceMap.containsKey(student)) {
            attendanceMap.put(student, "Present");
        }
    }

    public void markAbsent(Student student) {
        if (attendanceMap.containsKey(student)) {
            attendanceMap.put(student, "Absent");
        }
    }

    public boolean isPresent(Student student) {
        return attendanceMap.get(student).equals("Present");
    }

    public Map<Student, String> getAttendanceMap() {
        return attendanceMap;
    }
    public int getWeekNumber() {
        return weekNumber;
    }

    public void setWeekNumber(int weekNumber) {
        this.weekNumber = weekNumber;
    }

    public TutorialClass getTutorialClass() {
        return tutorialClass;
    }

    public void setTutorialClass(TutorialClass tutorialClass) {
        this.tutorialClass = tutorialClass;
    }

    @Override
    public String toString() {
        return "weekNumber: " + weekNumber + tutorialClass.toString()+"\n" +attendanceMap.toString();

    }
}