package lk.ijse.DTO;

import lk.ijse.Entity.Course;
import lk.ijse.Entity.Student;


public class RegistrationDTO {

    private String registrationID;
    private String date;
    private Student student;
    private Course course;
    private String studentName;
    private String programName;
    private double programFee;
    private double upfrontPayment;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String registrationID, String date, Student student,  Course course, String studentName, String programName, double programFee, double upfrontPayment) {
        this.registrationID = registrationID;
        this.date = date;
        this.student = student;
        this.course= course;
        this.studentName = studentName;
        this.programName = programName;
        this.programFee = programFee;
        this.upfrontPayment = upfrontPayment;
    }

    public RegistrationDTO(String registrationID, String text, StudentDto student, CourseDto course, String text1, String text2, double programFee, double upfrontPayment) {
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setProgram(Course course) {
        this.course = course;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public double getProgramFee() {
        return programFee;
    }

    public void setProgramFee(double programFee) {
        this.programFee = programFee;
    }

    public double getUpfrontPayment() {
        return upfrontPayment;
    }

    public void setUpfrontPayment(double upfrontPayment) {
        this.upfrontPayment = upfrontPayment;
    }

    @Override
    public String toString() {
        return "RegistrationDTO{" +
                "registrationID='" + registrationID + '\'' +
                ", date='" + date + '\'' +
                ", student=" + student +
                ", course=" + course +
                ", studentName='" + studentName + '\'' +
                ", programName='" + programName + '\'' +
                ", programFee=" + programFee +
                ", upfrontPayment=" + upfrontPayment +
                '}';
    }
}
