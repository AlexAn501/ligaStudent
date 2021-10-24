package ru.digitalleague.ligastudent.ligastudent.dto;

import ru.digitalleague.ligastudent.ligastudent.model.Student;
import ru.digitalleague.ligastudent.ligastudent.model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class StudentWithTeachersDTO {
    private long studentId;
    private String name;
    private String middleName;
    private String lastName;
    private String speciality;
    private int course;
    private List<TeacherDTO> teachers;


    public static StudentWithTeachersDTO fromStudent(Student student){
        StudentWithTeachersDTO dto = new StudentWithTeachersDTO();
        dto.setStudentId(student.getStudentId());
        dto.setName(student.getName());
        dto.setMiddleName(student.getMiddleName());
        dto.setLastName(student.getLastName());
        dto.setSpeciality(student.getSpeciality());
        dto.setCourse(student.getCourse());
        List<TeacherDTO> teachers = new ArrayList<>();

        for (Teacher teacher : student.getTeachers()) {
            teachers.add(TeacherDTO.fromTeacher(teacher));
        }
        dto.setTeachers(teachers);

        return dto;
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
