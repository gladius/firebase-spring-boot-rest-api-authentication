import Cookies from "js-cookie";
import Router from "next/router";
import { createContext, useContext, useEffect, useReducer } from "react";
import publicPages from "components/auth/public-pages";
import axios from "axios";

export const AuthContext = createContext();
const RESET_USER_STATE = "RESET_USER_STATE";

const initialState = {
  isLoading: true,
  isAuthenticated: false,
  pic: null,
  fullname: null,
};

const userReducer = (state, action) => {
  switch (action.type) {
    case RESET_USER_STATE:
      const appCookies = action.payload;
      return {
        ...state,
        isAuthenticated: appCookies.authenticated,
        pic: appCookies.pic,
        fullname:
          appCookies.fullname && appCookies.fullname.split("_").join(" "),
        isLoading: false,
      };
    default:
      return state;
  }
};

export const AuthProvider = (props) => {
  const [state, dispatch] = useReducer(userReducer, initialState);

  useEffect(() => {
    refreshAuthContext();
  }, []);

  useEffect(() => {
    if (!state.isLoading) {
      const path = props.appProps.router.route;
      if (
        path != "/" &&
        !state.isAuthenticated &&
        !publicPages.includes(path)
      ) {
        Router.push("/");
      }
    }
  }, [state.isAuthenticated, state.isLoading]);

  const refreshAuthContext = () => {
    dispatch({ type: RESET_USER_STATE, payload: Cookies.get() });
  };

  const logout = () => {
    axios({
      url: process.env.NEXT_PUBLIC_MIDDLEWARE_URL + "/session/logout",
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      withCredentials: true,
    }).then((res) => {
      refreshAuthContext();
    });
  };

  return (
    <AuthContext.Provider
      value={{
        refreshAuthContext,
        logout,
        isLoading: state.isLoading,
        isAuthenticated: state.isAuthenticated,
        pic: state.pic,
        fullname: state.fullname,
      }}
    >
      {props.children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => {
  return useContext(AuthContext);
};
