package org.ttsaudiosaver.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.ttsaudiosaver.web.model.User;
import org.ttsaudiosaver.web.model.dao.user.UserDAO;
import org.ttsaudiosaver.web.service.RegistrationService;

@Controller
public class SignupController {
	
	@Autowired
	private RegistrationService registrationService;
	
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
	public String signup(@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("username") String username,
			HttpSession session,
			Model model) {
		System.out.println("Inside signup method");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user = registrationService.register(user);
		session.setAttribute("user", user);
		return "redirect:" + UrlTemplate.INDEX;
	}

}
