import ReactPolling from "react-polling";
import { LOCAL_URI } from "../constants/Constant";
import { commonSnackBar } from "../store/DataUtil";

export default function SpringBusConfig() {
  let data = {
    "Content-Type": "application/json",
  };

  return (
    <ReactPolling
      url={`${LOCAL_URI}/user-service/getBusMessage`}
      interval={5000}
      retryCount={10}
      onSuccess={(res) => {
        let message = res.responseData.msg;
        if (message !== "NO_MSG") {
          commonSnackBar({ msg: message, type: "warning" });
        }
        return true;
      }}
      onFailure={(err) => console.log(err + "handle failure")}
      headers={data}
      render={({ startPolling, stopPolling, isPolling }) => {}}
    />
  );
}
