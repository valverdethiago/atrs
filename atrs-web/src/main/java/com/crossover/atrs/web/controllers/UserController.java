package com.crossover.atrs.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/me", method = GET)
	public OAuth2Authentication list(OAuth2Authentication auth) {
		return auth;
	}

}
