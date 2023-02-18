import { LOCAL_URI, APPLICATION } from "../constants/Constant";
import axios from "axios";
import { setCookie, getCookie } from "./Cookie";
import { AUTH_TOKEN } from "../constants/Constant";
import store from "./Index";
import { authActions, productActions, snackBarActions } from "./Index";

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
      store.dispatch(authActions.isLoginClose());
      store.dispatch(authActions.checkLogin());
      commonSnackBar({ msg: "LOGIN SUCCESS", type: "success" });
    })
    .catch((err) =>
      commonSnackBar({ msg: err.response.data.errorCode, type: "error" })
    );
}

export function getProductList() {
  axios
    .get(`${LOCAL_URI}/product-service/sellProductList`)
    .then((res) => {
      store.dispatch(productActions.getProductList(res.data.responseData));
      commonSnackBar({ msg: "LOAD SUCCESS", type: "success" });
    })
    .catch((err) =>
      commonSnackBar({ msg: "FAIL LOAD PRODUCTLIST", type: "error" })
    );
}

export function getProductInfoHandler(productId) {
  axios
    .get(`${LOCAL_URI}/product-service/getProductInfo`, {
      params: { productId: productId },
    })
    .then((res) => {
      store.dispatch(productActions.getProductInfo(res.data.responseData));
      store.dispatch(productActions.showOrderModal());
    })
    .catch((err) =>
      commonSnackBar({ msg: err.response.data.errorCode, type: "error" })
    );
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
      store.dispatch(productActions.closeOrderModal());
      commonSnackBar({ msg: "ORDER SUCCESS", type: "success" });
    })
    .catch((err) =>
      commonSnackBar({ msg: err.response.data.errorCode, type: "error" })
    );
}

export function getMyorderList() {
  axios
    .get(`${LOCAL_URI}/user-service/getUserOrderList`, {
      headers: {
        account_token: getCookie(AUTH_TOKEN),
        "API-VERSION": 1,
        "Content-Type": APPLICATION,
      },
    })
    .then((res) => {
      console.log(res.data.responseData);
      store.dispatch(productActions.getMyOrderList(res.data.responseData));
      store.dispatch(productActions.showMyOrderList());
      commonSnackBar({ msg: "LOAD SUCCESS", type: "success" });
    })
    .catch((err) =>
      commonSnackBar({ msg: err.response.data.errorCode, type: "error" })
    );
}

export function commonSnackBar(data) {
  store.dispatch(snackBarActions.showSnackBar(data));
}
