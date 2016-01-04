package org.ttsaudiosaver.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "compiled_audios")
public class CompiledAudio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "compiled_audio_id")
	private int compiledAudioId;
	
	@Column(name = "file_id", nullable = false)
	private String fileId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<TranslationPair> pairsIncluded = new ArrayList<TranslationPair>();
	
	public int getCompiledAudioId() {
		return compiledAudioId;
	}

	public void setCompiledAudioId(int compiledAudioId) {
		this.compiledAudioId = compiledAudioId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TranslationPair> getPairsIncluded() {
		return pairsIncluded;
	}

	public void addTranslationPair(TranslationPair translationPair) {
		pairsIncluded.add(translationPair);
	}
}