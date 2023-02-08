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

export default function FormDialog() {
  const isSignupShow = useSelector((state) => state.auth.isSignup);
  const [userId, setUserId] = useState("");
  const [userId, setUserId] = useState("");
  const [userId, setUserId] = useState("");
  const [userId, setUserId] = useState("");

  const dispatch = useDispatch();

  const handleClose = (event) => {
    event.preventDefault();
    dispatch(authActions.isSignupClose());
  };

  const signupHandler = (event) => {
    event.preventDefault();
    dispatch(authActions.signupHandler());
  };

  return (
    <div>
      <Dialog open={isSignupShow} onClose={handleClose}>
        <DialogTitle>Subscribe</DialogTitle>
        <DialogContent>
          <DialogContentText>
            To subscribe to this website, please enter your email address here.
            We will send updates occasionally.
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
            id="name"
            label="name"
            value={userId}
            onChange={(e) => {
              setUserId(e.currentTarget.value);
            }}
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="email"
            label="email"
            value={userId}
            onChange={(e) => {
              setUserId(e.currentTarget.value);
            }}
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="pw"
            label="pw"
            value={userId}
            onChange={(e) => {
              setUserId(e.currentTarget.value);
            }}
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="phoneNum"
            label="phoneNum"
            type="Number"
            fullWidth
            variant="standard"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={signupHandler}>Subscribe</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
