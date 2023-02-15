import * as React from "react";
import Snackbar from "@mui/material/Snackbar";
import MuiAlert from "@mui/material/Alert";

import { useDispatch, useSelector } from "react-redux";
import { snackBarActions } from "../store/Index";

export default function SnackBar() {
  const snackBarInfo = useSelector((state) => state.snackBar.snackBar);
  console.log(snackBarInfo);

  const dispatch = useDispatch();
  const vertical = snackBarInfo.vertical;
  const horizontal = snackBarInfo.horizontal;

  const handleClose = () => {
    dispatch(snackBarActions.closeSnackBar());
  };

  const Alert = React.forwardRef(function Alert(props, ref) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
  });

  return (
    <div>
      <Snackbar
        anchorOrigin={{ vertical, horizontal }}
        open={snackBarInfo.showSnackBar}
        onClose={handleClose}
        key={snackBarInfo.vertical + snackBarInfo.horizontal}
        autoHideDuration={1500}
      >
        <Alert severity={snackBarInfo.type}>{snackBarInfo.msg}</Alert>
      </Snackbar>
    </div>
  );
}
