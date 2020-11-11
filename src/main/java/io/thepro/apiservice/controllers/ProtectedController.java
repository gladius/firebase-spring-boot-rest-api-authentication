package io.thepro.apiservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.thepro.apiservice.security.SecurityService;

@RestController
@RequestMapping("protected")
public class ProtectedController {

	@Autowired
	private SecurityService securityService;

	@GetMapping("data")
	public String getProtectedData() {
		String name = securityService.getUser().getName();
		return name.split("\\s+")[0] + ", you have accessed protected data from spring boot";
	}

}
