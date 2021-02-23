import React, { useEffect, useState } from "react";
import axios from "axios";
import { useAuth } from "contexts/useAuth.js";

const backend = ({ url, label }) => {
  const { idToken } = useAuth();
  const [data, setData] = useState(null);
  const [status, setStatus] = useState(null);

  useEffect(() => {
    axios({
      url: process.env.NEXT_PUBLIC_MIDDLEWARE_URL + url,
      method: "GET",
      headers: {
        Authorization: "Bearer " + idToken,
      },
    })
      .then((res) => {
        setData(res.data);
        setStatus(res.status);
      })
      .catch((error) => {
        if (error.response) {
          setData(error.response.data.message);
          setStatus(error.response.data.code);
        }
      });
  }, [idToken]);
  return (
    <>
      {data && (
        <>
          <td className="data">
            <div className="message">{data}</div>
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
