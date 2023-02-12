import React, { useState } from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { LOCAL_URI } from "../constants/constant";
import { useDispatch } from "react-redux";
import { getProductInfoHandler } from "../store/DataUtil";

export default function ImgMediaCard(props) {
  const [prodId, setProdId] = useState(props.productInfo.productId);

  const dispatch = useDispatch();

  const handleOrderModal = (event) => {
    event.preventDefault();
    getProductInfoHandler(prodId);
  };

  return (
    <Card>
      <CardMedia
        component="img"
        alt="green iguana"
        height="340"
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
          <br />
          QTY : {props.productInfo.qty}
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="small" onClick={handleOrderModal}>
          BUY
        </Button>
        <Button size="small">DESC</Button>
      </CardActions>
    </Card>
  );
}
