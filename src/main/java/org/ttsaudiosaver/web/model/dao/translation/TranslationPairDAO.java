package org.ttsaudiosaver.web.model.dao.translation;

import java.util.List;

import org.ttsaudiosaver.web.model.TranslationPair;

public interface TranslationPairDAO {

	void saveTranslationPair(TranslationPair translationPair);
	List<TranslationPair> findAllTranslationPairs();
	void deleteById(String translationPairId);
	void deleteByAudioFileId(String translationPairFileId);
	TranslationPair findById(String translationPairId);
	List<TranslationPair> findTranslationPairsByFileIds(List<String> ids);
	TranslationPair findByAudioFileId(String translationPairFileId);
	void updateTranslationPair(TranslationPair translationPair);
	
}