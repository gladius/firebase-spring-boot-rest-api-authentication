import { useState } from "react";
import { useAuth } from "contexts/useAuth";
import axios from "axios";
import RolesView from "components/roles/roles";

const RoleManager = () => {
  const { loadingUser, user, isSeller, idToken, refreshToken } = useAuth();
  const [loading, setLoading] = useState(false);

  const addRole = (role) => {
    setLoading(true);
    axios({
      url:
        process.env.NEXT_PUBLIC_MIDDLEWARE_URL +
        "/role/add?uid=" +
        user.user_id +
        "&role=" +
        role,
      method: "PUT",
      headers: {
        Authorization: "Bearer " + idToken,
      },
    })
      .then(() => refreshToken())
      .then(() => setLoading(false));
  };

  const removeRole = (role) => {
    setLoading(true);
    axios({
      url:
        process.env.NEXT_PUBLIC_MIDDLEWARE_URL +
        "/role/remove?uid=" +
        user.user_id +
        "&role=" +
        role,
      method: "DELETE",
      headers: {
        Authorization: "Bearer " + idToken,
      },
    })
      .then(() => refreshToken())
      .then(() => setLoading(false));
  };
  return (
    <div className="container">
      <div className="roles-container">
        {loading ? (
          <h5>ReAuthenticating...</h5>
        ) : (
          <>
            <RolesView />
            <br />
            <div>
              {isSeller ? (
                <button
                  type="button"
                  className="button primary"
                  onClick={() => removeRole("ROLE_SELLER")}
                >
                  Remove Seller Role
                </button>
              ) : (
                <>
                  <button
                    type="button"
                    className="button primary"
                    onClick={() => addRole("ROLE_SELLER")}
                  >
                    Add Seller Role
                  </button>
                </>
              )}
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default RoleManager;
