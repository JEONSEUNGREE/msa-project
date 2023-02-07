import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import Bar from "./layout/appbar";
import reportWebVitals from "./reportWebVitals";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <React.StrictMode>
    <Bar />
  </React.StrictMode>
);

reportWebVitals();
