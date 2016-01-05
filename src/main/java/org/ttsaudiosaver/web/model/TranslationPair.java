package org.ttsaudiosaver.web.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@Column(name = "seq_num")
	private Integer seqNumber;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="compiled_audio_translations",
					joinColumns=@JoinColumn(name="translation_pair_id"),
					inverseJoinColumns=@JoinColumn(name="compiled_audio_id"))
	private List<CompiledAudio> compiledAudios = new ArrayList<CompiledAudio>();

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

	public Integer getSeqNumber() {
		return seqNumber;
	}

	public void setSeqNumber(Integer seqNumber) {
		this.seqNumber = seqNumber;
	}

	@Override
	public String toString() {
		return "TranslationPair [translationPairId=" + translationPairId + ", fileId=" + fileId + ", toTranslate="
				+ toTranslate + ", translationResult=" + translationResult + ", translateFromLang=" + translateFromLang
				+ ", translateToLang=" + translateToLang + ", seqNumber=" + seqNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		result = prime * result + ((seqNumber == null) ? 0 : seqNumber.hashCode());
		result = prime * result + ((toTranslate == null) ? 0 : toTranslate.hashCode());
		result = prime * result + ((translateFromLang == null) ? 0 : translateFromLang.hashCode());
		result = prime * result + ((translateToLang == null) ? 0 : translateToLang.hashCode());
		result = prime * result + translationPairId;
		result = prime * result + ((translationResult == null) ? 0 : translationResult.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TranslationPair other = (TranslationPair) obj;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		if (seqNumber == null) {
			if (other.seqNumber != null)
				return false;
		} else if (!seqNumber.equals(other.seqNumber))
			return false;
		if (toTranslate == null) {
			if (other.toTranslate != null)
				return false;
		} else if (!toTranslate.equals(other.toTranslate))
			return false;
		if (translateFromLang == null) {
			if (other.translateFromLang != null)
				return false;
		} else if (!translateFromLang.equals(other.translateFromLang))
			return false;
		if (translateToLang == null) {
			if (other.translateToLang != null)
				return false;
		} else if (!translateToLang.equals(other.translateToLang))
			return false;
		if (translationPairId != other.translationPairId)
			return false;
		if (translationResult == null) {
			if (other.translationResult != null)
				return false;
		} else if (!translationResult.equals(other.translationResult))
			return false;
		return true;
	}

	public List<CompiledAudio> getCompiledAudios() {
		return compiledAudios;
	}
}