package com.daghan.todo.domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "USER_DATA", catalog = "todolistdb")
//Annotation for JSON marshaling in MVC
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserData {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idUSER_DATA")
	private Long userDataId;

	@Size(min = 2, max = 45)
	@Column(name = "first_name")
	private String firstName;

	@Size(min = 2, max = 45)
	@Column(name = "last_name")
	private String lastName;

	@OneToOne
	@JoinColumn(name = "fk_LOGIN_TABLE_id")
	LoginDetail loginDetail;

	public LoginDetail getLoginDetail() {
		return loginDetail;
	}

	public void setLoginDetail(LoginDetail loginDetail) {
		this.loginDetail = loginDetail;
	}

	public Long getUserDataId() {
		return userDataId;
	}

	public void setUserDataId(Long userDataId) {
		this.userDataId = userDataId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
