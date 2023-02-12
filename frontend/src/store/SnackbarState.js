import { createSlice } from "@reduxjs/toolkit";

const initalSnackBarState = {
  snackBar: {
    showSnackBar: false,
    vertical: "bottom",
    horizontal: "center",
    msg: "DEFAULT MESSAGE",
    type: "",
  },
};

const snackBarSlice = createSlice({
  name: "snackBar",
  initialState: initalSnackBarState,
  reducers: {
    showSnackBar(state, data) {
      state.snackBar.showSnackBar = true;
      state.snackBar.msg =
        data.payload.msg != null && data.payload.msg !== ""
          ? data.payload.msg
          : "DEFAULT MESSAGE";
      state.snackBar.type =
        data.payload.type != null && data.payload.type !== ""
          ? data.payload.type
          : "success";
    },
    closeSnackBar(state) {
      state.snackBar.showSnackBar = false;
    },
  },
});

export default snackBarSlice;
