package org.ttsaudiosaver.web.model.dao.translation;

import java.util.List;

import org.ttsaudiosaver.web.model.CompiledAudio;

public interface CompiledAudioDAO {
	
	void saveCompiledAudio(CompiledAudio compiledAudio);
	List<CompiledAudio> findAllCompiledAudios();
	void deleteById(String compiledAudioId);
	void deleteByAudioFileId(String compiledAudioFileId);
	CompiledAudio findById(String compiledAudioId);
	CompiledAudio findByAudioFileId(String compiledAudioFileId);
	void updateCompiledAudio(CompiledAudio compiledAudio);

}
