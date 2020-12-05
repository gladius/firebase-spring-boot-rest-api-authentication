import UserProvider from "../context/useAuth";
import "../styles.css";

const MyApp = ({ Component, pageProps }) => {
  return (
    <UserProvider>
      <Component {...pageProps} />
    </UserProvider>
  );
};

export default MyApp;
