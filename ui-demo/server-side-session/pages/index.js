import Link from "next/link";
import axios from "axios";
import cookies from "next-cookies";
import Layout from "../components/layout";
import firebase from "../firebase/clientApp";

export async function getServerSideProps(ctx) {
  const allCookies = cookies(ctx);
  let isAuthenticated = false;
  if (
    allCookies["authenticated"] != null &&
    typeof allCookies["authenticated"] != "undefined"
  ) {
    isAuthenticated = true;
  }
  return {
    props: {
      isAuthenticated,
    },
  };
}

export default ({ isAuthenticated }) => {
  const MIDDLEWARE_URL = process.env.MIDDLEWARE_URL;
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
        location.reload(true);
      })
      .catch();
  };

  const logout = async () => {
    axios({
      url: MIDDLEWARE_URL + "/session/logout",
      method: "POST",
      withCredentials: true,
    })
      .then((response) => {
        location.reload(true);
      })
      .catch((error) => {
        console.log("error from middleware :: ", error);
      });
  };

  return (
    <Layout>
      {isAuthenticated ? (
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
    </Layout>
  );
};
