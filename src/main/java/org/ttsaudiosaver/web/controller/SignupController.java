package org.ttsaudiosaver.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignupController {
	
	@RequestMapping(value = UrlTemplate.SIGN_UP, method = RequestMethod.GET)
	public String getSignupPage(Model model) {
		System.out.println("Inside getSignupPage method");
		return ViewMap.SIGN_UP.getView();
	}
	
	@RequestMapping(value = UrlTemplate.FORGOT_PASSWORD, method = RequestMethod.GET)
	public String getForgotPasswordPage(Model model) {
		System.out.println("Inside getForgotPasswordPage method");
		return ViewMap.FORGOT_PASSWORD.getView();
	}
	
	@RequestMapping(value = UrlTemplate.SIGN_UP, method = RequestMethod.POST)
	public String signup(Model model) {
		System.out.println("Inside signup method");
		return "redirect:" + ViewMap.INDEX.getView();
	}

}
