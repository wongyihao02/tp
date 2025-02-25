package student;

public class Student {
    private String name;
    private int age;
    private String gender;
    private String contact;
    private String matricNumber;
    private String TutorialClass;

    public Student(String name, int age, String gender, String contact, String matricNumber, String tutorialClass) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
        this.matricNumber = matricNumber;
        this.TutorialClass = tutorialClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public void setMatricNumber(String matricNumber) {
        this.matricNumber = matricNumber;
    }
    public String getTutorialClass() {
        return TutorialClass;
    }
    public void setTutorialClass(String tutorialClass) {
        TutorialClass = tutorialClass;
    }

    public String toFileFormat() {
        return getName() +","+ getAge() +"," + getGender() +"," + getContact() +","+ getMatricNumber() +","+getTutorialClass()+"/n";
    }

}
