// src/components/Student.js
import React, { useEffect, useState } from 'react';
import {
  Container,
  Paper,
  TextField,
  Button,
  Box,
  Typography,
  ThemeProvider, // Import ThemeProvider
  CssBaseline,
  Divider,
} from '@mui/material';
import { styled } from '@mui/system';
import blackAestheticTheme from '../theme/blackAestheticTheme'; // Import the theme

// Using styled utility for the main Paper container
const StyledUnifiedPaper = styled(Paper)(({ theme }) => ({
  padding: theme.spacing(6),
  width: 600,
  margin: theme.spacing(4, 'auto'),
  // Adjust for responsiveness
  [theme.breakpoints.down('sm')]: {
    width: '90%',
    padding: theme.spacing(3),
  },
}));

const FormContainer = styled(Box)(({ theme }) => ({
  display: 'flex',
  flexDirection: 'column',
  gap: theme.spacing(3), // Generous gap for readability
}));

export default function Student() {
  const [name, setName] = useState('');
  const [address, setAddress] = useState('');
  const [students, setStudents] = useState([]);

  const handleClick = async (e) => {
    e.preventDefault();
    const student = { name, address };

    try {
      const response = await fetch("http://localhost:8080/student/add", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(student)
      });

      if (response.ok) {
        alert("New Student added successfully!");
        setName('');
        setAddress('');
        fetchStudents();
      } else {
        const errorData = await response.json();
        alert(`Failed to add student: ${errorData.message || 'Unknown error'}`);
      }
    } catch (error) {
      alert("Could not connect to the server.");
    }
  };

  const fetchStudents = async () => {
    try {
      const response = await fetch("http://localhost:8080/student/getAll");
      const result = await response.json();
      setStudents(result);
    } catch (error) {
      alert("Failed to load student list.");
    }
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  return (
    <ThemeProvider theme={blackAestheticTheme}> {/* Apply the custom black aesthetic theme */}
      <CssBaseline /> {/* Applies baseline CSS rules for consistency */}
      <Container maxWidth="md">
        <StyledUnifiedPaper elevation={3}>
          {/* Add Student Section */}
          <Typography variant="h4" component="h1" sx={{ mb: 4, textAlign: 'center', color: 'secondary.main' }}>
            ADD NEW STUDENT
          </Typography>

          <FormContainer noValidate autoComplete="off">
            <TextField
              id="student-name"
              label="Student Name"
              variant="outlined"
              fullWidth
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
            <TextField
              id="student-address"
              label="Student Address"
              variant="outlined"
              fullWidth
              value={address}
              onChange={(e) => setAddress(e.target.value)}
            />
            <Button variant="contained" color="secondary" onClick={handleClick} sx={{ mt: 2, alignSelf: 'flex-end' }}>
              SUBMIT
            </Button>
          </FormContainer>

          <Divider sx={{ my: 6 }} /> {/* Visual divider between sections */}

          {/* Students List Section */}
          <Typography variant="h4" component="h2" sx={{ mb: 4, textAlign: 'center', color: 'secondary.main' }}>
            REGISTERED STUDENTS
          </Typography>

          {students.length > 0 ? (
            <Box sx={{ display: 'flex', flexDirection: 'column', gap: blackAestheticTheme.spacing(2) }}>
              {students.map((student) => (
                <Paper
                  elevation={4} // Slightly more elevation for individual cards
                  key={student.id}
                  sx={{
                    padding: blackAestheticTheme.spacing(2.5),
                    textAlign: 'left',
                    borderLeft: `4px solid ${blackAestheticTheme.palette.secondary.main}`, // Teal accent border
                    borderRadius: '6px', // Slight rounding
                    '&:hover': {
                        boxShadow: '0px 0px 20px rgba(3, 218, 198, 0.2)', // Teal glow on hover
                        transform: 'translateY(-2px)',
                        transition: 'all 0.2s ease-in-out',
                    }
                  }}
                >
                  <Typography variant="body1" sx={{ fontWeight: 600, color: 'text.primary' }}>ID: {student.id}</Typography>
                  <Typography variant="body2" color="text.secondary">NAME: {student.name}</Typography>
                  <Typography variant="body2" color="text.secondary">ADDRESS: {student.address}</Typography>
                </Paper>
              ))}
            </Box>
          ) : (
            <Typography variant="body1" textAlign="center" color="text.secondary" sx={{ mt: 4 }}>NO STUDENTS FOUND. ADD ONE ABOVE!</Typography>
          )}
        </StyledUnifiedPaper>
      </Container>
    </ThemeProvider>
  );
}