package ru.digitalleague.ligastudent.ligastudent.dto;

import ru.digitalleague.ligastudent.ligastudent.model.Teacher;

public class TeacherDTO {
    private long id;
    private String name;
    private String middleName;
    private String lastName;
    private String chair;

    public static TeacherDTO fromTeacher(Teacher teacher){
        TeacherDTO teacherDTO = new TeacherDTO();
        teacherDTO.setId(teacher.getId());
        teacherDTO.setName(teacher.getName());
        teacherDTO.setMiddleName(teacher.getMiddleName());
        teacherDTO.setLastName(teacher.getLastName());
        teacherDTO.setChair(teacher.getChair());

        return teacherDTO;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getChair() {
        return chair;
    }

    public void setChair(String chair) {
        this.chair = chair;
    }
}
