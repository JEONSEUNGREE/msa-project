import * as React from "react";
import CssBaseline from "@mui/material/CssBaseline";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import Typography from "@mui/material/Typography";
import ConfigImg from "./ConfigImg";

export default function Config() {
  return (
    <React.Fragment>
      <CssBaseline />
      <Container maxWidth="xl">
        <Box
          sx={{
            height: "100vh",
          }}
        >
          <Box sx={{ "text-align": "center", "margin-top": 50 }}>
            <Typography variant="h3" sx={{ "font-family": "Neucha" }}>
              SPRING BUS CONFIG
            </Typography>
          </Box>
          <Box
            sx={{
              "margin-top": 30,
            }}
          >
            <ConfigImg />
          </Box>
        </Box>
      </Container>
    </React.Fragment>
  );
}
