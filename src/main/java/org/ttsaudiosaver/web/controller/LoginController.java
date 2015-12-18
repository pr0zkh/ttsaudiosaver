package org.ttsaudiosaver.web.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ttsaudiosaver.web.SessionAttributes;
import org.ttsaudiosaver.web.model.User;
import org.ttsaudiosaver.web.service.profile.ProfileService;
import org.ttsaudiosaver.web.service.profile.ProfileService.LoginFailedException;
import org.ttsaudiosaver.web.service.profile.ProfileService.UserNotFoundException;

@Controller
public class LoginController {
	
	private static final Logger logger = Logger.getLogger(LoginController.class); 
	
	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(value = UrlTemplate.LOGIN, method = RequestMethod.GET)
	public String getLoginPage(Model model) {
		logger.info("Inside getLoginPage method");
		return ViewMap.LOGIN.getView();
	}
	
	@RequestMapping(value = UrlTemplate.LOGIN, method = RequestMethod.POST)
	public String login(@RequestParam("email") String email,
			@RequestParam("password") String password,
			HttpSession session,
			Model model) {
		logger.info("Inside login method");
		try {
			User user = profileService.login(email, password);
			session.setAttribute(SessionAttributes.USER, user);
			return "redirect:" + UrlTemplate.INDEX;
		} catch (UserNotFoundException e) {
			model.addAttribute("error", "Could not find user with email " + email + ". Please, try again.");
		} catch (LoginFailedException e) {
			model.addAttribute("error", "Login failed. Please, try again or contact our technical support.");
		}
		model.addAttribute("email", email);
		return ViewMap.LOGIN.getView();
	}
	
	@RequestMapping(value = UrlTemplate.FACEBOOK_LOGIN, method = RequestMethod.POST)
	public @ResponseBody String fbLogin(@RequestParam("username") String username,
			@RequestParam("email") String email, 
			@RequestParam("profilePicUrl") String profilePicUrl, 
			HttpSession session) {
		User user = profileService.fbLogin(email, username, profilePicUrl);
		session.setAttribute(SessionAttributes.USER, user);
		return "success";
	}
	
	@RequestMapping(value = UrlTemplate.LOGOUT, method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute(SessionAttributes.USER);
		return "redirect:" + UrlTemplate.INDEX;
	}
}