import { useAuth } from "../context/useAuth";
import Navbar from "../components/navbar";
import Demo from "../components/data/demo";

const Index = () => {
  const { loadingUser, roles, isSeller, isSuper } = useAuth();

  return (
    <div className="container">
      {loadingUser ? (
        <h1>Authenticating</h1>
      ) : (
        <div>
          <Navbar />
          {roles && (
            <div className="card">
              <span className="label">User Roles</span>
              {roles &&
                roles.map((val, key) => (
                  <span key={key} className="role">
                    {val}
                  </span>
                ))}
            </div>
          )}
          <Demo label="Public Data" url="/public/data" />
          <Demo label="Protected Data" url="/protected/data" />
          {isSeller ? (
            <Demo label="Seller Data" url="/seller/data" />
          ) : (
            <div className="card access-closed">Visible only to Sellers</div>
          )}
          {isSuper ? (
            <Demo label="Super Data" url="/super/data" />
          ) : (
            <div className="card access-closed">
              Visible only to Super Admins
            </div>
          )}
        </div>
      )}
    </div>
  );
};

export default Index;
