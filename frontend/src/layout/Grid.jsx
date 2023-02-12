import { useEffect } from "react";
import { useSelector } from "react-redux";

import Box from "@mui/material/Box";
import Grid from "@mui/material/Unstable_Grid2";
import Product from "../main/Product";
import { getProductList } from "../store/DataUtil";

export default function ResponsiveGrid() {
  const productList = useSelector((state) => state.product.productList);

  useEffect(() => {
    getProductList();
    return () => {};
  }, []);

  return (
    <Box sx={{ flexGrow: 1 }} p={5}>
      <Grid
        container
        spacing={{ xs: 4, md: 4 }}
        columns={{ xs: 4, sm: 6, md: 12 }}
      >
        {productList.map((item, index) => (
          <Grid xs={3} sm={3} md={3} key={index}>
            <Product productInfo={item} />
          </Grid>
        ))}
      </Grid>
    </Box>
  );
}
