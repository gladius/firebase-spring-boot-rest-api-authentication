# Firebase Authentication for Spring boot [![License: MIT](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)

[![Open with ThePro](https://thepro.io/button.svg)](https://thepro.io/post/firebase-authentication-for-spring-boot-rest-api-KL)

Firebase is a backendless platform to run applications without dedicated backend. But, sometimes you may need to communicate with API of an exisiting backend or you may want a dedicated backend to perform operations that cannot be done using firebase infrastructure .

This **Spring Boot Starter** is perfect for such situations when you want to extend firebase's authentication menchanism with **Spring Security** to seamlessly create and use protected rest API's.

### Configuration

- Be sure to add the following environment variable globally or project specific run configuration environment variable `GOOGLE_APPLICATION_CREDENTIALS=path_to_firebase_server_config.json`

- The starter can be configured to use firebase session as client side / strictly server side or both together.
- Htty Only / Secure enabled Session cookies may not work as expected in development hosts (localhost, 120.0.0.1). Adding self signed ssl certificate with reverse proxied host will work perfectly fine. Read this article => [Create Local Domains with SSL for all Development Web Applications](https://thepro.io/post/create-local-domains-with-ssl-for-all-development-web-applications-Ya)
- Following application properties can edited to customize for your needs. Sample @ [application.yaml](src/main/resources/)

### Role Management

- Roles can be added through `SecurityRoleService` during registeration of user or manually managed by Super admins
- Super Admins are defined through application property `security.super-admins`
- With roles interated with spring security, spring authorization annotations like @Secured, @RolesAllowed, @PreAuthorize, @PostAuthorized etc will work out of the box.
- I personally like to define per role annotations like @IsSuper, @IsSeller etc for the sake of simplicity.

```
    @GetMapping("data")
	@isSeller
	public String getProtectedData() {
		return "You have accessed seller only data from spring boot";
	}
```

- UI useAuth hook also has utility properties like roles, hasRole, isSuper, isSeller properties exposed accross the application to allow or restrict access to specific UI components

## Tutorials :

- [Firebase Authentication for Spring Boot Rest API](https://thepro.io/post/firebase-authentication-for-spring-boot-rest-api-KL)
- [Create Local Domains with SSL for all Development Web Applications](https://thepro.io/post/create-local-domains-with-ssl-for-all-your-web-applications-Ya)
- [Configuring Firebase with Spring Boot for Kubernetes Deployment](https://thepro.io/post/configuring-firebase-with-spring-boot-for-kubernetes-deployment-wL)

### UI Demo

#### UI Demo

Nextjs application demonstrating client side firebase session. [ui-demo](ui-demo/)

#### Screenshots

|                                                                        Logged out                                                                         |                                                                        Logged In                                                                         |
| :-------------------------------------------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------------------------------------: |
| ![Image of UI Loggedout](https://raw.githubusercontent.com/gladius/firebase-spring-boot-rest-api-authentication/master/ui-demo/screenshots/loggedout.png) | ![Image of UI LoggedIn ](https://raw.githubusercontent.com/gladius/firebase-spring-boot-rest-api-authentication/master/ui-demo/screenshots/loggedin.png) |

### Kubernetes Deployment

Checkout the post at https://thepro.io/post/configuring-firebase-with-spring-boot-for-kubernetes-deployment-wL

## Author

👤 **Gladius**

- Website: thepro.io/@/gladius
- Github: [@gladius](https://github.com/gladius)

## Show your support

Give a ⭐️ if this project helped you!

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
