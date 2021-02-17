import "styles/globals.css";
import { AuthProvider } from "contexts/useAuth";

function MyApp(props) {
  const { Component, pageProps } = props;

  return (
    <AuthProvider appProps={props}>
      <Component {...pageProps} />
    </AuthProvider>
  );
}

export default MyApp;
