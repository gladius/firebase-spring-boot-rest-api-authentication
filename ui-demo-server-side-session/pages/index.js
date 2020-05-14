import Link from "next/link";
import axios from "axios";
import Layout from "../components/layout";
import Loader from "../components/loader";
import { useUser } from "../context/userContext";
import firebase from "../firebase/clientApp";

export default () => {
  const MIDDLEWARE_URL = process.env.MIDDLEWARE_URL;
  const { user, loadingUser } = useUser();

  const login = () => {
    firebase.auth().setPersistence(firebase.auth.Auth.Persistence.NONE);
    firebase
      .auth()
      .signInWithPopup(new firebase.auth.GoogleAuthProvider())
      .then((resp) => {
        resp.user.getIdToken().then((idToken) => {
          console.log("IdToken :: ", idToken);
          sessionLogin(idToken);
        });
      })
      .catch((error) => {
        console.log("Error from firebase login :: ", error);
      });
  };

  const sessionLogin = async (idToken) => {
    axios({
      url: MIDDLEWARE_URL + "/session/login",
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: "Bearer " + idToken,
      },
      withCredentials: true,
    })
      .then((res) => {
        console.log("response from backend:: ", res);
      })
      .catch();
  };

  const logout = async () => {
    axios({
      url: MIDDLEWARE_URL + "/session/logout",
      method: "POST",
    })
      .then((response) => {
        console.log("Response from middleware :: ", response);
      })
      .catch((error) => {
        console.log("error from middleware :: ", error);
      });
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
                  <a>Go to firebase authorized rest call demo</a>
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
