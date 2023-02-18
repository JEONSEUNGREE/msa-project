import * as React from "react";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import Grid from "@mui/material/Unstable_Grid2";
import img1 from "../bus_config_img/1.png";
import img2 from "../bus_config_img/2.png";
import img3 from "../bus_config_img/3.png";
import img4 from "../bus_config_img/4.png";
import img5 from "../bus_config_img/5.png";
import img6 from "../bus_config_img/6.png";
import img7 from "../bus_config_img/7.png";
import img8 from "../bus_config_img/8.png";
import img9 from "../bus_config_img/9.png";

const imgList = [
  {
    uri: img1,
  },
  {
    uri: img2,
  },
  {
    uri: img3,
  },
  {
    uri: img4,
  },
  {
    uri: img5,
  },
  {
    uri: img6,
  },
  {
    uri: img7,
  },
  {
    uri: img8,
  },
  {
    uri: img9,
  },
];

export default function ResponsiveGrid() {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <Grid
        container
        spacing={{ xs: 2, md: 3 }}
        columns={{ xs: 4, sm: 8, md: 12 }}
      >
        {imgList.map((item, index) => (
          <Grid xs={2} sm={2} md={6} key={index}>
            <Box sx={{ "text-align": "center" }}>
              <Typography variant="h5" sx={{ "font-family": "Neucha" }}>
                {index + 1}
              </Typography>
            </Box>
            <img src={item.uri} alt="default" width={700} />
          </Grid>
        ))}
      </Grid>
    </Box>
  );
}
