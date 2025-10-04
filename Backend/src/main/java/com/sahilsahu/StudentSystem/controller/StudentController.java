package com.sahilsahu.StudentSystem.controller;

import com.sahilsahu.StudentSystem.exception.StudentNotFoundException;
import com.sahilsahu.StudentSystem.model.Student;
import com.sahilsahu.StudentSystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")  // Optional: groups all endpoints under /api
@CrossOrigin(origins = {"http://localhost:3000", "https://your-future-netlify-app-url.netlify.app"})
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // POST /api/student
    @PostMapping("/student")
    @ResponseStatus(HttpStatus.CREATED)
    public Student newStudent(@RequestBody Student newStudent) {
        return studentRepository.save(newStudent);
    }

    // GET /api/students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // GET /api/student/{id}
    @GetMapping("/student/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
    }

    // PUT /api/student/{id}
    @PutMapping("/student/{id}")
    public Student updateStudent(@RequestBody Student newStudent, @PathVariable int id) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setName(newStudent.getName());
                    student.setEmail(newStudent.getEmail());
                    student.setAddress(newStudent.getAddress());
                    return studentRepository.save(student);
                }).orElseThrow(() -> new StudentNotFoundException(id));
    }

    // DELETE /api/student/{id}
    @DeleteMapping("/student/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }
}

