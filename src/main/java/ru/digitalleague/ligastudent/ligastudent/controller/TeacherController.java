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
import ru.digitalleague.ligastudent.ligastudent.dto.*;
import ru.digitalleague.ligastudent.ligastudent.model.Student;
import ru.digitalleague.ligastudent.ligastudent.model.Teacher;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/teachers")
@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @Autowired
    StudentService studentService;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @GetMapping()
    public ResponseEntity<List> getAllStudents() {
        List<TeacherDTO> teachersDTO = teacherService.getAllTeacher()
                .stream()
                .map(TeacherConvertor::fromTeacher)
                .collect(Collectors.toList());

        amqpTemplate.convertAndSend("teachers", "Get all teachers");
        return new ResponseEntity<>(teachersDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherWithStudentsDTO> getTeacher(@PathVariable long id) {
        Teacher teacher = teacherService.getTeacher(id);
        TeacherWithStudentsDTO teacherStudents = TeacherConvertor.fromTeacherWithStudents(teacher);
        amqpTemplate.convertAndSend("teachers", "Get teacher " + teacher);
        return new ResponseEntity<>(teacherStudents, HttpStatus.OK);
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<TeacherWithStudentsAndRolesDTO> getStudentWithRole(@PathVariable long id){
        Teacher teacher = teacherService.getTeacher(id);
        TeacherWithStudentsAndRolesDTO teacherRoles = TeacherConvertor.fromTeacherWithStudentsAndRoles(teacher);
        amqpTemplate.convertAndSend("teachers","Get students and roles teacher " + teacher);
        return new ResponseEntity<>(teacherRoles,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Teacher> addNewTeacher(@RequestBody Teacher teacher) {
        teacherService.saveOrUpdateTeacher(teacher);
        amqpTemplate.convertAndSend("teachers", "Teacher was created " + teacher);
        return new ResponseEntity<>(teacher, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher) {
        teacherService.saveOrUpdateTeacher(teacher);
        amqpTemplate.convertAndSend("teachers", "Teacher was update " + teacher);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable long id) {
        teacherService.deleteTeacher(id);
        amqpTemplate.convertAndSend("teachers", "Teacher with ID = " + id + " was delete");
        return new ResponseEntity<>(String.format("Teacher with ID = %d was deleted", id), HttpStatus.OK);
    }


    @GetMapping("/students/{id}")
    public ResponseEntity<List> getAllStudentsFromTeacher(@PathVariable long id) {
        List<Student> teacherStudents = teacherService.getAllStudentsFromTeacher(id);
        List<StudentDTO> students = teacherStudents
                .stream()
                .map(StudentConvertor::fromStudent)
                .collect(Collectors.toList());
        amqpTemplate.convertAndSend("teachers", "List of students who study with a teacher with ID = " + id);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<String> addNewStudentToTeacher(@RequestHeader("teacher_id") long id,
                                                         @RequestBody Student student) {
        Teacher teacher = teacherService.getTeacher(id);
        student.setTeachers(studentService.getAllTeacherFromStudent(student.getStudentId()));
        teacher.addStudentToTeacher(student);
        teacherService.saveOrUpdateTeacher(teacher);
        amqpTemplate.convertAndSend("teachers"
                , "Added a student " + student + " to the teacher's student list with ID = " + id);
        return new ResponseEntity<>("Student with ID = " + student.getStudentId()
                + " was added", HttpStatus.OK);
    }
}
