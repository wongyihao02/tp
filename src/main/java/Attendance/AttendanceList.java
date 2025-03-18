package Attendance;

import students.Student;
import Tutorial.TutorialClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AttendanceList {
    private Map<Student, String> attendanceMap;
    private int weekNumber;
    private Map<Student, ArrayList<String>> commentList;
    TutorialClass tutorialClass;

    public AttendanceList(TutorialClass tutorialClass, int weekNumber) {
        this.tutorialClass = tutorialClass;
        attendanceMap = new HashMap<>();
        commentList = new HashMap<>();
        for (Student student : tutorialClass.getStudentList().getStudents()) {
            attendanceMap.put(student, "Present");
            commentList.put(student, new ArrayList<String>());
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


    public void addComments(Student student, ArrayList<String> comments) {
        try {
            if (commentList.containsKey(student)) {
                commentList.get(student).addAll(comments);
            } else {
                commentList.put(student, comments);
            }
        } catch (NullPointerException e) {
            System.out.println("error adding comment : " + e.getMessage());
        }
    }

    public ArrayList<String> getComment(Student student) {
        try {
            if (commentList.containsKey(student)) {
                return commentList.get(student);
            }
        } catch (NullPointerException e) {
            System.out.println("error getting comments : " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public boolean isPresent(Student student) {
        return attendanceMap.get(student).equals("Present");
    }

    public Map<Student, ArrayList<String>> getCommentList() { return commentList; }
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