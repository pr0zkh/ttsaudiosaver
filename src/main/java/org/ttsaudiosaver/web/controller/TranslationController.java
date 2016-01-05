package org.ttsaudiosaver.web.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.ttsaudiosaver.web.SessionAttributes;
import org.ttsaudiosaver.web.model.CompiledAudio;
import org.ttsaudiosaver.web.model.TranslationPair;
import org.ttsaudiosaver.web.model.User;
import org.ttsaudiosaver.web.service.profile.ProfileService;
import org.ttsaudiosaver.web.service.translation.TranslationService;

import com.google.gson.JsonObject;

@Controller
public class TranslationController {
	
	private static final Logger logger = Logger.getLogger(TranslationController.class);
	private static final String STATUS = "status";
	private static final String DATA = "data";
	private static final String ERROR_MSG = "errorMsg";
	
	@Autowired
	private TranslationService translationService;
	
	@Autowired
	private ProfileService profileService;
	
	@RequestMapping(value = UrlTemplate.TRANSLATE, method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public ModelAndView translate(@RequestParam("toTranslate") String toTranslate, 
			@RequestParam("fromLang") String fromLang, 
			@RequestParam("toLang") String toLang) {
		ModelAndView model = new ModelAndView(ViewMap.TRANSLATION_PAIR.getView());
		try {
			TranslationPair pair = translationService.getTranslation(toTranslate, fromLang, toLang);
			model.addObject("translationPair", pair);
			model.addObject(STATUS, "success");
		} catch (Exception e) {
			logger.error("******************************************");
			logger.error("Unable to perform a call to TTS WebService", e);
			logger.error("******************************************");
			model.addObject(ERROR_MSG, e.getMessage());
			model.addObject(STATUS, "error");
		}
		return model;
	}
	
	@RequestMapping(value = UrlTemplate.COMPILE_TRANSLATIONS, method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public ModelAndView compileTranslatePairs(@RequestParam("fileIds") String[] fileIds, 
			@RequestParam("name") String name, 
			HttpSession session) {
		ModelAndView model = new ModelAndView(ViewMap.COMPILE_AUDIO.getView());
		User user = (User)session.getAttribute(SessionAttributes.USER);
		try {
			CompiledAudio compiledAudio = translationService.compileTranslations(fileIds, name);
			user = profileService.addAudio(user, compiledAudio);
			session.setAttribute(SessionAttributes.USER, user);
			model.addObject("audio", compiledAudio);
			model.addObject(STATUS, "success");
		} catch (Exception e) {
			logger.error("******************************************");
			logger.error("Unable to compile an audio file", e);
			logger.error("******************************************");
			model.addObject(ERROR_MSG, e.getMessage());
			model.addObject(STATUS, "error");
		} 
		return model;
	}
	
	@RequestMapping(value = UrlTemplate.UPDATE_TRANSLATION, method = RequestMethod.POST, produces = "text/html; charset=utf-8")
	public ModelAndView updateTranslation(@RequestParam("toTranslate") String toTranslate, 
			@RequestParam("translation") String translation,
			@RequestParam("fromLang") String fromLang, 
			@RequestParam("toLang") String toLang) {
		ModelAndView model = new ModelAndView(ViewMap.UPDATE_TRANSLATION.getView());
		try {
			TranslationPair pair = translationService.updateTranslation(toTranslate, translation, fromLang, toLang);
			model.addObject("translationPair", pair);
			model.addObject(STATUS, "success");
		} catch (Exception e) {
			logger.error("******************************************");
			logger.error("Unable to perform a call to TTS WebService", e);
			logger.error("******************************************");
			model.addObject(ERROR_MSG, e.getMessage());
			model.addObject(STATUS, "error");
		}
		return model;
	}
	
	@RequestMapping(value = UrlTemplate.TRANSLATION_DETAILS, method = RequestMethod.GET)
	public ModelAndView getTranslationInfoPage(@PathVariable("audioId") String audioId, HttpSession session) {
		ModelAndView model = new ModelAndView();
		User user = (User)session.getAttribute(SessionAttributes.USER);
		CompiledAudio audioToUpdate = null;
		for(CompiledAudio audio : user.getCompiledAudios()) {
			if(audio.getFileId().equals(audioId)) {
				audioToUpdate = audio;
				break;
			}
		}
		model.setViewName(ViewMap.TRANSLATION_DETAILS.getView());
		model.addObject("audio", audioToUpdate);
		return model;
	}
	
	@RequestMapping(value = UrlTemplate.COMPILE_AUDIO, method = RequestMethod.POST)
	public @ResponseBody String compileAudio(@RequestParam("fileIds") String[] fileIds) {
		JsonObject response = new JsonObject();
		try {
			String compiledAudioFileId = translationService.compileAudio(fileIds);
			response.addProperty(STATUS, "success");
			response.addProperty(DATA, compiledAudioFileId);
		} catch (URISyntaxException | IOException e) {
			response.addProperty(STATUS, "error");
			response.addProperty(DATA, e.getMessage());
		}
		return response.toString();
	}
	
	@RequestMapping(value = UrlTemplate.REMOVE_AUDIO, method = RequestMethod.POST)
	public @ResponseBody String removeAudio(@RequestParam("id") Integer id, HttpSession session) {
		JsonObject response = new JsonObject();
		User user = (User)session.getAttribute(SessionAttributes.USER);
		try{
			CompiledAudio audio = null;
			for(CompiledAudio ca : user.getCompiledAudios()) {
				if(ca.getCompiledAudioId() == id) {
					audio = ca;
					break;
				}
			}
			user.removeCompiledAudio(audio);
			translationService.removeAudio(audio);
			response.addProperty(STATUS, "success");
		} catch (Exception e) {
			response.addProperty(STATUS, "error");
		}
		return response.toString();
	}
	
	@RequestMapping(value = UrlTemplate.UPDATE_EXISTING_AUDIO, method = RequestMethod.POST)
	public @ResponseBody String updateExistingAudio(@RequestParam("id") Integer id, 
			@RequestParam("fileId") String fileId, 
			@RequestParam("fileIds") String[] fileIds, 
			HttpSession session) {
		JsonObject response = new JsonObject();
		User user = (User)session.getAttribute(SessionAttributes.USER);
		try{
			CompiledAudio audio = null;
			for(CompiledAudio ca : user.getCompiledAudios()) {
				if(ca.getCompiledAudioId() == id) {
					audio = ca;
					break;
				}
			}
			user.removeCompiledAudio(audio);
			CompiledAudio updated = translationService.updateExistingAudio(fileId, audio, fileIds);
			user.addCompiledAudio(updated);
			response.addProperty(STATUS, "success");
		} catch (Exception e) {
			response.addProperty(STATUS, "error");
		}
		return response.toString();
	}
}