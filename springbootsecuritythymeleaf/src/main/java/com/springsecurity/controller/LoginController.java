package com.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/showMyLoginPage")
	public String loginPage() {
		return "fancy-login";
	}
	
	@GetMapping("/access-denied")
	public String accessDenaid() {
		return "access-denied";
	}

}
