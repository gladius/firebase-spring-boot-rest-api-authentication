import React, { useEffect, useState } from "react";
import axios from "axios";
import { useAuth } from "contexts/useAuth.js";

const backend = ({ url, label }) => {
  const { idToken } = useAuth();
  const [data, setData] = useState(null);
  const [status, setStatus] = useState(null);

  useEffect(() => {
    axios({
      url: "http://localhost:8090" + url,
      method: "GET",
      headers: {
        Authorization: "Bearer " + idToken,
      },
    })
      .then((res) => {
        console.log("res.data ==> ", res.data);
        setData(res.data);
        setStatus(res.status);
      })
      .catch((error) => {
        setData(error.response.data);
        setStatus(error.response.status);
      });
  }, [idToken]);
  console.log("data ==> ", data);
  return (
    <>
      {data && (
        <>
          <td className="data">
            <div className="message">
              {status == 200 ? data : <>{data.message}</>}
            </div>
          </td>
          <td>
            <span className="path">{url}</span>
          </td>
          <td>
            <span
              className="status"
              style={{ background: status == 200 ? "#02c39a" : "#d90429" }}
            >
              {status}
            </span>
          </td>
        </>
      )}
    </>
  );
};

export default backend;
