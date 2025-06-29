package com.sahilsahu.StudentSystem;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sahilsahu.StudentSystem.model.Student;
import com.sahilsahu.StudentSystem.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;

    @BeforeEach
    void setup() {
        studentRepository.deleteAll();  // reset db
        student = new Student();
        student.setName("Sahil");
        student.setEmail("sahil@example.com");
        student.setAddress("India");
        student = studentRepository.save(student); // save to H2 DB
    }

    @Test
    void testCreateStudent() throws Exception {
        Student newStudent = new Student();
        newStudent.setName("Aman");
        newStudent.setEmail("aman@example.com");
        newStudent.setAddress("Delhi");

        mockMvc.perform(post("/api/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newStudent)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email", is("aman@example.com")));
    }

    @Test
    void testGetAllStudents() throws Exception {
        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Sahil")));
    }

    @Test
    void testGetStudentById() throws Exception {
        mockMvc.perform(get("/api/student/" + student.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.address", is("India")));
    }

    @Test
    void testUpdateStudent() throws Exception {
        student.setName("Updated Name");

        mockMvc.perform(put("/api/student/" + student.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Updated Name")));
    }

    @Test
    void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/api/student/" + student.getId()))
                .andExpect(status().isNoContent());

        Optional<Student> deleted = studentRepository.findById(student.getId());
        assert (deleted.isEmpty());
    }
}
