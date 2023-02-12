import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";

export default function DenseTable(props) {

  return (
    <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} size="small" aria-label="a dense table">
        <TableHead>
          <TableRow>
            <TableCell>productName</TableCell>
            <TableCell align="right">orderStatus</TableCell>
            <TableCell align="right">qty</TableCell>
            <TableCell align="right">orderDate</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {props.myOrderList.map((row) => (
            <TableRow
              key={row.orderDate}
              sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
            >
              <TableCell component="th" scope="row">
                {row.productName}
              </TableCell>
              <TableCell align="right">{row.orderStatus}</TableCell>
              <TableCell align="right">{row.qty}</TableCell>
              <TableCell align="right">{row.orderDate}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
