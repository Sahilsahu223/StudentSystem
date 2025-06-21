package com.sahilsahu.StudentSystem.service;

import java.util.List;

import com.sahilsahu.StudentSystem.model.Student;

public interface StudentService {
    public Student saveStudent(Student student);
    public List<Student> getAllStudents();
}
