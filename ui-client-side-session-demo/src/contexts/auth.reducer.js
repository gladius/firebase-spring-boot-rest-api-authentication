export const RESET_AUTH_STATE = "RESET_AUTH_STATE";

export const initialState = {
  user: null,
  idToken: null,
  roles: null,
  isSuper: false,
  isSeller: false,
  loadingUser: true,
};

export const authReducer = (state, action) => {
  switch (action.type) {
    case RESET_AUTH_STATE:
      return {
        ...state,
        user: action.payload.user ? action.payload.user : initialState.user,
        idToken: action.payload.idToken,
        roles: action.payload.roles,
        isSuper: action.payload.isSuper,
        isSeller: action.payload.isSeller,
        loadingUser: false,
      };
    default:
      return state;
  }
};
