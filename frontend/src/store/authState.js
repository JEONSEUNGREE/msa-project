import { createSlice } from "@reduxjs/toolkit";
import { getCookie } from "../store/cookie";
import { AUTH_TOKEN } from "../constants/constant";

const initalAuthState = {
  isLogin: false,
  isSignup: false,
  isAuth: getCookie(AUTH_TOKEN) != null ? true : false,
};

const authSlice = createSlice({
  name: "auth",
  initialState: initalAuthState,
  reducers: {
    isSignupClose(state) {
      state.isSignup = false;
    },
    isSignupShow(state) {
      state.isSignup = true;
    },
    isLoginShow(state) {
      state.isLogin = true;
    },
    isLoginClose(state) {
      state.isLogin = false;
    },
  },
});

export default authSlice;
