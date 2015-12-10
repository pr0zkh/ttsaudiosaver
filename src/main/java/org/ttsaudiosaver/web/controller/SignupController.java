package org.ttsaudiosaver.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignupController {
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String getSignupPage(Model model) {
		System.out.println("Inside getSignupPage method");
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(Model model) {
		System.out.println("Inside signup method");
		return "redirect:index";
	}

}
