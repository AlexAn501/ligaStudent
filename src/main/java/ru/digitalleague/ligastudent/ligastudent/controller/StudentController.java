package ru.digitalleague.ligastudent.ligastudent.controller;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.ligastudent.ligastudent.api.StudentService;
import ru.digitalleague.ligastudent.ligastudent.api.TeacherService;
import ru.digitalleague.ligastudent.ligastudent.convertor.StudentConvertor;
import ru.digitalleague.ligastudent.ligastudent.convertor.TeacherConvertor;
import ru.digitalleague.ligastudent.ligastudent.dto.StudentDTO;
import ru.digitalleague.ligastudent.ligastudent.dto.StudentWithTeachersAndRolesDTO;
import ru.digitalleague.ligastudent.ligastudent.dto.StudentWithTeachersDTO;
import ru.digitalleague.ligastudent.ligastudent.dto.TeacherDTO;
import ru.digitalleague.ligastudent.ligastudent.model.Student;
import ru.digitalleague.ligastudent.ligastudent.model.Teacher;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @Autowired
    TeacherService teacherService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping()
    public ResponseEntity<List> getAllStudents() {
        List<StudentDTO> studentsDTO = studentService.getAllStudent()
                .stream()
                .map(StudentConvertor::fromStudent)
                .collect(Collectors.toList());
        amqpTemplate.convertAndSend("students", "Get all students");
        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentWithTeachersDTO> getStudent(@PathVariable long id) {
        Student student = studentService.getStudent(id);
        StudentWithTeachersDTO studentTeachers = StudentConvertor.fromStudentWithTeachers(student);
        amqpTemplate.convertAndSend("students", "Get student " + student);
        return new ResponseEntity<>(studentTeachers, HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<StudentWithTeachersAndRolesDTO> getStudentWithRole(@PathVariable long id){
        Student student = studentService.getStudent(id);
        StudentWithTeachersAndRolesDTO studentRoles = StudentConvertor.fromStudentWithTeacherAndRoles(student);
        amqpTemplate.convertAndSend("students","Get teacher and roles teacher " + student);
        return new ResponseEntity<>(studentRoles,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Student> addNewStudents(@RequestBody Student student) {
        studentService.saveOrUpdateStudent(student);
        amqpTemplate.convertAndSend("students", "Student was created " + student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        studentService.saveOrUpdateStudent(student);
        amqpTemplate.convertAndSend("students",
                String.format("Student with ID = %d was update", student.getStudentId()));
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        amqpTemplate.convertAndSend("students", "Student with ID = " + id + " was delete");
        return new ResponseEntity<>(String.format("Student with ID = %d was deleted", id), HttpStatus.OK);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<List> getAllTacherFromStudent(@PathVariable long id) {
        List<Teacher> studentTeachers = studentService.getAllTeacherFromStudent(id);
        List<TeacherDTO> teachers = studentTeachers
                .stream()
                .map(TeacherConvertor::fromTeacher)
                .collect(Collectors.toList());
        amqpTemplate.convertAndSend("students", "List of teachers student with ID =  " + id);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @PostMapping("/teachers")
    public ResponseEntity<String> addNewStudentToTeacher(@RequestHeader("student_id") long id,
                                                         @RequestBody Teacher teacher) {
        Student student = studentService.getStudent(id);
        teacher.setStudents(teacherService.getAllStudentsFromTeacher(teacher.getId()));
        //      Добавление учителя в список учителей студента происходит через учителя.
        teacher.addStudentToTeacher(student);
        teacherService.saveOrUpdateTeacher(teacher);
        amqpTemplate.convertAndSend("teachers"
                , "Added a teacher " + teacher + " to the student's teacher list with ID = " + id);
        return new ResponseEntity<>("Teacher with ID = " + teacher.getId()
                + " was added", HttpStatus.OK);
    }
}
