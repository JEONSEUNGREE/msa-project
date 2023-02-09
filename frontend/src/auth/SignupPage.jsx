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
import { LOCAL_URI } from "../constants/constant";
import axios from "axios";

export default function FormDialog() {
  const isSignupShow = useSelector((state) => state.auth.isSignup);
  const [userId, setUserId] = useState("");
  const [name, setName] = useState("");
  const [pw, setPw] = useState("");
  const [email, setEmail] = useState("");
  const [phoneNum, setPhoneNum] = useState("");

  const dispatch = useDispatch();

  const handleClose = (event) => {
    event.preventDefault();
    dispatch(authActions.isSignupClose());
  };

  const signupHandler = (event) => {
    axios
      .post(
        `${LOCAL_URI}/user-service/signup`,
        {
          id: userId,
          name: name,
          pw: pw,
          email: email,
          phoneNum: phoneNum,
        },
        {
          headers: {
            "API-VERSION": 1,
            "Content-Type": "application/json",
          },
        }
      )
      .then((res) => {
        dispatch(authActions.isSignupClose());
        console.log(res);
      })
      .catch((err) => console.log(err));
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
            value={name}
            onChange={(e) => {
              setName(e.currentTarget.value);
            }}
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="email"
            label="email"
            value={email}
            onChange={(e) => {
              setEmail(e.currentTarget.value);
            }}
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="pw"
            label="pw"
            value={pw}
            onChange={(e) => {
              setPw(e.currentTarget.value);
            }}
            type="password"
            fullWidth
            variant="standard"
          />
          <TextField
            autoFocus
            margin="dense"
            id="phoneNum"
            label="phoneNum"
            value={phoneNum}
            onChange={(e) => {
              setPhoneNum(e.currentTarget.value);
            }}
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
