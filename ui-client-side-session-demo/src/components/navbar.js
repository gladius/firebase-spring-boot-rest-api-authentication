import React from "react";
import { useAuth } from "contexts/useAuth.js";

const Navbar = () => {
  const { user, login, logout } = useAuth();
  return (
    <div className="navbar">
      <h2>Spring Boot Firebase Authorization</h2>
      {user != null ? (
        <div className="user-nav-section">
          <div className="user-info">
            <img className="avatar" src={user.picture} />
            <div className="name">{user.name}</div>
          </div>
          <div className="logout-section">
            <button
              type="button"
              className="button secondary"
              onClick={() => logout()}
            >
              Logout
            </button>
          </div>
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
