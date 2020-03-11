# Firebase Authentication for Spring boot [![License: MIT](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT) 

[![Open with ThePro](https://thepro.io/button.svg)](https://thepro.io/post/firebase-authentication-for-spring-boot-rest-api)

Firebase is a backendless platform to run applications without dedicated backend. But, sometimes you may need to communicate with API of an exisiting backend or you may want a dedicated backend to perform operations that cannot be done using firebase infrastructure .

This **Spring Boot Starter** is perfect for such situations when you want to extend firebase's authentication menchanism with **Spring Security** to seamlessly create and use protected rest API's.

### Configuration

Be sure to add the following environment variable globally or project specific run configuration environment variable

 * GOOGLE_APPLICATION_CREDENTIALS=path_to_firebase_server_config.json

### Kubernetes Deployment

Checkout the post at https://thepro.io/post/firebase-service-account-configuration-for-spring-boot-on-kubernetes

### Protected API

![Image of Protected API ](https://raw.githubusercontent.com/gladius-thayalarajan/firebase-spring-boot-rest-api-authentication/master/screenshots/protected_api.png)

### Public API

![Image of Protected API ](https://raw.githubusercontent.com/gladius-thayalarajan/firebase-spring-boot-rest-api-authentication/master/screenshots/public_api.png)


## Author

👤 **Gladius Thayalarajan**

* Website: thepro.io/@/gladius
* Github: [@gladius-thayalarajan](https://github.com/gladius-thayalarajan)


## Show your support

Give a ⭐️ if this project helped you!


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
