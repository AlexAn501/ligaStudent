package ru.digitalleague.ligastudent.ligastudent.dto;

import ru.digitalleague.ligastudent.ligastudent.model.Student;

public class StudentDTO {
    private long studentId;
    private String name;
    private String middleName;
    private String lastName;
    private String speciality;
    private int course;

    public static StudentDTO fromStudent(Student student){
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(student.getStudentId());
        studentDTO.setName(student.getName());
        studentDTO.setMiddleName(student.getMiddleName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setSpeciality(student.getSpeciality());
        studentDTO.setCourse(student.getCourse());

        return studentDTO;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}
