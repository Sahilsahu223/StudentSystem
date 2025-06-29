package com.sahilsahu.StudentSystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahilsahu.StudentSystem.model.Student;
import com.sahilsahu.StudentSystem.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Student sampleStudent;

    @BeforeEach
    void setup() {
        sampleStudent = new Student();
        sampleStudent.setId(1);
        sampleStudent.setName("Sahil");
        sampleStudent.setEmail("sahil@example.com");
        sampleStudent.setAddress("India");
    }

    // ✅ 1. Test Create Student (POST)
    @Test
    void testAddStudent() throws Exception {
        when(studentRepository.save(any(Student.class))).thenReturn(sampleStudent);

        mockMvc.perform(post("/api/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleStudent)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Sahil"));
    }

    // ✅ 2. Test Get All Students (GET)
    @Test
    void testGetAllStudents() throws Exception {
        when(studentRepository.findAll()).thenReturn(List.of(sampleStudent));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].email").value("sahil@example.com"));
    }

    // ✅ 3. Test Get Student by ID (GET)
    @Test
    void testGetStudentById() throws Exception {
        when(studentRepository.findById(1)).thenReturn(Optional.of(sampleStudent));

        mockMvc.perform(get("/api/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address").value("India"));
    }

    // ✅ 4. Test Update Student (PUT)
    @Test
    void testUpdateStudent() throws Exception {
        when(studentRepository.findById(1)).thenReturn(Optional.of(sampleStudent));
        when(studentRepository.save(any(Student.class))).thenReturn(sampleStudent);

        sampleStudent.setName("Updated Sahil");

        mockMvc.perform(put("/api/student/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleStudent)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Sahil"));
    }

    // ✅ 5. Test Delete Student (DELETE)
    @Test
    void testDeleteStudent() throws Exception {
        when(studentRepository.existsById(1)).thenReturn(true);

        mockMvc.perform(delete("/api/student/1"))
                .andExpect(status().isNoContent());
    }
}
