package org.ttsaudiosaver.web.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<TranslationPair> pairsIncluded = new HashSet<TranslationPair>();
	
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

	public Set<TranslationPair> getPairsIncluded() {
		return pairsIncluded;
	}

	public void addTranslationPair(TranslationPair translationPair) {
		pairsIncluded.add(translationPair);
	}
}