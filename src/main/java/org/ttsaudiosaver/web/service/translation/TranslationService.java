package org.ttsaudiosaver.web.service.translation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.ttsaudiosaver.web.model.CompiledAudio;
import org.ttsaudiosaver.web.model.TranslationPair;
import org.ttsaudiosaver.web.model.dao.translation.CompiledAudioDAO;
import org.ttsaudiosaver.web.model.dao.translation.TranslationPairDAO;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class TranslationService {
	
	private static final String TTS_WS_ENDPOINT_URL = "http://localhost:8081/translate_tts";
	private static final String TTS_COMPILE_WS_ENDPOINT_URL = "http://localhost:8081/compile_tts";
	private static final String TO_TRANSLATE_PARAM = "from";
	private static final String FROM_LANG = "fromLang";
	private static final String TO_LANG = "toLang";
	private static final String TRANSLATION = "translation";
	private static final String FILE_ID = "fileId";
	private static final String FILE_IDS = "fileIds";
	
	@Autowired
	@Qualifier(value = "compiledAudioDao")
	private CompiledAudioDAO compiledAudioDAO;
	
	@Autowired
	@Qualifier(value = "translationPairDao")
	private TranslationPairDAO translationPairDAO;
	
	public TranslationPair getTranslation(String toTranslate, String fromLang, String toLang) throws URISyntaxException, IOException {
		TranslationPair pair = new TranslationPair();
		URIBuilder uriBuilder = new URIBuilder(TTS_WS_ENDPOINT_URL);
		uriBuilder.addParameter(TO_TRANSLATE_PARAM, toTranslate);
		uriBuilder.addParameter(FROM_LANG, fromLang);
		uriBuilder.addParameter(TO_LANG, toLang);
		URL url = uriBuilder.build().toURL();
		String response = getResponse(url);
		if(response != null) {
			Map<String, String> resultSet = getTranslaitonFromReponse(response);
			pair.setFileId(resultSet.get(FILE_ID));
			pair.setToTranslate(toTranslate);
			pair.setTranslationResult(resultSet.get(TRANSLATION));
			pair.setTranslateToLang(toLang);
			pair.setTranslateFromLang(fromLang);
			translationPairDAO.saveTranslationPair(pair);
		}
		return pair;
	}
	
	public CompiledAudio compileTranslations(String[] fileIds, String name) throws URISyntaxException, IOException {
		CompiledAudio compiledAudio = new CompiledAudio();
		URIBuilder uriBuilder = new URIBuilder(TTS_COMPILE_WS_ENDPOINT_URL);
		for(String fileId : fileIds) {
			uriBuilder.addParameter(FILE_IDS, fileId);
		}
		URL url = uriBuilder.build().toURL();
		String response = getResponse(url);
		if(response != null) {
			List<TranslationPair> pairs = translationPairDAO.findTranslationPairsByFileIds(Arrays.asList(fileIds));
			compiledAudio.setPairsIncluded(pairs);
			compiledAudio.setFileId(getCompiledFileId(response));
			compiledAudio.setName(name);
			compiledAudioDAO.saveCompiledAudio(compiledAudio);
		}
		return compiledAudio;
	}
	
	private String getResponse(URL url) throws IOException {
		InputStream is = null;
		StringBuilder response = new StringBuilder();
		try {
			is = url.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String tmp = "";
			while(null != (tmp = br.readLine())) {
				response.append(tmp);
			}
		} finally {
			if(is != null) {
				is.close();
			}
		}
		return response.toString();
	}
	
	private Map<String, String> getTranslaitonFromReponse(String response) {
		JsonElement jelement = new JsonParser().parse(response);
	    JsonObject  jobject = jelement.getAsJsonObject();
	    String translation = jobject.get("translation").getAsString();
	    String fileId = jobject.get("fileId").getAsString();
	    Map<String, String> result = new HashMap<String, String>();
	    result.put(TRANSLATION, translation);
	    result.put(FILE_ID, fileId);
	    return result;
	}
	
	private String getCompiledFileId(String response) {
		JsonElement jelement = new JsonParser().parse(response);
		 JsonObject  jobject = jelement.getAsJsonObject();
		 return jobject.get("compiledFileId").getAsString();
	}
}