package io.thepro.apiservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
