package ru.digitalleague.ligastudent.ligastudent.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.ligastudent.ligastudent.api.TeacherService;
import ru.digitalleague.ligastudent.ligastudent.dto.StudentDTO;
import ru.digitalleague.ligastudent.ligastudent.dto.TeacherDTO;
import ru.digitalleague.ligastudent.ligastudent.dto.TeacherWithStudentsDTO;
import ru.digitalleague.ligastudent.ligastudent.model.Student;
import ru.digitalleague.ligastudent.ligastudent.model.Teacher;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;
    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/teachers")
    public ResponseEntity<List> getAllStudents() {
        List<TeacherDTO> teachersDTO = teacherService.getAllTeacher()
                .stream()
                .map(TeacherDTO::fromTeacher)
                .collect(Collectors.toList());

        amqpTemplate.convertAndSend("teachers", "get all teachers");
        return new ResponseEntity<>(teachersDTO, HttpStatus.OK);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherWithStudentsDTO> getTeacher(@PathVariable long id) {
        Teacher teacher = teacherService.getTeacher(id);
        TeacherWithStudentsDTO teacherStudents = TeacherWithStudentsDTO.fromTeacher(teacher);
        amqpTemplate.convertAndSend("teachers", "call teacher " + teacher);
        return new ResponseEntity<>(teacherStudents, HttpStatus.OK);
    }

    @PostMapping("/teachers")
    public ResponseEntity<Teacher> addNewTeacher(@RequestBody Teacher teacher) {
        teacherService.saveOrUpdateTeacher(teacher);
        amqpTemplate.convertAndSend("teachers", "teacher was created " + teacher);
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    @PutMapping("/teachers")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher) {
        teacherService.saveOrUpdateTeacher(teacher);
        amqpTemplate.convertAndSend("teachers", "teacher was update " + teacher);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable long id) {
        teacherService.deleteTeacher(id);
        amqpTemplate.convertAndSend("teachers", "teacher with id =" + id + " was delete");
        return new ResponseEntity<>(String.format("Teacher with ID = %d was deleted", id), HttpStatus.OK);
    }


    @GetMapping("/teachers/students/{id}")
    public ResponseEntity<List> getAllStudentsFromTeacher(@PathVariable long id) {
        List<Student> teacherStudents = teacherService.getAllStudentsFromTeacher(id);
        List<StudentDTO> students = teacherStudents
                .stream()
                .map(StudentDTO::fromStudent)
                .collect(Collectors.toList());
        amqpTemplate.convertAndSend("teachers", "Student ");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/teachers/students")
    public ResponseEntity<String> addNewStudentToTeacher(@RequestHeader("teacher_id") long id,
                                                         @RequestBody Student student) {
        Teacher teacher = teacherService.getTeacher(id);
        teacher.addStudentToTeacher(student);
        teacherService.saveOrUpdateTeacher(teacher);
        return new ResponseEntity<>("student with id = " + student.getStudentId()
                + " was added", HttpStatus.OK);
    }
}
