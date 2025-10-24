import java.io.*;
import java.util.ArrayList;

public class StudentDatabase {
    ArrayList<Student> records;
    String filename;

    StudentDatabase (String filename) {
        this.filename = filename;
        records = new ArrayList<>();
    }

    public void readFromFile(){
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null){
                records.add(createRecordFrom(line));
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

    public void saveToFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))){
            for (Student student : records){
                bw.write(student.toString());
                bw.newLine();
            }
            System.out.println("Successfully wrote to the file");
        }
        catch (IOException e){
            System.out.println("Error writing file");
        }
    }

    public void addStudent(Student student){
        records.add(student);
    }

    public void updateStudent(Student s) {
        int i = 0;
        for (Student student : records){
            if (student.getStudentID() == s.getStudentID()){
                records.set(i , student);
                break;
            }
            i++;
        }
    }

    public void deleteStudent(int id){
        for (Student student : records){
            if (student.getStudentID() == id){
                records.remove(student);
            }
        }
    }

    public Student searchStudent(String key){
        for(Student student : records){
            if( String.valueOf(student.getStudentID()).equals(key) || student.getFullName().equals(key) )
                return student;
        }
        System.out.println("Student not found");
        return null;
    }

    public ArrayList<Student> getAllStudents(){
        return new ArrayList<>(records);
    }
}
