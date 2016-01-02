package org.ttsaudiosaver.web.model.dao.translation;

import org.ttsaudiosaver.web.model.TranslationPair;

public interface TranslationPairDAO {

	void saveTranslationPair(TranslationPair translationPair);
	void deleteTranslationPair(TranslationPair translationPair);
	TranslationPair findTranslationPairById(String translationPairId);
	TranslationPair findTranslationPairByFileId(String fileId);
	void updateTranslationPair(TranslationPair translationPair);
	
}