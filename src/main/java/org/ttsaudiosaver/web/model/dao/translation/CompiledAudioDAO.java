package org.ttsaudiosaver.web.model.dao.translation;

import org.ttsaudiosaver.web.model.CompiledAudio;

public interface CompiledAudioDAO {
	
	void saveCompiledAudio(CompiledAudio compiledAudio);
	void deleteCompiledAudio(CompiledAudio compiledAudio);
	CompiledAudio findCompiledAudioById(String compiledAudioId);
	void updateCompiledAudio(CompiledAudio compiledAudio);
}