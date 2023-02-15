import * as React from "react";
import Box from "@mui/material/Box";
import Backdrop from "@mui/material/Backdrop";
import SpeedDial from "@mui/material/SpeedDial";
import SpeedDialIcon from "@mui/material/SpeedDialIcon";
import SpeedDialAction from "@mui/material/SpeedDialAction";
import MonitorIcon from "@mui/icons-material/Monitor";
import SearchIcon from "@mui/icons-material/Search";
import SettingsIcon from "@mui/icons-material/Settings";
import PermMediaIcon from "@mui/icons-material/PermMedia";
import {
  ZIPKIN_URI,
  GRAFANA_URI,
  PROMETHEUS_URI,
  RABBITMQ_URI,
} from "../constants/constant";

const actions = [
  { icon: <SettingsIcon />, name: "RABBITMQ_URI", uri: RABBITMQ_URI },
  { icon: <SearchIcon />, name: "ZIPKIN_URI", uri: ZIPKIN_URI },
  { icon: <PermMediaIcon />, name: "PROMETHEUS_URI", uri: PROMETHEUS_URI },
  { icon: <MonitorIcon />, name: "GRAFANA_URI", uri: GRAFANA_URI },
];

export default function SpeedDialTooltipOpen(props) {
  const [open, setOpen] = React.useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleUri = (uri, event) => {
    window.open(uri);
  };

  return (
    <Box
      sx={{
        flexGrow: 1,
        position: "fixed",
        bottom: 50,
        right: 50,
      }}
    >
      {props.children}
      <SpeedDial
        ariaLabel="SpeedDial tooltip example"
        icon={<SpeedDialIcon />}
        onClose={handleClose}
        onOpen={handleOpen}
        open={open}
      >
        {actions.map((action) => (
          <SpeedDialAction
            key={action.name}
            icon={action.icon}
            tooltipTitle={action.name}
            tooltipOpen
            onClick={handleUri.bind(this, action.uri)}
          />
        ))}
      </SpeedDial>
    </Box>
  );
}
