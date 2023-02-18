import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import Bar from "./layout/Appbar";
import reportWebVitals from "./reportWebVitals";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import SignupPage from "./auth/SignupPage";
import LoginPage from "./auth/LoginPage";
import Drawer from "./layout/Drawer";
import SnackBar from "./layout/SnackBar";
import SpeedDial from "./layout/SpeedDial";
import SpringBusConfig from "./layout/SpringBusConfig";
import Home from "./main/Home";
import ProductOrder from "./main/ProductOrder";
import Config from "./main/Config";
import OrderList from "./main/OrderList";
import { Provider } from "react-redux";
import store from "./store/Index";
import { CookiesProvider } from "react-cookie";
import Grid from "./layout/Grid";

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <CookiesProvider>
    <Provider store={store}>
      <BrowserRouter>
        <React.StrictMode>
          <Bar />
          <Drawer />
          <SignupPage />
          <LoginPage />
          <ProductOrder />
          <OrderList />
          <SnackBar />
          <SpringBusConfig />

          <Switch>
            <Route path="/" exact>
              <Home />
            </Route>
            <Route path="/product" exact>
              <Grid />
            </Route>
            <Route path="/config" exact>
              <Config />
            </Route>
          </Switch>
          <SpeedDial />
        </React.StrictMode>
      </BrowserRouter>
    </Provider>
  </CookiesProvider>
);

reportWebVitals();
