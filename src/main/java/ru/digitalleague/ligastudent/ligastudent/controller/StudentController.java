package ru.digitalleague.ligastudent.ligastudent.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.ligastudent.ligastudent.api.StudentService;
import ru.digitalleague.ligastudent.ligastudent.model.Student;
import ru.digitalleague.ligastudent.ligastudent.model.Teacher;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping("/students")
    public ResponseEntity<List> getAllStudents() {
        List<Student> students = studentService.getAllStudent();
        amqpTemplate.convertAndSend("get all students");
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable long id) {
        Student student = studentService.getStudent(id);
        amqpTemplate.convertAndSend(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> addNewStudents(@RequestBody Student student) {
        studentService.saveOrUpdateStudent(student);
        amqpTemplate.convertAndSend("student was created", student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/students")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        studentService.saveOrUpdateStudent(student);
        amqpTemplate.convertAndSend("student was update", student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        amqpTemplate.convertAndSend("student with id =" + id + " was delete");
        return new ResponseEntity<>(String.format("Student with ID = %d was deleted", id), HttpStatus.OK);
    }

    @GetMapping("/students-teachers/{id}")
    public ResponseEntity<List> getAllTacherFromStudent(long id) {
        List<Teacher> studentTeachers = studentService.getAllTeacherFromStudent(id);

        return new ResponseEntity<>(studentTeachers, HttpStatus.OK);
    }

    @PostMapping("/students-teachers")
    public ResponseEntity<String> addNewStudentToTeacher(@RequestHeader("student_id")  long id,
                                                         @RequestBody Teacher teacher) {
        Student student = studentService.getStudent(id);
        student.addTeacherToStudent(teacher);
        return new ResponseEntity<>("Teacher with id = " + teacher.getId()
                + " was added", HttpStatus.OK);
    }
}
