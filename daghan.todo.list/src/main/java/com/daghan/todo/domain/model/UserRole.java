package com.daghan.todo.domain.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USER_ROLES", catalog = "todolistdb")
public class UserRole {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idUSER_ROLES")
	private Long rolesId;

	
	@Column(name = "authority")
	private String authority = "AUTH";

	@OneToOne
	@JoinColumn(name = "fk_LOGIN_TABLE_id")
	LoginDetail loginDetail;
	
	public LoginDetail getLoginDetail() {
		return loginDetail;
	}

	public void setLoginDetail(LoginDetail loginDetail) {
		this.loginDetail = loginDetail;
	}

	public Long getRolesId() {
		return rolesId;
	}
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}


}
