package org.ttsaudiosaver.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value = UrlTemplate.LOGIN, method = RequestMethod.GET)
	public String getLoginPage(Model model) {
		System.out.println("Inside getLoginPage method");
		return ViewMap.LOGIN.getView();
	}
	
	@RequestMapping(value = UrlTemplate.LOGIN, method = RequestMethod.POST)
	public String login(Model model) {
		return "redirect:" + ViewMap.INDEX.getView();
	}
	
	@RequestMapping(value = UrlTemplate.LOGOUT, method = RequestMethod.POST)
	public String logout(Model model) {
		return "redirect:" + ViewMap.INDEX.getView();
	}
}