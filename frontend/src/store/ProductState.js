import { createSlice } from "@reduxjs/toolkit";

const initalDrawerState = {
  productList: [],
};

const productSlice = createSlice({
  name: "product",
  initialState: initalDrawerState,
  reducers: {
    getProductList(state, data) {
      state.productList = data.payload;
      console.log(data.payload);
    },
  },
});

export default productSlice;
