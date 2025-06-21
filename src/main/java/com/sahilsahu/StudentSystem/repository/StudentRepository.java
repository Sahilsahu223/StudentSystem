package com.sahilsahu.StudentSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sahilsahu.StudentSystem.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
}
