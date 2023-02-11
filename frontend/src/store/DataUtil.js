import { LOCAL_URI, APPLICATION } from "../constants/constant";
import axios from "axios";
import { setCookie, getCookie } from "./cookie";
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

export function getProductInfoHandler(productId) {
  axios
    .get(`${LOCAL_URI}/product-service/getProductInfo`, {
      params: { productId: productId },
    })
    .then((res) => {
      console.log(res.data.responseData);
      store.dispatch(productActions.getProductInfo(res.data.responseData));
      store.dispatch(productActions.showOrderModal());
    })
    .catch((err) => console.log(err));
}

export function orderProductHandler(data) {
  axios
    .post(
      `${LOCAL_URI}/order-service/order`,
      {
        productId: data.productId,
        productName: data.productName,
        categoryId: "TEMP",
        qty: data.qty,
      },
      {
        headers: {
          account_token: getCookie(AUTH_TOKEN),
          "API-VERSION": 1,
          "Content-Type": APPLICATION,
        },
      }
    )
    .then((res) => {
      store.dispatch(productActions.getProductInfo(res.data.responseData));
      store.dispatch(productActions.showOrderModal());
      alert("ORDER SUCCESS");
    })
    .catch((err) => {
      alert(err.response.data.message);
    });
}
