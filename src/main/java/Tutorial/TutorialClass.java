package Tutorial;

import students.StudentList;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class TutorialClass {
    private String tutorialName;
    private StudentList studentList;
    private LocalTime startTime;
    private LocalTime endTime;
    private DayOfWeek dayOfWeek;

    public String getTutorialName() {
        return tutorialName;
    }

    public void setTutorialName(String tutorialName) {
        this.tutorialName = tutorialName;
    }

    public StudentList getStudentList() {
        return studentList;
    }

    public void setStudentList(StudentList studentList) {
        this.studentList = studentList;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @Override
    public String toString() {
        return "tutorialName: " + tutorialName +
                ", dayOfWeek: " + dayOfWeek +
                ", from: " + startTime +
                ", to: " + endTime +
                ", studentList: " + studentList;
    }
}