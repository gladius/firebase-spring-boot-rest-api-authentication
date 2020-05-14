import React from "react";
import Link from "next/link";

const layout = ({ children }) => {
  return (
    <div className="vertical-center">
      <div className="container">
        <div className="card">
          <div className="card-header">
            <h3>
              <Link href="/">
                <a style={{ color: "#000" }}>
                  Spring Boot Firebase Authorization Demo
                </a>
              </Link>
            </h3>
          </div>
          <div className="card-body p-5">{children}</div>
        </div>
      </div>
    </div>
  );
};

export default layout;
