
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class StudentDatabase {
    ArrayList<Student> studentsDB ;
    String filename;

    StudentDatabase (String filename) {
        this.filename = filename;
        studentsDB = new ArrayList<>();
    }

    public void readFromFile(){
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null){
                studentsDB.add(createRecordFrom(line));
            }
        }
        catch (IOException e){
            System.out.println("Error reading file");
        }
    }
    public Student createRecordFrom(String line){
        String[] fields = line.split(",");
        int studentID = Integer.parseInt(fields[0]);
        String fullName = fields[1];
        int age = Integer.parseInt(fields[2]);
        String gender = fields[3];
        String department = fields[4];
        float gpa = Float.parseFloat(fields[5]);
        Student student = new Student(studentID,fullName,age,gender,department,gpa);
        return student;
    }
}
