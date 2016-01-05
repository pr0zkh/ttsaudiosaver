package org.ttsaudiosaver.web.model;

import java.util.ArrayList;
import java.util.Date;
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
	
	@Column(name = "creation_date")
	private Date creationDate;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	@JoinTable(name="compiled_audio_translations",
					joinColumns=@JoinColumn(name="compiled_audio_id"),
					inverseJoinColumns=@JoinColumn(name="translation_pair_id"))
	private List<TranslationPair> pairsIncluded = new ArrayList<TranslationPair>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="user_compiled_audios",
					joinColumns=@JoinColumn(name="compiled_audio_id"),
					inverseJoinColumns=@JoinColumn(name="user_id"))
	private List<User> users = new ArrayList<User>();
	
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "CompiledAudio [compiledAudioId=" + compiledAudioId + ", fileId=" + fileId + ", name=" + name
				+ ", creationDate=" + creationDate + ", pairsIncluded=" + pairsIncluded + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + compiledAudioId;
		result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((fileId == null) ? 0 : fileId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((pairsIncluded == null) ? 0 : pairsIncluded.hashCode());
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
		CompiledAudio other = (CompiledAudio) obj;
		if (compiledAudioId != other.compiledAudioId)
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (fileId == null) {
			if (other.fileId != null)
				return false;
		} else if (!fileId.equals(other.fileId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (pairsIncluded == null) {
			if (other.pairsIncluded != null)
				return false;
		} else if (!pairsIncluded.equals(other.pairsIncluded))
			return false;
		return true;
	}

	public List<User> getUsers() {
		return users;
	}
}