import { configureStore } from "@reduxjs/toolkit";

import authSlice from "./AuthState";
import drawerSlice from "./DrawerState";
import productSlice from "./ProductState";
import snackBarSlice from "./SnackbarState";

const store = configureStore({
  reducer: {
    auth: authSlice.reducer,
    drawer: drawerSlice.reducer,
    product: productSlice.reducer,
    snackBar: snackBarSlice.reducer,
  },
});

export const authActions = authSlice.actions;
export const drawerActions = drawerSlice.actions;
export const productActions = productSlice.actions;
export const snackBarActions = snackBarSlice.actions;

export default store;
