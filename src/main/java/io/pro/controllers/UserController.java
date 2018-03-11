package io.pro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.pro.services.UserService;


@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping(method = RequestMethod.POST, value = "/user/save")
	public void save(@RequestHeader(value="ID-TOKEN") String idToken) throws Exception {
		service.saveUser(idToken);
	}

}
