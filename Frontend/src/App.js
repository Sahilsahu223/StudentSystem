import React, { useState, useEffect } from 'react';
import StudentService from './service/StudentService';
import './App.css';

function App() {
    // Existing State
    const [students, setStudents] = useState([]);
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [address, setAddress] = useState('');

    // New state to manage editing
    const [editingRollNo, setEditingRollNo] = useState(null);

    useEffect(() => {
        fetchStudents();
    }, []);

    const fetchStudents = () => {
        StudentService.getAllStudents()
            .then(response => {
                setStudents(response.data);
            })
            .catch(error => {
                console.error("There was an error fetching the students:", error);
            });
    };

    // Resets the form and editing state
    const resetForm = () => {
        setName('');
        setEmail('');
        setAddress('');
        setEditingRollNo(null);
    };

    const handleFormSubmit = (event) => {
        event.preventDefault();
        const student = { name, email, address };

        // If editingRollNo is not null, we are updating an existing student
        if (editingRollNo) {
            StudentService.updateStudent(editingRollNo, student)
                .then(() => {
                    fetchStudents();
                    resetForm();
                })
                .catch(error => {
                    console.error("Error updating student:", error);
                });
        } else {
            // Otherwise, we are creating a new student
            StudentService.createStudent(student)
                .then(() => {
                    fetchStudents();
                    resetForm();
                })
                .catch(error => {
                    console.error("Error creating student:", error);
                });
        }
    };

    // This function is called when the "Edit" button is clicked
    const handleEdit = (student) => {
        setEditingRollNo(student.rollNo);
        setName(student.name);
        setEmail(student.email);
        setAddress(student.address);
    };

    const handleDelete = (rollNo) => {
        StudentService.deleteStudent(rollNo)
            .then(() => {
                fetchStudents();
            })
            .catch(error => {
                console.error("Error deleting student:", error);
            });
    };

    return (
        <div className="container">
            <h1>🎓 Student Management System</h1>

            <div className="form-container">
                {/* Change form title based on whether we are editing or adding */}
                <h2>{editingRollNo ? 'Update Student' : 'Add New Student'}</h2>
                <form onSubmit={handleFormSubmit}>
                    <input
                        type="text"
                        placeholder="Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                    <input
                        type="text"
                        placeholder="Address"
                        value={address}
                        onChange={(e) => setAddress(e.target.value)}
                        required
                    />
                    {/* Change button text and add a cancel button if editing */}
                    <div className="form-buttons">
                        <button type="submit">{editingRollNo ? 'Update' : 'Add Student'}</button>
                        {editingRollNo && (
                            <button type="button" className="cancel-btn" onClick={resetForm}>
                                Cancel
                            </button>
                        )}
                    </div>
                </form>
            </div>

            <div className="student-list">
                <h2>Student List</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Roll No</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Address</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {students.map(student => (
                            <tr key={student.rollNo}>
                                <td>{student.rollNo}</td>
                                <td>{student.name}</td>
                                <td>{student.email}</td>
                                <td>{student.address}</td>
                                <td className="action-buttons">
                                    {/* Edit Button */}
                                    <button className="edit-btn" onClick={() => handleEdit(student)}>
                                        Edit
                                    </button>
                                    {/* Delete Button */}
                                    <button className="delete-btn" onClick={() => handleDelete(student.rollNo)}>
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
}

export default App;