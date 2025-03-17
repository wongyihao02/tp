package Tutorial;

import Students.StudentList;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class TutorialClass implements Comparable<TutorialClass> {
    private String tutorialName;
    private StudentList studentList;
    private LocalTime startTime;
    private LocalTime endTime;
    private DayOfWeek dayOfWeek;
    private LocalDate date;

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

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public int compareTo(TutorialClass o) {

        if (this.date.isEqual(o.getDate())) {
            if (this.startTime.isBefore(o.getStartTime())) {
                return -1;
            } else if (this.endTime.isAfter(o.getEndTime())) {
                return 1;
            } else {
                return 0;
            }
        }
        return date.compareTo(o.getDate());
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