import { createSlice } from "@reduxjs/toolkit";

const initalDrawerState = {
  isDrawer: false,
};

const drawerSlice = createSlice({
  name: "drawer",
  initialState: initalDrawerState,
  reducers: {
    isDrawerOpen(state) {
      state.isDrawer = true;
    },
    isDrawerClose(state) {
      state.isDrawer = false;
    }
  },
});

export default drawerSlice;
