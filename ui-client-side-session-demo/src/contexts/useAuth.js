import { useReducer, useEffect, createContext, useContext } from "react";
import firebase from "config/firebase-config";
import { initialState, authReducer, RESET_AUTH_STATE } from "./auth.reducer";

export const UserContext = createContext();

const UserProvider = ({ children }) => {
  const [state, dispatch] = useReducer(authReducer, initialState);

  useEffect(() => {
    const unsubscriber = firebase.auth().onAuthStateChanged(async (user) => {
      if (user) {
        resetAuth();
      } else {
        dispatch({
          type: RESET_AUTH_STATE,
          payload: {
            loading: false,
          },
        });
      }
    });
    return () => unsubscriber();
  }, []);

  const hasRole = (role) => state.roles.includes(role);

  const extractRoles = (claims) =>
    Object.keys(claims).filter((claim) => claim.includes("ROLE_"));

  // Login
  const login = () =>
    firebase.auth().signInWithPopup(new firebase.auth.GoogleAuthProvider());

  // Logout
  const logout = () => firebase.auth().signOut();

  const refreshToken = () => {
    firebase
      .auth()
      .currentUser.getIdToken(true)
      .then((idToken) => resetAuth(idToken))
      .catch((error) => console.error(error));
  };

  const resetAuth = () => {
    firebase
      .auth()
      .currentUser.getIdTokenResult()
      .then((idTokenResult) => {
        if (typeof idTokenResult.claims != undefined) {
          const roles = extractRoles(idTokenResult.claims);
          dispatch({
            type: RESET_AUTH_STATE,
            payload: {
              user: idTokenResult.claims,
              idToken: idTokenResult.token,
              roles,
              isSuper: roles.includes("ROLE_SUPER"),
              isSeller: roles.includes("ROLE_SELLER"),
            },
          });
        }
      })
      .catch((error) => console.error(error));
  };
  return (
    <UserContext.Provider
      value={{
        login,
        logout,
        refreshToken,
        user: state.user,
        roles: state.roles,
        isSuper: state.isSuper,
        isSeller: state.isSeller,
        idToken: state.idToken,
        loadingUser: state.loadingUser,
        hasRole,
      }}
    >
      {children}
    </UserContext.Provider>
  );
};

export default UserProvider;
export const useAuth = () => useContext(UserContext);
