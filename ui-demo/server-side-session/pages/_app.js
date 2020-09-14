import Head from "next/head";

const App = ({ Component, pageProps }) => (
  <>
    <Head>
      <title>Spring Boot Firebase Authorization Demo</title>
      <meta name="viewport" content="initial-scale=1.0, width=device-width" />
      <link href="style.css" rel="stylesheet" />
      <link
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
        rel="stylesheet"
      />
    </Head>
    <Component {...pageProps} />
  </>
);

export default App;
