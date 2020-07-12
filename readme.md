# Firebase Authentication for Spring boot [![License: MIT](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)

[![Open with ThePro](https://thepro.io/button.svg)](https://thepro.io/post/firebase-authentication-for-spring-boot-rest-api-KL)

Firebase is a backendless platform to run applications without dedicated backend. But, sometimes you may need to communicate with API of an exisiting backend or you may want a dedicated backend to perform operations that cannot be done using firebase infrastructure .

This **Spring Boot Starter** is perfect for such situations when you want to extend firebase's authentication menchanism with **Spring Security** to seamlessly create and use protected rest API's.

### Configuration

- Be sure to add the following environment variable globally or project specific run configuration environment variable `GOOGLE_APPLICATION_CREDENTIALS=path_to_firebase_server_config.json`

- The starter can be configured to use firebase session as client side / strictly server side or both together.
- Htty Only / Secure enabled Session cookies may not work as expected in development hosts (localhost, 120.0.0.1). Adding self signed ssl certificate with reverse proxied host will work perfectly fine.
- Following application properties can edited to customize for your needs. Sample @ [application.yaml](src/main/resources/)

## Tutorials :

* https://thepro.io/post/firebase-authentication-for-spring-boot-rest-api-KL

### UI Demo

#### Screenshots

![Image of UI Demo Protected API ](https://raw.githubusercontent.com/gladius/firebase-spring-boot-rest-api-authentication/master/ui-demo/screenshots/ui_demo_protected_api.png)
![Image of UI Demo Protected API ERROR](https://raw.githubusercontent.com/gladius/firebase-spring-boot-rest-api-authentication/master/ui-demo/screenshots/ui_demo_protected_api-error.png)

#### Client Side Session Demo

Nextjs application demonstrating client side firebase session. [client-side-session](ui-demo/client-side-session/)

#### Server Side Session Demo

Nextjs application demonstrating server side firebase session. [server-side-session](ui-demo/server-side-session/)

### Kubernetes Deployment

Checkout the post at https://thepro.io/post/firebase-service-account-configuration-for-spring-boot-on-kubernetes

## Author

👤 **Gladius**

- Website: thepro.io/@/gladius
- Github: [@gladius](https://github.com/gladius)

## Show your support

Give a ⭐️ if this project helped you!

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
