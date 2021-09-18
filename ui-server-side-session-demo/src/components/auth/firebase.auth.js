import axios from "axios";
import { getAuth, signInWithCustomToken } from "firebase/auth";
import firebase from "config/firebase.config";

export const backendLogin = async (idToken, user) => {
  return axios({
    url: process.env.NEXT_PUBLIC_MIDDLEWARE_URL + "/session/login",
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: "Bearer " + idToken,
    },
    data: { provider: "GOOGLE" },
    withCredentials: true,
  })
    .then((res) => {
      return Promise.resolve({ success: true, user });
    })
    .catch((error) => {
      return Promise.reject(error);
    });
};

export const testLogin = async (testId) => {
  return axios({
    url: process.env.NEXT_PUBLIC_MIDDLEWARE_URL + `/test/login/${testId}`,
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((res) => {
      return signInWithCustomToken(getAuth(firebase), res.data)
        .then(async (resp) => {
          return await resp.user.getIdToken().then((idToken) => {
            return Promise.resolve(backendLogin(idToken));
          });
        })
        .catch((error) => {
          return Promise.reject(error);
        });
    })
    .catch((error) => {
      return Promise.reject(error);
    });
};
