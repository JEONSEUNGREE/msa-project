import { LOCAL_URI, APPLICATION } from "../constants/constant";
import axios from "axios";
import { setCookie } from "./cookie";
import { AUTH_TOKEN } from "../constants/constant";
import store from "./index";
import { productActions } from "../store/index";

export function userLoginHandler(payload) {
  axios
    .post(
      `${LOCAL_URI}/user-service/login`,
      {
        userId: payload.userId,
        password: payload.pw,
      },
      {
        headers: {
          "API-VERSION": 1,
          "Content-Type": APPLICATION,
        },
      }
    )
    .then((res) => {
      console.log(res.headers.account_token);
      setCookie(AUTH_TOKEN, res.headers.account_token, {});
    })
    .catch((err) => console.log(err));
}

export function getProductList() {
  axios
    .get(`${LOCAL_URI}/product-service/sellProductList`)
    .then((res) => {
      store.dispatch(productActions.getProductList(res.data.responseData));
    })
    .catch((err) => console.log(err));
}
