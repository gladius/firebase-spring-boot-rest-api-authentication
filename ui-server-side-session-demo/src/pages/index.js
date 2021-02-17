import Head from "next/head";
import Login from "components/auth/login";
import { useAuth } from "contexts/useAuth";

export default function Home() {
  const { isAuthenticated, fullname, pic, logout } = useAuth();
  return (
    <div className="container">
      <Head>
        <title>Server Side Session</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <main className="main">
        <div className="title-card">
          <h2 className="title">Server Side Session</h2>{" "}
          {isAuthenticated && (
            <span>
              <button
                className="button-logout"
                data-testid="logout"
                onClick={() => logout()}
              >
                Logout
              </button>
            </span>
          )}
        </div>

        {isAuthenticated ? (
          <div className="profile-container">
            {pic && <img src={pic} className="avatar" />}
            <span className="name" data-testid="authenticated-user-fullname">
              {fullname}
            </span>
          </div>
        ) : (
          <div className="login-container">
            <Login />
          </div>
        )}
      </main>
    </div>
  );
}
