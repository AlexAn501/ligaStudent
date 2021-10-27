package ru.digitalleague.ligastudent.ligastudent.dto;

import java.util.List;

public class StudentWithTeachersDTO {
    private long studentId;
    private String name;
    private String middleName;
    private String lastName;
    private String speciality;
    private int course;
    private String login;
    private List<TeacherDTO> teachers;

    public StudentWithTeachersDTO() {
    }

    public StudentWithTeachersDTO(StudentDTO studentDTO) {
        this.studentId = studentDTO.getStudentId();
        this.name = studentDTO.getName();
        this.middleName = studentDTO.getMiddleName();
        this.lastName = studentDTO.getLastName();
        this.speciality = studentDTO.getSpeciality();
        this.course = studentDTO.getCourse();
        this.login = studentDTO.getLogin();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<TeacherDTO> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeacherDTO> teachers) {
        this.teachers = teachers;
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
