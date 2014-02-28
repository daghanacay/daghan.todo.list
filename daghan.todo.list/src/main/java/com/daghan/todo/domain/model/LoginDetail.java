package com.daghan.todo.domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "LOGIN_TABLE", catalog = "todolistdb")
// Annotation for JSON marshaling in MVC
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginDetail {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idLOGIN_TABLE")
	private Long loginId;

	@NotNull
	@Size(min = 6, max = 45)
	@Column(name = "login")
	private String login;

	@NotNull
	@Size(min = 6, max = 45)
	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private boolean enabled = true;

	@OneToOne(mappedBy = "loginDetail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private UserData userData;
	
	@OneToOne(mappedBy = "loginDetail", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private UserRole userRole;

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Long getLoginId() {
		return loginId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

}
