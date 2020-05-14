import React, { useEffect, useState } from "react";
import axios from "axios";
import Layout from "../components/layout";
import Loader from "../components/loader";

const backend = () => {
  const MIDDLEWARE_URL = process.env.MIDDLEWARE_URL;
  const [restResponse, setRestResponse] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const path = MIDDLEWARE_URL + "/protected/data";
    console.log("Request Path :: ", path);
    axios({
      url: path,
      method: "GET",
      withCredentials: true,
    })
      .then((res) => {
        setRestResponse(res.data);
        setLoading(false);
      })
      .catch((error) => {
        console.log("Error from backend :", error.response.data);
        setError(error.response.data);
        setLoading(false);
      });
  }, []);

  return (
    <Layout>
      {loading ? (
        <Loader />
      ) : (
        <div>
          <span className="mb-5">
            <h4>Spring boot middleware response:</h4>
          </span>
          {restResponse && (
            <pre className="card text-white bg-dark p-2">
              {JSON.stringify(restResponse, undefined, 2)}
            </pre>
          )}
          {error && (
            <pre className="card text-white bg-danger p-2">
              {JSON.stringify(error, undefined, 2)}
            </pre>
          )}
        </div>
      )}
    </Layout>
  );
};

export default backend;
