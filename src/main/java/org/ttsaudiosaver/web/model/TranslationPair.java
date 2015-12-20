package org.ttsaudiosaver.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "translation_pairs")
public class TranslationPair {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "translation_pair_id")
	private int translationPairId;
	
	@Column(name = "file_id", nullable = false)
	private String fileId;
	
	@Column(name = "to_translate", nullable = false)
	private String toTranslate;
	
	@Column(name = "translation_result", nullable = false)
	private String translationResult;
	
	@Column(name = "translate_from_lang", nullable = false)
	private String translateFromLang;
	
	@Column(name = "translate_to_lang", nullable = false)
	private String translateToLang;
	
	@ManyToOne
	@JoinColumn(name = "compiled_audio_id")
	private CompiledAudio compiledAudio;

	public int getTranslationPairId() {
		return translationPairId;
	}

	public void setTranslationPairId(int translationPairId) {
		this.translationPairId = translationPairId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getToTranslate() {
		return toTranslate;
	}

	public void setToTranslate(String toTranslate) {
		this.toTranslate = toTranslate;
	}

	public String getTranslationResult() {
		return translationResult;
	}

	public void setTranslationResult(String translationResult) {
		this.translationResult = translationResult;
	}

	public String getTranslateFromLang() {
		return translateFromLang;
	}

	public void setTranslateFromLang(String translateFromLang) {
		this.translateFromLang = translateFromLang;
	}

	public String getTranslateToLang() {
		return translateToLang;
	}

	public void setTranslateToLang(String translateToLang) {
		this.translateToLang = translateToLang;
	}

	public CompiledAudio getCompiledAudio() {
		return compiledAudio;
	}

	public void setCompiledAudio(CompiledAudio compiledAudio) {
		this.compiledAudio = compiledAudio;
	}
}