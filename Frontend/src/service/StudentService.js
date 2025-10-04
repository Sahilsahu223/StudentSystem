import axios from 'axios';

// The base URL of your Spring Boot API
const API_BASE_URL = "http://localhost:8080/api";

class StudentService {

    // Method to get all students
    getAllStudents() {
        return axios.get(API_BASE_URL + "/students");
    }

    // Method to create a new student
    createStudent(student) {
        return axios.post(API_BASE_URL + "/students", student);
    }

    // Method to get a single student by roll number
    getStudentByRollNo(rollNo) {
        return axios.get(API_BASE_URL + "/students/" + rollNo);
    }

    // Method to update a student
    updateStudent(rollNo, studentDetails) {
        return axios.put(API_BASE_URL + "/students/" + rollNo, studentDetails);
    }

    // Method to delete a student
    deleteStudent(rollNo) {
        return axios.delete(API_BASE_URL + "/students/" + rollNo);
    }
}

// Export an object of the class
export default new StudentService();