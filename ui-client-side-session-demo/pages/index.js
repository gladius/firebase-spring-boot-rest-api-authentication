import Link from "next/link";
import Layout from "../components/layout";
import Loader from "../components/loader";
import { useUser } from "../context/userContext";
import firebase from "../firebase/clientApp";

export default () => {
  const { user, loadingUser } = useUser();

  const login = () => {
    firebase.auth().signInWithPopup(new firebase.auth.GoogleAuthProvider());
  };

  const logout = () => {
    firebase.auth().signOut();
  };

  return (
    <Layout>
      {loadingUser ? (
        <Loader />
      ) : (
        <div>
          {user != null ? (
            <div>
              <button
                type="button"
                className="btn btn-danger float-right"
                onClick={() => logout()}
              >
                Logout
              </button>
              <h3>
                <Link href="/demo">
                  Go to firebase authorized rest call demo
                </Link>
              </h3>
            </div>
          ) : (
            <div>
              <button
                type="button"
                className="btn btn-primary"
                onClick={() => login()}
              >
                Login
              </button>
            </div>
          )}
        </div>
      )}
    </Layout>
  );
};
