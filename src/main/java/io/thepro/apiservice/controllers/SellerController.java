package io.thepro.apiservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.thepro.apiservice.security.roles.IsSeller;

@RestController
@RequestMapping("seller")
public class SellerController {
	
	@GetMapping("data")
	@IsSeller
	public String getProtectedData() {
		return "You have accessed seller only data from spring boot";
	}
	
}
