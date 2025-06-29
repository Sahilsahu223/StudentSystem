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
public class StudentSystemTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ObjectMapper objectMapper;

	private Student student;

	@BeforeEach
	void init() {
		studentRepository.deleteAll();  // reset DB
		student = new Student();
		student.setName("Sahil");
		student.setEmail("sahil@example.com");
		student.setAddress("India");
		student = studentRepository.save(student);
	}

	// ✅ Create (POST)
	@Test
	void testCreateStudentSuccess() throws Exception {
		Student newStudent = new Student();
		newStudent.setName("Test");
		newStudent.setEmail("test@example.com");
		newStudent.setAddress("Delhi");

		mockMvc.perform(post("/api/student")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(newStudent)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.email", is("test@example.com")));
	}



	// ✅ Delete and Verify DB Entry Gone
	@Test
	void testDeleteStudentValid() throws Exception {
		mockMvc.perform(delete("/api/student/" + student.getId()))
				.andExpect(status().isNoContent());

		Optional<Student> s = studentRepository.findById(student.getId());
		assert (s.isEmpty());
	}

	// ✅ Update and Validate Field Change
	@Test
	void testUpdateStudent() throws Exception {
		student.setName("Updated");
		student.setEmail("updated@example.com");

		mockMvc.perform(put("/api/student/" + student.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(student)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Updated"));
	}

	// ✅ Get All (ensure DB state)
	@Test
	void testGetAllStudents() throws Exception {
		mockMvc.perform(get("/api/students"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].email").value("sahil@example.com"));
	}
}

