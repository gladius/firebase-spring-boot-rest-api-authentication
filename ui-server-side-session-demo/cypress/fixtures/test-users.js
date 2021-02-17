export const testUsers = {
  "johnwick@online.com": {
    uid: 3,
    name: "John Wick",
    isSuper: false,
    email: "johnwick@online.com",
  },
  "jakeperalta@online.com": {
    uid: 4,
    name: "jake peralta",
    isSuper: false,
    email: "jakeperalta@online.com",
  },
  "superman@online.com": {
    uid: 5,
    name: "super man",
    isSuper: true,
    email: "superman@online.com",
  },
};

export const findUserByEmail = (email) => {
  return testUsers[email];
};
