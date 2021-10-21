package ru.digitalleague.ligastudent.ligastudent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.ligastudent.ligastudent.api.TeacherService;
import ru.digitalleague.ligastudent.ligastudent.model.Student;
import ru.digitalleague.ligastudent.ligastudent.model.Teacher;

import java.util.List;

@RestController
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("/teachers")
    public ResponseEntity<List> getAllStudents() {
        List<Teacher> teachers = teacherService.getAllTeacher();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<Teacher> getTeacher(@PathVariable long id) {
        Teacher teacher = teacherService.getTeacher(id);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @PostMapping("/teachers")
    public ResponseEntity<Teacher>  addNewTeacher(@RequestBody Teacher teacher) {
        teacherService.saveOrUpdateTeacher(teacher);
        return new ResponseEntity<>(teacher,HttpStatus.CREATED);
    }

    @PutMapping("/teachers")
    public ResponseEntity<Teacher> updateTeacher(@RequestBody Teacher teacher) {
        teacherService.saveOrUpdateTeacher(teacher);
        return new ResponseEntity<>(teacher, HttpStatus.OK);
    }

    @DeleteMapping("/teacher/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable long id) {
        teacherService.deleteTeacher(id);
        return new ResponseEntity<>(String.format("Teacher with ID = %d was deleted", id),HttpStatus.OK);
    }


    @GetMapping("/teachers-students/{id}")
    public ResponseEntity<List> getAllStudentsFromTacher(long id) {
        List<Student> teacherStudents = teacherService.getAllStudentsFromTeacher(id);
        return new ResponseEntity<>(teacherStudents, HttpStatus.OK);
    }

    @PostMapping("/teachers-students")
    public ResponseEntity<String> addNewStudentToTeacher(@RequestHeader("teacher_id") long id,
                                          @RequestBody Student student) {
        Teacher teacher = teacherService.getTeacher(id);
        teacher.addStudentToTeacher(student);
        return new ResponseEntity<>("student with id = " + student.getStudentId()
                + " was added", HttpStatus.OK);
    }
}
