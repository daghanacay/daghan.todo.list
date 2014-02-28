package com.daghan.todo.domain.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Registration {
	@NotNull
	@Size(min = 6, max = 45)
	private String email;
	@NotNull
	@Size(min = 6, max = 45)
	private String password;
	@NotNull
	@Size(min = 2, max = 45)
	private String firstName;
	@NotNull
	@Size(min = 2, max = 45)
	private String lastName;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
