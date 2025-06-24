package com.sahilsahu.StudentSystem;

import com.sahilsahu.StudentSystem.controller.StudentController;
import com.sahilsahu.StudentSystem.model.Student;
import com.sahilsahu.StudentSystem.repository.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Student student;

    @BeforeAll
    void setup() {
        student = new Student();
        student.setId(1);
        student.setName("Sahil");
        student.setEmail("sahil@example.com");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Running test...");
    }

    @Test
    void shouldReturnStudentById() throws Exception {
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Sahil"));
    }


    @Test
    void shouldAddNewStudent() throws Exception {
        // Mock repository behavior
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        // Perform POST request and verify response
        mockMvc.perform(get("/students") // Correct endpoint path
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(student)))
                .andExpect(status().isCreated()); // Make sure your controller has @ResponseStatus(HttpStatus.CREATED}
    }

    @Test
    void shouldDeleteStudentSuccessfully() throws Exception {
        // Mock studentRepository to simulate that the student exists
        when(studentRepository.existsById(1)).thenReturn(true);
        doNothing().when(studentRepository).deleteById(1);

        mockMvc.perform(delete("/students/1"))
                .andExpect(status().isNoContent());
    }
    @Test
    void shouldReturn404IfStudentNotFound() throws Exception {
        when(studentRepository.findById(99)).thenReturn(Optional.empty());

        mockMvc.perform(get("/student/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Student not found"));
    }



    @AfterEach
    void afterEach() {
        System.out.println("Test completed.");
    }

    @AfterAll
    void tearDown() {
        System.out.println("All tests done.");
    }
}

