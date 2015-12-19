package org.ttsaudiosaver.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ttsaudiosaver.web.SessionAttributes;
import org.ttsaudiosaver.web.model.User;
import org.ttsaudiosaver.web.service.profile.ProfileService;
import org.ttsaudiosaver.web.service.profile.ProfileService.UserAlreadyExistsException;
import org.ttsaudiosaver.web.service.profile.ProfileService.UserNotFoundException;
import org.ttsaudiosaver.web.service.profile.ProfileService.UserUpdateFailedException;

import com.google.gson.JsonObject;

@Controller
public class ProfileController {
	
	private static final String STATUS_RESPONSE_PARAM = "status";
	private static final String ERROR_MSG_PARAM = "errorMsg";
	
	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(value = UrlTemplate.CHANGE_PASSWORD, method = RequestMethod.POST)
	public @ResponseBody String changePassword(@RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword,
			HttpSession session) {
		User user = (User)session.getAttribute(SessionAttributes.USER);
		JsonObject response = new JsonObject();
		try {
			user = profileService.changePassword(user, oldPassword, newPassword);
			session.setAttribute(SessionAttributes.USER, user);
			response.addProperty(STATUS_RESPONSE_PARAM, "success");
		} catch (UserUpdateFailedException e) {
			response.addProperty(STATUS_RESPONSE_PARAM, "error");
			response.addProperty(ERROR_MSG_PARAM, "Your old password and password that was submitted do not match");
		}
		return response.toString();
	}
	
	@RequestMapping(value = UrlTemplate.RESTORE_PASSWORD, method = RequestMethod.POST)
	public @ResponseBody String restorePassword(@RequestParam("email") String email) {
		JsonObject response = new JsonObject();
		try {
			profileService.restorePassword(email);
			response.addProperty(STATUS_RESPONSE_PARAM, "success");
		} catch (UserNotFoundException e) {
			response.addProperty(STATUS_RESPONSE_PARAM, "error");
			response.addProperty(ERROR_MSG_PARAM, e.getMessage());
		}
		return response.toString();
	}
	
	@RequestMapping(value = UrlTemplate.UPDATE_PROFILE, method = RequestMethod.POST)
	public @ResponseBody String updateProfileDetails(@RequestParam("username") String newUsername,
			@RequestParam("email") String newEmail, HttpSession session) {
		JsonObject response = new JsonObject();
		User user = (User)session.getAttribute(SessionAttributes.USER); 
		try {
			user = profileService.updateProfileInfo(user, newUsername, newEmail);
			session.setAttribute(SessionAttributes.USER, user);
			response.addProperty(STATUS_RESPONSE_PARAM, "success");
		} catch (UserAlreadyExistsException e) {
			response.addProperty(STATUS_RESPONSE_PARAM, "error");
			response.addProperty(ERROR_MSG_PARAM, e.getMessage());
		}
		return response.toString();
	}
}