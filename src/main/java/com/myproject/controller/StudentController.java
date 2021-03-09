package com.myproject.controller;

import com.myproject.model.Student;
import com.myproject.model.StudentCreateOrUpdateResponce;
import com.myproject.model.StudentRequest;
import com.myproject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/students")
    public List<Student> getStudents(@RequestParam(required = false) String name,
                                     @RequestParam(required = false) String lastName,
                                     @RequestParam(required = false) Integer minAge,
                                     @RequestParam(required = false) Integer maxAge,
                                     @RequestParam(required = false) Double minRating,
                                     @RequestParam(required = false) Double maxRating,
                                     @RequestParam(required = false) String mail) {
        StudentRequest studentRequest = new StudentRequest(name, lastName, minAge, maxAge, minRating, maxRating, mail);
        return studentService.getStudents(studentRequest);
    }

    @GetMapping(value = "/get_student")
    public ResponseEntity<?> getStudent(@RequestParam String id) {
        if (!isNumeric(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id");

        Student student = studentService.getStudent(id);
        if (student == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No student with such id");

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @PostMapping(value = "/create_student")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        StudentCreateOrUpdateResponce studentCreateOrUpdateResponce = studentService.canCreateOrUpdateStudent(student);


        if (studentCreateOrUpdateResponce.isCan()) {
            Student createdStudent = studentService.createStudent(student);
            if (createdStudent != null)
                return ResponseEntity.status(HttpStatus.OK).body(createdStudent);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No duplicated students allowed");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(studentCreateOrUpdateResponce.toString());
    }

    @PostMapping(value = "/update_student")
    public ResponseEntity<?> updateStudent(@RequestParam String id,
                                           @RequestBody Student student) {
        if (!isNumeric(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id");

        StudentCreateOrUpdateResponce studentCreateOrUpdateResponce = studentService.canCreateOrUpdateStudent(student);
        if (!studentCreateOrUpdateResponce.isCan())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(studentCreateOrUpdateResponce.toString());

        student.setId(Long.parseLong(id));
        Student updatedStudent = studentService.updateStudent(student);
        if (updatedStudent == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
    }

    @DeleteMapping(value = "/delete_student")
    public ResponseEntity<?> deleteStudent(@RequestParam String id) {
        if (!isNumeric(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id");

        Student deletedStudent = studentService.deleteStudent(id);
        if (deletedStudent == null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No student witch such id");

        return ResponseEntity.status(HttpStatus.OK).body(deletedStudent);
    }
}
