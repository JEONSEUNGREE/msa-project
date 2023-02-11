import { useEffect } from "react";
import { useSelector } from "react-redux";

import { experimentalStyled as styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Unstable_Grid2";
import Product from "../main/Product";
import { getProductList } from "../store/DataUtil";

const Item = styled(Paper)(({ theme }) => ({
  backgroundColor: theme.palette.mode === "dark" ? "#1A2027" : "#fff",
  ...theme.typography.body2,
  padding: theme.spacing(2),
  textAlign: "center",
  color: theme.palette.text.secondary,
}));

export default function ResponsiveGrid() {
  const productList = useSelector((state) => state.product.productList);

  useEffect(() => {
    getProductList();
    return () => {
      console.log("컴포넌트가 화면에서 사라짐");
    };
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
            <Product productInfo={item}/>
          </Grid>
        ))}
      </Grid>
    </Box>
  );
}
