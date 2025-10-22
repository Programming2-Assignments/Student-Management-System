public class Student {
    private int studentID;
    private String fullName;
    private int age;
    private String gender;
    private String department;
    private float  gpa;

    public Student(int studentID,String fullName, int age, String gender, String department, float gpa) {
        this.fullName = fullName;
        this.studentID = studentID;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.gpa = gpa;
    }

    public Student(String fullName, int age, String gender, String department, float gpa) {
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.department = department;
        this.gpa = gpa;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(int gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return studentID + "," + fullName  + "," + age + "," + gender  + "," + department + "," + gpa ;
    }
}
