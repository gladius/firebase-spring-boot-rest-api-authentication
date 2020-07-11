import React from "react";

const loader = () => {
  return (
    <div className="d-flex justify-content-center">
      <div
        className="spinner-grow text-primary"
        style={{ width: "3rem", height: "3rem" }}
        role="status"
      >
        <span className="sr-only">Loading...</span>
      </div>
    </div>
  );
};

export default loader;
