import * as React from "react";
import Typography from "@mui/material/Typography";
import Clock from "react-live-clock";

export default function HOME() {
  return (
    <Typography
      variant="h1"
      gutterBottom
      sx={{
        position: "absolute",
        left: "50%",
        transform: "translateX(-50%)",
        top: "400px",
      }}
    >
      <Clock format={"HH:mm:ss"} ticking={true} timezone={"Asia/Seoul"} />
    </Typography>
  );
}
