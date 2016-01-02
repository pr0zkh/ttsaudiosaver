package org.ttsaudiosaver.web.controller;import org.apache.commons.lang3.StringUtils;

public enum ViewMap {
	
	SIGN_UP(UrlTemplate.SIGN_UP, "signup"),
	FORGOT_PASSWORD(UrlTemplate.FORGOT_PASSWORD, "forgotPassword"),
	LOGIN(UrlTemplate.LOGIN, "login"),
	INDEX(UrlTemplate.INDEX, "index"),
	USER_DETAILS(UrlTemplate.USER_DETAILS, "userDetails"),
	TRANSLATION_PAIR(UrlTemplate.TRANSLATE, "translation/translationPairRenderer"),
	UPDATE_TRANSLATION(UrlTemplate.UPDATE_TRANSLATION, "translation/updatedTranslationRenderer"),
	COMPILE_AUDIO(UrlTemplate.COMPILE_TRANSLATIONS, "translation/compiledTranslationRenderer"),
	TRANSLATION_DETAILS(UrlTemplate.TRANSLATION_DETAILS, "translation/translationDetails");
	
	private String urlTemplate;
	private String view;
	
	private ViewMap(String urlTemplate, String view) {
		this.urlTemplate = urlTemplate;
		this.view = view;
	}
	
	public String getUrlTemplate() {
		return this.urlTemplate;
	}
	
	public String getView() {
		return this.view;
	}
	
	public ViewMap getViewMapByUrlTemplate(String urlTemplate) {
		if(StringUtils.isNotBlank(urlTemplate)) {
			for(ViewMap viewMap : values()) {
				if(urlTemplate.equals(viewMap.getUrlTemplate())) {
					return viewMap;
				}
			}
		}
		return null;
	}
}