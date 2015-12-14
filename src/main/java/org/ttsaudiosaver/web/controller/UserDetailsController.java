package org.ttsaudiosaver.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserDetailsController {
	
	@RequestMapping(value = UrlTemplate.USER_DETAILS, method = RequestMethod.GET)
	public String getUserDetailsPage(Model model) {
		return ViewMap.USER_DETAILS.getView();
	}

}