# Firebase Authentication for Spring boot [![License: MIT](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)

[![Open with ThePro](https://thepro.io/button.svg)](https://thepro.io/post/firebase-authentication-for-spring-boot-rest-api-5V)

Firebase is a backendless platform to run applications without dedicated backend. But, sometimes you may need to communicate with API of an exisiting backend or you may want a dedicated backend to perform operations that cannot be done through firebase infrastructure.

This **Spring Boot Starter** is perfect for such situations when you want to extend firebase's authentication menchanism with **Spring Security** to seamlessly create and use protected rest API's.

### Configuration

- Be sure to add the following environment variable globally or project specific run configuration environment variable `GOOGLE_APPLICATION_CREDENTIALS=path_to_firebase_server_config.json`

- The starter can be configured to use firebase session as client side / strictly server side or both together.
- Htty Only / Secure enabled Session cookies may not work as expected in development hosts (localhost, 120.0.0.1). Adding self signed ssl certificate with reverse proxied host will work perfectly fine. Read this article => [Local Domain Names with SSL for development applications ](https://thepro.io/post/local-domain-names-with-ssl-for-local-development-applications-LG)
- Following application properties can edited to customize for your needs. Sample @ [application.yaml](src/main/resources/)

### Role Management

- Roles can be added through `SecurityRoleService` during registeration of user or manually managed by Super admins
- Super Admins are defined through application property `security.super-admins`
- With roles feature tightly integrated with spring security, Spring authorization annotations like **`@Secured, @RolesAllowed, @PreAuthorize, @PostAuthorized`** etc will work perfectly fine.
- I personally like to define per role annotations like **`@IsSuper, @IsSeller`** for the sake of simplicity.

```
    @GetMapping("data")
	@isSeller
	public String getProtectedData() {
		return "You have accessed seller only data from spring boot";
	}
```

- UI useAuth hook also has utility properties like **_ `roles, hasRole, isSuper, isSeller `_** properties exposed accross the application to allow or restrict access to specific UI components. Read this post at thepro.io for more detailed explanation on role management [Firebase and Spring Boot Based Role Management and Authorization](https://thepro.io/post/firebase-and-spring-boot-based-role-management-and-authorization-3D)

### End to End Test

The method I used to solve the problem of testing firebase social authentication is opinionated and may not be suitable to everyone. Simply put, we create a toggleable Test User functionaly that authenticates specific set of static test users through firebase custom token. This solves a lot of issues associated with testing a third party backed authentication flow. Read this post at thepro.io for more in detail explanation [End to End Test Firebase Authentication with Cypress, Spring Boot & Nextjs](https://thepro.io/post/end-to-end-test-firebase-authentication-with-cypress-spring-boot-nextjs-Mg)

## Related Tutorials :

- [Firebase Authentication for Spring Boot Rest API](https://thepro.io/post/firebase-authentication-for-spring-boot-rest-api-5V)
- [Firebase and Spring Boot Based Role Management and Authorization](https://thepro.io/post/firebase-and-spring-boot-based-role-management-and-authorization-3D)
- [Firebase with Spring Boot for Kubernetes Deployment Configuration](https://thepro.io/post/firebase-with-spring-boot-kubernetes-deployment-configuration-RA)
- [Local Domain Names with SSL for development applications ](https://thepro.io/post/local-domain-names-with-ssl-for-local-development-applications-LG)
- [Firebase Server Side Session Authentication with Next.js and Spring Boot](https://thepro.io/post/firebase-server-side-session-authentication-with-next.js-and-spring-boot-py)
- [End to End Test Firebase Authentication with Cypress, Spring Boot & Nextjs](https://thepro.io/post/end-to-end-test-firebase-authentication-with-cypress-spring-boot-nextjs-Mg)

### UI Demo

- Nextjs application demonstrating Client side firebase session. [ui-client-side-session-demo](ui-client-side-session-demo/)
- Nextjs application demonstrating Server side firebase session. [ui-server-side-session-demo](ui-server-side-session-demo/)

### Screenshots

#### Client Side Session Screenshots

|                                                                                  Logged out                                                                                   |                                                                                         Logged In                                                                                         |
| :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------: |
| ![Image of UI Loggedout](https://raw.githubusercontent.com/gladius/firebase-spring-boot-rest-api-authentication/master/ui-client-side-session-demo/screenshots/loggedout.png) |       ![Image of UI LoggedIn ](https://raw.githubusercontent.com/gladius/firebase-spring-boot-rest-api-authentication/master/ui-client-side-session-demo/screenshots/loggedin.png)        |
|                                                                                                                                                                               | ![Image of UI Loggedin Seller](https://raw.githubusercontent.com/gladius/firebase-spring-boot-rest-api-authentication/master/ui-client-side-session-demo/screenshots/loggedin_seller.png) |

#### Server Side Session Screenshots

![Image of UI Server Side Session](https://raw.githubusercontent.com/gladius/firebase-spring-boot-rest-api-authentication/master/ui-server-side-session-demo/screenshots/screenshot.png)

#### Cypress End to End Tests Screencapture

![Image of End to End Tests ](https://raw.githubusercontent.com/gladius/firebase-spring-boot-rest-api-authentication/master/ui-server-side-session-demo/screenshots/cypress_auth_test.gif)

## Author

👤 **Gladius**

- Website: thepro.io/@/gladius
- Github: [@gladius](https://github.com/gladius)

## Show your support

Give a ⭐️ if this project helped you!

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
