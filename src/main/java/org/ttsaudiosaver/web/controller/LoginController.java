package org.ttsaudiosaver.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(Model model) {
		System.out.println("Inside getLoginPage method");
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model) {
		return null;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(Model model) {
		return null;
	}
}