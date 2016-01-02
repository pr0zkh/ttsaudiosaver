package org.ttsaudiosaver.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "translation_pairs")
@NamedQueries({
	@NamedQuery(name = "findByFileId", query = "select DISTINCT(pair) from TranslationPair pair where pair.fileId = :fileId")
})
public class TranslationPair {
	
	public static interface Constants {
		public static final String QUERY_FIND_BY_FILE_ID = "findByFileId";
		
		public static final String PARAM_FILE_ID = "fileId";
	}

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
}