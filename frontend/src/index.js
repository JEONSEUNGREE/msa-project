import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import Bar from "./layout/appbar";
import reportWebVitals from "./reportWebVitals";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import SignupPage from "./auth/SignupPage";
import LoginPage from "./auth/loginPage";
import Login from "./auth/Login";
import { Provider } from "react-redux";
import store from "./store/index";
import { CookiesProvider } from "react-cookie";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <CookiesProvider>
    <Provider store={store}>
      <BrowserRouter>
        <React.StrictMode>
          <Bar />
          <SignupPage />
          <LoginPage />
          <Switch>
            <Route path="/login" exact>
              <Login />
            </Route>
          </Switch>
        </React.StrictMode>
      </BrowserRouter>
    </Provider>
  </CookiesProvider>
);

reportWebVitals();
