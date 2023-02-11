import { createSlice } from "@reduxjs/toolkit";
import { getProductInfoHandler } from "./DataUtil";

const initalDrawerState = {
  productList: [],
  showOrderModal: false,
  albumInfo: [],
  nowProductId: "",
};

const productSlice = createSlice({
  name: "product",
  initialState: initalDrawerState,
  reducers: {
    getProductList(state, data) {
      state.productList = data.payload;
    },
    getProductInfo(state, data) {
      state.albumInfo = data.payload;
    },
    showOrderModal(state) {
      state.showOrderModal = true;
    },
    closeOrderModal(state) {
      state.showOrderModal = false;
    },
  },
});

export default productSlice;
