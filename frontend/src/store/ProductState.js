import { createSlice } from "@reduxjs/toolkit";

const initalProductState = {
  productList: [],
  showOrderModal: false,
  albumInfo: [],
  nowProductId: "",

  showMyOrderList: false,
  myOrderList: [],
};

const productSlice = createSlice({
  name: "product",
  initialState: initalProductState,
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
    showMyOrderList(state) {
      state.showMyOrderList = true;
    },
    closeMyOrderList(state) {
      state.showMyOrderList = false;
    },
    getMyOrderList(state, data) {
      state.myOrderList = data.payload;
    },
    clearPrevOrder(state) {
      state.albumInfo = [];
      state.nowProductId = "";
    },
  },
});

export default productSlice;
