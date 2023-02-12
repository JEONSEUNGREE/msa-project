import React, { useState } from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import OrderTable from "./OrderTable";

import { useDispatch, useSelector } from "react-redux";
import { productActions } from "../store/Index";

export default function OrderList() {
  const showMyOrderList = useSelector((state) => state.product.showMyOrderList);
  const myOrderList = useSelector((state) => state.product.myOrderList);

  const dispatch = useDispatch();

  const closeMyOrderList = (event) => {
    event.preventDefault();
    dispatch(productActions.closeMyOrderList());
  };

  return (
    <div>
      <Dialog open={showMyOrderList} onClose={closeMyOrderList} maxWidth="350">
        <DialogTitle>MY ORDER LIST</DialogTitle>
        <DialogContent>
          <OrderTable myOrderList={myOrderList} />
        </DialogContent>
        <DialogActions>
          <Button onClick={closeMyOrderList}>Close</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
