package org.ttsaudiosaver.web.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ttsaudiosaver.web.model.CompiledAudio;
import org.ttsaudiosaver.web.model.TranslationPair;
import org.ttsaudiosaver.web.service.translation.TranslationService;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Controller
public class TranslationController {
	
	private static final Logger logger = Logger.getLogger(TranslationController.class);
	private static final String STATUS = "status";
	private static final String DATA = "data";
	private static final String ERROR_MSG = "errorMsg";
	
	@Autowired
	private TranslationService translationService;
	
	@RequestMapping(value = UrlTemplate.TRANSLATE, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String translate(@RequestParam("toTranslate") String toTranslate, 
			@RequestParam("fromLang") String fromLang, 
			@RequestParam("toLang") String toLang) {
		JsonObject response = new JsonObject();
		try {
			TranslationPair pair = translationService.getTranslation(toTranslate, fromLang, toLang);
			response.addProperty(STATUS, "success");
			response.add(DATA, new Gson().toJsonTree(pair));
		} catch (Exception e) {
			logger.error("******************************************");
			logger.error("Unable to perform a call to TTS WebService", e);
			logger.error("******************************************");
			response.addProperty(STATUS, "error");
			response.addProperty(ERROR_MSG, e.getMessage());
		}
		return response.toString();
	}
	
	@RequestMapping(value = UrlTemplate.COMPILE_TRANSLATIONS, method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	public @ResponseBody String compileTranslatePairs(@RequestParam("fileIds") String[] fileIds, 
			@RequestParam("name") String name, 
			HttpSession session) {
		JsonObject response = new JsonObject();
		try {
			CompiledAudio compiledAudio = translationService.compileTranslations(fileIds, name);
			response.addProperty(STATUS, "success");
			response.add(DATA, new Gson().toJsonTree(compiledAudio));
		} catch (Exception e) {
			logger.error("******************************************");
			logger.error("Unable to compile an audio file", e);
			logger.error("******************************************");
			response.addProperty(STATUS, "error");
			response.addProperty(ERROR_MSG, e.getMessage());
		} 
		return response.toString();
	}
}