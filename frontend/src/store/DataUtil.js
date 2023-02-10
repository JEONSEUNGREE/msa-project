import { LOCAL_URI, APPLICATION } from "../constants/constant";
import axios from "axios";
import { setCookie } from "./cookie";
import { AUTH_TOKEN } from "../constants/constant";

export function userLoginHandler(payload) {
  axios
    .post(
      `${LOCAL_URI}/user-service/signup`,
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
      let token = res.headers;
      setCookie(AUTH_TOKEN, token, {});
    })
    .catch((err) => console.log(err));
}
