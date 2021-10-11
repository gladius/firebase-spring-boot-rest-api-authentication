import GoogleSVG from "./google.svg";
import { useState } from "react";
import { testLogin, backendLogin } from "./firebase.auth";
import {
  getAuth,
  setPersistence,
  inMemoryPersistence,
  signInWithPopup,
  GoogleAuthProvider,
} from "firebase/auth";
import { useAuth } from "contexts/useAuth";
import firebase from "config/firebase.config";

const LoginModal = ({}) => {
  const [loading, setLoading] = useState(null);
  const [testId, setTestId] = useState("");
  const { refreshAuthContext } = useAuth();
  const authenticateUser = () => {
    setLoading("Authenticating");
    const auth = getAuth(firebase);
    setPersistence(auth, inMemoryPersistence).then(() => {
      signInWithPopup(auth, new GoogleAuthProvider()).then(async (result) => {
        if (result.user) {
          result.user.getIdToken().then((idToken) => {
            backendLogin(idToken, result.user).then((response) =>
              refreshAuthContext()
            );
          });
        }
      });
    });
  };
  return (
    <div>
      {!loading ? (
        <>
          <button
            data-testid="login-with-google"
            onClick={() => authenticateUser()}
            className="button-google"
          >
            <span className="button-icon">
              <GoogleSVG size="30px" />
            </span>
            Sign in with Google
          </button>
          {process.env.NEXT_PUBLIC_TEST_LOGIN == "true" && (
            <div className="test-login-container">
              <input
                data-testid="test-login-user"
                value={testId}
                onChange={(e) => setTestId(e.target.value)}
              />
              <button
                data-testid="test-login-submit"
                onClick={() =>
                  testLogin(testId)
                    .then((resp) => {
                      refreshAuthContext();
                    })
                    .catch((error) => setLoading("Authentication Failed"))
                }
              >
                Test Login
              </button>
            </div>
          )}
        </>
      ) : (
        <div>{loading}</div>
      )}
    </div>
  );
};

export default LoginModal;
