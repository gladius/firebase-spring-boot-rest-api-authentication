package io.thepro.apiservice.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

	@GetMapping("/data")
	public Map<String, Object> getData() {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("message", "Hello there");
		return response;
	}

}
