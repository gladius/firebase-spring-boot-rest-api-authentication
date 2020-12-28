import React from "react";
import { useAuth } from "contexts/useAuth.js";

const Navbar = () => {
  const { user, login, logout } = useAuth();

  return (
    <div className="navbar">
      <h2>Spring Boot Firebase Authorization</h2>
      {user != null ? (
        <div>
          <button
            type="button"
            className="button secondary"
            onClick={() => logout()}
          >
            Logout
          </button>
        </div>
      ) : (
        <div>
          <button
            type="button"
            className="button primary"
            onClick={() => login()}
          >
            Login
          </button>
        </div>
      )}
    </div>
  );
};

export default Navbar;
