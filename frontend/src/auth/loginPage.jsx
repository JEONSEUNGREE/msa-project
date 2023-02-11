import React, { useState } from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";

import { useDispatch, useSelector } from "react-redux";
import { authActions } from "../store/index";
import { userLoginHandler } from "../store/DataUtil";

export default function FormDialog() {
  const isLoginShow = useSelector((state) => state.auth.isLogin);
  const [userId, setUserId] = useState("");
  const [pw, setPw] = useState("");

  const dispatch = useDispatch();

  const handleClose = (event) => {
    event.preventDefault();
    dispatch(authActions.isLoginClose());
  };

  const loginSubmit = (evnet) => {
    let data = {
      userId: userId,
      pw: pw,
    };
    userLoginHandler(data);
  };

  return (
    <div>
      <Dialog open={isLoginShow} onClose={handleClose}>
        <DialogTitle>LOGIN</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Please enter your Id and Password
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="id"
            label="id"
            fullWidth
            value={userId}
            onChange={(e) => {
              setUserId(e.currentTarget.value);
            }}
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="pw"
            label="pw"
            type="password"
            value={pw}
            onChange={(e) => {
              setPw(e.currentTarget.value);
            }}
            fullWidth
            variant="standard"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={loginSubmit}>Login</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
