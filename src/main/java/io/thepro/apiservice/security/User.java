package io.thepro.apiservice.security;

import lombok.Data;

@Data
public class User {

	private String uid;
	private String name;
	private String email;
	private boolean isEmailVerified;
	private String issuer;
	private String picture;

}
