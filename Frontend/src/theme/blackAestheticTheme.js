// src/theme/blackAestheticTheme.js
import { createTheme } from '@mui/material/styles';

const blackAestheticTheme = createTheme({
  palette: {
    mode: 'dark', // Crucial for dark mode components
    primary: {
      main: '#6200EE', // A deep, vibrant purple for primary actions/branding
      // You could also consider a dark blue like #00BFFF (Deep Sky Blue)
    },
    secondary: {
      main: '#03DAC6', // A bright teal/cyan for secondary actions/accents
    },
    background: {
      default: '#121212', // Very dark grey, almost black
      paper: '#1E1E1E',   // Slightly lighter dark grey for cards/containers
    },
    text: {
      primary: '#E0E0E0', // Light grey for main text
      secondary: '#B0B0B0', // Slightly darker grey for secondary text
      disabled: '#616161',
    },
    error: {
      main: '#CF6679', // A muted red for errors
    },
    warning: {
      main: '#FFCC80', // A soft orange for warnings
    },
    info: {
      main: '#2196F3', // Standard blue for info
    },
    success: {
      main: '#81C784', // Green for success
    },
    divider: '#3A3A3A', // Darker divider for contrast
  },
  typography: {
    fontFamily: ['"Montserrat"', 'sans-serif'].join(','),
    h1: { fontFamily: '"Oswald", sans-serif', fontWeight: 600, letterSpacing: '0.05em' },
    h2: { fontFamily: '"Oswald", sans-serif', fontWeight: 600, letterSpacing: '0.04em' },
    h3: { fontFamily: '"Oswald", sans-serif', fontWeight: 600, letterSpacing: '0.03em' },
    h4: { fontFamily: '"Oswald", sans-serif', fontWeight: 600, letterSpacing: '0.02em' },
    h5: { fontFamily: '"Oswald", sans-serif', fontWeight: 500, letterSpacing: '0.01em' },
    h6: { fontFamily: '"Oswald", sans-serif', fontWeight: 500, letterSpacing: '0.01em' },
    button: {
      textTransform: 'uppercase', // Often used in dark/tech themes for buttons
      fontWeight: 700,
      letterSpacing: '0.08em',
    },
  },
  spacing: 8, // Standard Material-UI spacing unit
  components: {
    MuiAppBar: {
      styleOverrides: {
        root: {
          backgroundColor: '#1E1E1E', // Match paper background
          boxShadow: 'none', // Flat look
          borderBottom: '1px solid #3A3A3A', // Subtle separation
        },
      },
    },
    MuiPaper: {
      styleOverrides: {
        root: {
          boxShadow: '0px 0px 15px rgba(0, 0, 0, 0.4)', // Slightly stronger, diffused glow
          borderRadius: '10px', // Modern rounded corners
          border: '1px solid #3A3A3A', // Subtle border for definition
        },
      },
    },
    MuiTextField: {
      styleOverrides: {
        root: {
          '& .MuiOutlinedInput-root': {
            borderRadius: '8px',
            backgroundColor: '#2A2A2A', // Slightly lighter input background
            '& fieldset': {
              borderColor: '#4A4A4A', // Darker border
            },
            '&:hover fieldset': {
              borderColor: '#6002EE', // Primary color on hover
            },
            '&.Mui-focused fieldset': {
              borderColor: '#BB86FC', // Lighter primary for focus glow
              borderWidth: '2px',
            },
          },
          '& .MuiInputLabel-root': {
            color: '#B0B0B0', // Label color
          },
          '& .MuiInputLabel-root.Mui-focused': {
            color: '#BB86FC', // Label color on focus
          },
          '& .MuiInputBase-input': {
            color: '#E0E0E0', // Input text color
          }
        },
      },
    },
    MuiButton: {
      styleOverrides: {
        root: {
          fontWeight: 700,
          letterSpacing: '0.08em',
          borderRadius: '6px', // Slightly less rounded for buttons
        },
        containedPrimary: {
          backgroundColor: '#BB86FC', // Lighter primary for button for better contrast
          color: '#121212', // Dark text on light button
          '&:hover': {
            backgroundColor: '#9A67EA',
            boxShadow: '0px 4px 15px rgba(187, 134, 252, 0.3)', // Subtle glow
          },
        },
        containedSecondary: {
          backgroundColor: '#03DAC6', // Teal accent
          color: '#121212',
          '&:hover': {
            backgroundColor: '#03C7B5',
            boxShadow: '0px 4px 15px rgba(3, 218, 198, 0.3)', // Subtle glow
          },
        },
      },
    },
    MuiDivider: {
      styleOverrides: {
        root: {
          borderColor: '#4A4A4A', // Clearly visible divider on dark background
        },
      },
    },
    MuiTypography: {
      styleOverrides: {
        gutterBottom: {
          marginBottom: '1rem', // Default spacing for Typography with gutterBottom
        }
      }
    }
  },
});

export default blackAestheticTheme;