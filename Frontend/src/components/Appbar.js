// src/components/Appbar.js
import React from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import MenuIcon from '@mui/icons-material/Menu';
import { Box, ThemeProvider } from '@mui/material'; // Import ThemeProvider
import blackAestheticTheme from '../theme/blackAestheticTheme'; // Import the theme

export default function Appbar() {
  return (
    <ThemeProvider theme={blackAestheticTheme}> {/* Apply the custom black aesthetic theme */}
      <Box sx={{ flexGrow: 1 }}>
        <AppBar position="static">
          <Toolbar>
            <IconButton
              size="large"
              edge="start"
              color="inherit" // Will use text.primary from dark mode
              aria-label="menu"
              sx={{ mr: 2 }}
            >
              <MenuIcon />
            </IconButton>
            <Typography
              variant="h3" // Will use Oswald font and specified weight/spacing
              component="div"
              sx={{ flexGrow: 1 }}
            >
              STUDENT MANAGEMENT SYSTEM
            </Typography>
            {/* You can add more modern navigation items here */}
          </Toolbar>
        </AppBar>
      </Box>
    </ThemeProvider>
  );
}