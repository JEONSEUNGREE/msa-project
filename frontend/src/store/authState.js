import { createSlice } from "@reduxjs/toolkit";
import { LOCAL_URI } from "../constants/constant";
import axios from "axios";

const initalAuthState = {
  isLogin: localStorage.getItem("account_token") != null ? true : false,
  isSignup: false,
};


const authSlice = createSlice({
  name: "auth",
  initialState: initalAuthState,
  reducers: {
    loginHandler(state, actions) {
      axios
        .post(
          `${LOCAL_URI}/user-service/signup`,
          {
            id: actions.payload.id,
            name: actions.payload.name,
            email: actions.payload.email,
            pw: actions.payload.pw,
          },
          {
            headers: {
              "API-VERSION": 1,
              "Content-Type": "application/json",
            },
          }
        )
        .then((res) => console.log(res))
        .catch((err) => console.log(err));
      state.isLogin = true;
      localStorage.setItem("account_tokgien", actions.payload);
    },
    logoutHandler(state, actions) {
      state.isLogin = false;
      localStorage.removeItem("account_token");
    },
    isSignupClose(state, actions) {
      state.isSignup = !state.isSignup;
    },
    isSignupShow(state) {
      state.isSignup = !state.isSignup;
      // axios.post(`${LOCAL_URI}/user-service/signup`,
      // {
      //     id: actions.payload.id,
      //     name: actions.payload.name,
      //     email: actions.payload.email,
      //     pw: actions.payload.pw,
      //     phoneNum: actions.payload.phoneNum,
      //     birthInfo: actions.payload.birthInfo,
      //     address: actions.payload.address,
      // },
      // {
      //     headers: {
      //         "Content-Type": 'application/json',
      //         'X-API-VERSION': 1,
      //     }
      // })
      // .then(res =>
      //     console.log(res)
      // )
      // .catch(err =>
      //     console.log(err)
      // );
    },
  },
});

export default authSlice;
