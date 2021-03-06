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
@Table(name = "users")
@NamedQueries({
	@NamedQuery(name = "findUserByEmail", query = "select DISTINCT(usr) from User usr left join fetch usr.compiledAudios where usr.email = :email"),
	@NamedQuery(name = "findUserByFBEmail", query = "select DISTINCT(usr) from User usr left join fetch usr.compiledAudios where usr.fbEmail = :fbEmail")
})
public class User {
	
	public static interface Constants {
		public static final String QUERY_FIND_BY_EMAIL = "findUserByEmail";
		public static final String QUERY_FIND_BY_FB_EMAIL = "findUserByFBEmail";
		
		public static final String PARAM_EMAIL = "email";
		public static final String PARAM_FB_EMAIL = "fbEmail";
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;
	
	@Column(name = "username", nullable = false)
	private String username;
	
	@Column(name = "password", nullable = false)
	private String password;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "fbEmail", unique = true)
	private String fbEmail;
	
	@Column(name = "profilePicUrl")
	private String profilePicUrl;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE})
	@JoinTable(name="user_compiled_audios",
				joinColumns=@JoinColumn(name="user_id"),
				inverseJoinColumns=@JoinColumn(name="compiled_audio_id"))
	private List<CompiledAudio> compiledAudios = new ArrayList<CompiledAudio>();

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + userId;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ "]";
	}

	public String getProfilePicUrl() {
		return profilePicUrl;
	}

	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}

	public String getFbEmail() {
		return fbEmail;
	}

	public void setFbEmail(String fbEmail) {
		this.fbEmail = fbEmail;
	}

	public List<CompiledAudio> getCompiledAudios() {
		return compiledAudios;
	}

	public void addCompiledAudio(CompiledAudio audio) {
		compiledAudios.add(audio);
	}
	
	public void removeCompiledAudio(CompiledAudio audio) {
		compiledAudios.remove(audio);
	}
}