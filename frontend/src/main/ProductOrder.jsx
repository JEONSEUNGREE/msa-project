import React, { useState, useEffect } from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogTitle from "@mui/material/DialogTitle";

import { useDispatch, useSelector } from "react-redux";
import { productActions } from "../store/Index";
import { orderProductHandler, getProductList } from "../store/DataUtil";

export default function ProductOrder() {
  const albumInfo = useSelector((state) => state.product.albumInfo);
  const showOrderModal = useSelector((state) => state.product.showOrderModal);
  const [qty, setQty] = useState("");

  useEffect(() => {
    getProductList();
    return () => {
      dispatch(productActions.clearPrevOrder);
      productActions.closeOrderModal();
    };
  }, [albumInfo]);

  const dispatch = useDispatch();

  const closeOrderModal = (event) => {
    event.preventDefault();
    dispatch(productActions.closeOrderModal());
  };

  const orderProduct = (event) => {
    let data = {
      productId: albumInfo.productId,
      productName: albumInfo.productName,
      qty: qty,
    };
    orderProductHandler(data);
  };

  return (
    <div>
      <Dialog open={showOrderModal} onClose={closeOrderModal}>
        <DialogTitle>ALBUM</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            name="ALBUM NAME"
            label="ALBUM NAME"
            fullWidth
            InputProps={{
              readOnly: true,
            }}
            variant="filled"
            value={albumInfo.productName || ""}
          />
          <TextField
            autoFocus
            margin="dense"
            id="QTY"
            label="QTY"
            value={qty || 0}
            onChange={(e) => {
              setQty(e.currentTarget.value);
            }}
            type="number"
            fullWidth
            variant="standard"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={orderProduct}>Buy</Button>
          <Button onClick={closeOrderModal}>Cancel</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
