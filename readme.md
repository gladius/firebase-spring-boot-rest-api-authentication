# Firebase Authentication for Spring boot [![License: MIT](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)

[![Open with ThePro](https://thepro.io/button.svg)](https://thepro.io/post/firebase-authentication-for-spring-boot-rest-api)

Firebase is a backendless platform to run applications without dedicated backend. But, sometimes you may need to communicate with API of an exisiting backend or you may want a dedicated backend to perform operations that cannot be done using firebase infrastructure .

This **Spring Boot Starter** is perfect for such situations when you want to extend firebase's authentication menchanism with **Spring Security** to seamlessly create and use protected rest API's.

### Configuration

- Be sure to add the following environment variable globally or project specific run configuration environment variable `GOOGLE_APPLICATION_CREDENTIALS=path_to_firebase_server_config.json`

- The starter can be configured to use firebase session as client side / strictly server side or both together.
- Htty Only / Secure enabled Session cookies may not work as expected in development hosts (localhost, 120.0.0.1). Adding self signed ssl certificate with reverse proxied host will work perfectly fine.
- Following application properties can edited to customize for your needs.

| Properties                                             | Description                                                                                                                                                                 | DataType |
| ------------------------------------------------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | -------- |
| `security.firebase-props.database-url`                 | Firebase Database URL found in Firebase Web SDK config                                                                                                                      | String   |
| `security.firebase-props.enable-strict-server-session` | server will only look for session cookie to verify request                                                                                                                  | Boolean  |
| `security.firebase-props.enable-check-session-revoked` | will check if firebase session was revoked elsewhere, this will also add overhead of few seconds to each request. Applicable only if `enable-strict-server-session` enabled | Boolean  |
| `security.firebase-props.enable-logout-everywhere`     | firebase will revoke refresh tokens everywhere. Applicable only if `enable-strict-server-session` enabled                                                                   | Boolean  |
| `security.firebase-props.session-expiry-in-days`       | Expiration time for long lived session. Applicable only if `enable-strict-server-session` enabled                                                                           | Integer  |
| `security.cookie-props.max-age-in-minutes`             | Default Cookie expiration time.                                                                                                                                             | Integer  |
| `security.cookie-props.http-only`                      | Cookies will not be accessible to client side scripts.                                                                                                                      | Boolean  |
| `security.cookie-props.secure`                         | Cookies will be sent only over secure https channel                                                                                                                         | Boolean  |
| `security.cookie-props.domain`                         | Cookies will only be available on provided domain                                                                                                                           | String   |
| `security.cookie-props.path`                           | Cookies will only available on provided path. Path "/" will allow access from any page.                                                                                     | String   |
| `security.allow-credentials`                           | Lets client know that server accepts cookies and other credentials from `security.allowed-origins`.                                                                         | String   |
| `security.allowed-origins`                             | An array of allowed cross origin domain names.                                                                                                                              | Array    |
| `security.allowed-methods`                             | An array of HTTP methods server will accept                                                                                                                                 | Array    |
| `security.allowed-headers`                             | An array of HTTP headers server will accept                                                                                                                                 | Array    |
| `security.allowed-public-apis`                         | An array of rest path on server which can be publicaly accessible. path can be wildcard ie. `/public/*` will accept `/public/path1,/public/path2`                           | Array    |
| `security.exposed-headers`                             | An array of exposed headers, this is required only if CSRF tokens are generated by the server                                                                               | Array    |

### UI Demo

#### Client Side Session Demo

Nextjs application demonstrating client side firebase session.

#### Server Side Session Demo

Nextjs application demonstrating server side firebase session.

### Kubernetes Deployment

Checkout the post at https://thepro.io/post/firebase-service-account-configuration-for-spring-boot-on-kubernetes

### Screenshots

![Image of UI Demo Protected API ](https://raw.githubusercontent.com/gladius/firebase-spring-boot-rest-api-authentication/master/screenshots/ui_demo_protected_api.png)

![Image of UI Demo Protected API ERROR](https://raw.githubusercontent.com/gladius/firebase-spring-boot-rest-api-authentication/master/screenshots/ui_demo_protected_api-error.png)

## Author

👤 **Gladius Thayalarajan**

- Website: thepro.io/@/gladius
- Github: [@gladius](https://github.com/gladius-thayalarajan)

## Show your support

Give a ⭐️ if this project helped you!

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
