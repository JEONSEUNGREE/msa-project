import * as React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { LOCAL_URI } from "../constants/constant";

export default function ImgMediaCard(props) {
  console.log(props);
  return (
    <Card sx={{ maxWidth: 345 }}>
      <CardMedia
        component="img"
        alt="green iguana"
        height="350"
        crossorigin
        image={LOCAL_URI + props.productInfo.productThumbnail}
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {props.productInfo.productName}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          STATUS : {props.productInfo.status}
          <br />
          PRICE : {props.productInfo.productPrice}
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="small">Share</Button>
        <Button size="small">Learn More</Button>
      </CardActions>
    </Card>
  );
}
