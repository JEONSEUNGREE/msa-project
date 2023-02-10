import * as React from "react";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import IconButton from "@mui/material/IconButton";
import MenuIcon from "@mui/icons-material/Menu";

import { useDispatch, useSelector } from "react-redux";
import { authActions } from "../store/index";
import { getCookie, removeCookie } from "../store/cookie";
import { AUTH_TOKEN } from "../constants/constant";

export default function ButtonAppBar() {
  const isAuth = useSelector((state) => state.auth.isAuth);
  const dispatch = useDispatch();

  const signupHandler = (event) => {
    event.preventDefault();
    dispatch(authActions.isSignupShow());
  };

  const loginHandler = (event) => {
    event.preventDefault();
    dispatch(authActions.isLoginShow());
  };

  const logoutHandler = (event) => {
    event.preventDefault();
    removeCookie(AUTH_TOKEN);
  };

  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
          <IconButton
            size="large"
            edge="start"
            color="inherit"
            aria-label="menu"
            sx={{ mr: 2 }}
          >
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
            News
          </Typography>
          <Button color="inherit" onClick={signupHandler}>
            Signup
          </Button>
          {!isAuth && (
            <Button color="inherit" onClick={loginHandler}>
              Login
            </Button>
          )}
          {isAuth && (
            <Button color="inherit" onClick={logoutHandler}>
              Logout
            </Button>
          )}
        </Toolbar>
      </AppBar>
    </Box>
  );
}
