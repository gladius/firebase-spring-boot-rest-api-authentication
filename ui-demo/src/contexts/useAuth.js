import { useState, useEffect, createContext, useContext } from "react";
import firebase from "config/firebase-config";

export const UserContext = createContext();

const UserProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [idToken, setIdToken] = useState(null);
  const [loadingUser, setLoadingUser] = useState(true);
  const [roles, setRoles] = useState(null);
  const [isSuper, setIsSuper] = useState(false);
  const [isSeller, setIsSeller] = useState(false);

  const filterRoles = (claims) => {
    const filterRoles = Object.keys(claims).filter((claim) => {
      const roleClaim = claim.includes("ROLE_");
      setIndividualRoles(claim);
      return roleClaim;
    });
    return filterRoles;
  };

  const setIndividualRoles = (role) => {
    console.log("setIndividualRoles == > ", role);
    switch (role.toUpperCase()) {
      case "ROLE_SUPER":
        setIsSuper(true);
        break;
      case "ROLE_SELLER":
        setIsSeller(true);
        break;
      default:
        break;
    }
  };

  const hasRole = (role) => roles.includes(role);

  useEffect(() => {
    const unsubscriber = firebase.auth().onAuthStateChanged(async (user) => {
      try {
        if (user) {
          user.getIdToken().then((idToken) => {
            const { uid, displayName, email, photoURL } = user;
            setIdToken(idToken);
            setUser({ uid, displayName, email, photoURL });
          });
          firebase
            .auth()
            .currentUser.getIdTokenResult()
            .then((idTokenResult) => {
              if (typeof idTokenResult.claims != undefined) {
                setRoles(filterRoles(idTokenResult.claims));
              }
            })
            .catch((error) => {
              console.log(error);
            });
        } else {
          setUser(null);
          setIdToken(null);
        }
      } catch (error) {
      } finally {
        setLoadingUser(false);
      }
    });
    return () => unsubscriber();
  }, []);

  const login = () => {
    firebase.auth().signInWithPopup(new firebase.auth.GoogleAuthProvider());
  };

  const logout = () => {
    firebase.auth().signOut();
  };

  return (
    <UserContext.Provider
      value={{
        user,
        setUser,
        idToken,
        loadingUser,
        login,
        logout,
        roles,
        isSuper,
        isSeller,
        hasRole,
      }}
    >
      {children}
    </UserContext.Provider>
  );
};

export default UserProvider;
export const useAuth = () => useContext(UserContext);
