import { configureStore } from "@reduxjs/toolkit";

import authSlice from "./authState";
import drawerSlice from "./DrawerState";
import productSlice from "./ProductState";

const store = configureStore({
  reducer: { auth: authSlice.reducer, drawer: drawerSlice.reducer, product: productSlice.reducer },
});

export const authActions = authSlice.actions;
export const drawerActions = drawerSlice.actions;
export const productActions = productSlice.actions;

export default store;
