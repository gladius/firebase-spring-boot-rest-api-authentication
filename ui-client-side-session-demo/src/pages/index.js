import { useAuth } from "contexts/useAuth";
import Navbar from "components/navbar";
import Demo from "components/data/demo";

const Index = () => {
  const { loadingUser, roles, isSeller, isSuper } = useAuth();

  return (
    <div className="container">
      {loadingUser ? (
        <h1>Authenticating</h1>
      ) : (
        <div>
          <Navbar />
          <div className="card">
            {roles && (
              <div>
                <span className="label">User Roles</span>
                <span colspan="3" height="50px">
                  {roles &&
                    roles.map((val, key) => (
                      <span key={key} className="role">
                        {val}
                      </span>
                    ))}
                </span>
              </div>
            )}
            <table>
              <tbody>
                <tr>
                  <td className="label">Public Data</td>
                  <Demo url="/public/data" />
                </tr>
                <tr>
                  <td className="label">Protected Data</td>
                  <Demo url="/protected/data" />
                </tr>
                <tr>
                  <td className="label">Seller Data</td>
                  {isSeller ? (
                    <Demo url="/seller/data" />
                  ) : (
                    <>
                      <td className="data access-closed">
                        Component Visible only to Sellers
                      </td>
                      <td></td>
                      <td></td>
                    </>
                  )}
                </tr>
                <tr>
                  <td className="label">Super Data</td>
                  {isSuper ? (
                    <Demo url="/super/data" />
                  ) : (
                    <>
                      <td className="data access-closed">
                        Component Visible only to Super Admins
                      </td>
                      <td></td>
                      <td></td>
                    </>
                  )}
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
};

export default Index;
