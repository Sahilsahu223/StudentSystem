package com.sahilsahu.StudentSystem.controller;

import com.sahilsahu.StudentSystem.exception.StudentNotFoundException;
import com.sahilsahu.StudentSystem.model.Student;
import com.sahilsahu.StudentSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("http://localhost:3000")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/student")
    Student newStudent(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);
    }
    @GetMapping("/students")
    List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @GetMapping("/student/{id}")
    Student getStudentById(@PathVariable Long id) {
        return studentRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @PutMapping("/student/{id}")
    Student updateStudent(@RequestBody Student newStudent, @PathVariable Long id) {
        return studentRepository.findById(Math.toIntExact(id))
                .map(user -> {
                    user.setAddress(newStudent.getAddress());
                    user.setName(newStudent.getName());
                    user.setEmail(newStudent.getEmail());
                    return studentRepository.save(user);
                }).orElseThrow(() -> new StudentNotFoundException(id));
    }

    @DeleteMapping("/student/{id}")
    String deleteUser(@PathVariable Long id){
        if(!studentRepository.existsById(Math.toIntExact(id))){
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(Math.toIntExact(id));
        return  "User with id "+id+" has been deleted success.";
    }



}
