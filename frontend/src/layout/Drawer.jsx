import * as React from "react";
import Box from "@mui/material/Box";
import Drawer from "@mui/material/Drawer";
import List from "@mui/material/List";
import Divider from "@mui/material/Divider";
import ListItem from "@mui/material/ListItem";
import ListItemButton from "@mui/material/ListItemButton";
import ListItemIcon from "@mui/material/ListItemIcon";
import ListItemText from "@mui/material/ListItemText";
import { useDispatch, useSelector } from "react-redux";
import { drawerActions } from "../store/index";
import ListIcon from "@mui/icons-material/List";
import HomeSharpIcon from "@mui/icons-material/HomeSharp";
import { Link } from "react-router-dom";

export default function TemporaryDrawer() {
  const isDrawerOpen = useSelector((state) => state.drawer.isDrawer);

  const dispatch = useDispatch();

  const drawerCloseHandler = (event) => {
    event.preventDefault();
    dispatch(drawerActions.isDrawerClose());
  };

  const list = (anchor) => (
    <Box
      role="presentation"
      sx={{ width: anchor === "top" || anchor === "bottom" ? "auto" : 250 }}
    >
      <List>
        {["Home", "Product"].map((text, index) => (
          <ListItem key={text} disablePadding>
            <ListItemButton
              component={Link}
              to={index % 2 === 0 ? "/home" : "/product"}
            >
              <ListItemIcon>
                {index % 2 === 0 ? <HomeSharpIcon /> : <ListIcon />}
              </ListItemIcon>
              <ListItemText primary={text} />
            </ListItemButton>
          </ListItem>
        ))}
      </List>
      <Divider />
    </Box>
  );

  return (
    <div>
      {["left"].map((anchor) => (
        <React.Fragment key={anchor}>
          <Drawer
            anchor={anchor}
            open={isDrawerOpen}
            onClose={drawerCloseHandler}
          >
            {list(anchor)}
          </Drawer>
        </React.Fragment>
      ))}
    </div>
  );
}
