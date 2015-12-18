package org.ttsaudiosaver.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.ttsaudiosaver.web.SessionAttributes;
import org.ttsaudiosaver.web.model.User;
import org.ttsaudiosaver.web.service.profile.ProfileService;
import org.ttsaudiosaver.web.service.profile.ProfileService.UserAlreadyExistsException;

@Controller
public class SignupController {
	
	private static final Logger logger = Logger.getLogger(SignupController.class);
	
	@Autowired
	private ProfileService registrationService;
	
	@RequestMapping(value = UrlTemplate.SIGN_UP, method = RequestMethod.GET)
	public String getSignupPage(Model model) {
		return ViewMap.SIGN_UP.getView();
	}
	
	@RequestMapping(value = UrlTemplate.FORGOT_PASSWORD, method = RequestMethod.GET)
	public String getForgotPasswordPage(Model model) {
		return ViewMap.FORGOT_PASSWORD.getView();
	}
	
	@RequestMapping(value = UrlTemplate.SIGN_UP, method = RequestMethod.POST)
	public String signup(@RequestParam("email") String email,
			@RequestParam("password") String password,
			@RequestParam("username") String username,
			HttpSession session,
			Model model) {
		try {
			User user = registrationService.register(username, password, email);
			session.setAttribute(SessionAttributes.USER, user);
			return "redirect:" + UrlTemplate.INDEX;
		} catch (UserAlreadyExistsException e) {
			logger.error("Could not perform registration: " + e.getMessage());
			return ViewMap.SIGN_UP.getView();
		}
	}
}