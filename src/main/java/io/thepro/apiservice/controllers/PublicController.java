package io.thepro.apiservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("public")
public class PublicController {

	@GetMapping("data")
	public String getPublicData() {

		return "You have accessed public data from spring boot";
	}

}
