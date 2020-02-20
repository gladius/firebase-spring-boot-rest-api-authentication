package io.thepro.apiservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import io.thepro.apiservice.security.SecurityUtils;
import io.thepro.apiservice.security.User;

@RestController
public class AuthController {

	@Autowired
	SecurityUtils securityUtils;

	@PostMapping("/me")
	public User login() {
		return securityUtils.getPrincipal();
	}

	@GetMapping("/create/token")
	public String getCustomToken() throws FirebaseAuthException {
		return FirebaseAuth.getInstance().createCustomToken(String.valueOf(securityUtils.getPrincipal().getUid()));
	}

}
