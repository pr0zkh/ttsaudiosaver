package org.ttsaudiosaver.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@RequestMapping(value = "/greeting")
	public String sayHello(Model model) {
		System.out.println("Inside sayHello method");
		model.addAttribute("greeting", "Hello world!");
		return "hello";
	}
	
	@RequestMapping(value = UrlTemplate.INDEX, method = RequestMethod.GET)
	public String getIndexPage(Model model) {
		return ViewMap.INDEX.getView();
	}
}