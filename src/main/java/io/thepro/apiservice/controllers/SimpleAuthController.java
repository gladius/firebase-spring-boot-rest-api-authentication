package io.thepro.apiservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import io.thepro.apiservice.security.SecurityService;
import io.thepro.apiservice.security.models.User;

@RestController
public class SimpleAuthController {

	@Autowired
	SecurityService securityService;

	@PostMapping("/me")
	public User getUser() {
		return securityService.getUser();
	}

	@GetMapping("/create/token")
	public String getCustomToken() throws FirebaseAuthException {
		return FirebaseAuth.getInstance().createCustomToken(String.valueOf(securityService.getUser().getUid()));
	}

}
