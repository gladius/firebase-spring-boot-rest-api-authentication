import React, { useEffect, useState } from "react";
import axios from "axios";
import { useAuth } from "../../context/useAuth";

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
        setData(res.data);
        setStatus(res.status);
      })
      .catch((error) => {
        setData(error.response.data);
        setStatus(error.response.status);
      });
  }, [idToken]);

  return (
    <div className="card">
      <div>
        <span
          className="status"
          style={{ background: status == 200 ? "#02c39a" : "#d90429" }}
        >
          {status}
        </span>
        <span className="label">{label}</span>
        <span className="path">{url}</span>
      </div>

      <div className="message">{JSON.stringify(data)}</div>
    </div>
  );
};

export default backend;
