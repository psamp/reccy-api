package com.ficcy.api.rep;

import javax.validation.constraints.NotNull;

public class RegistrationRequest {
	@NotNull
	private String email;
	@NotNull
	private String username;
	@NotNull
	private String password;
	@NotNull
	private String first;
	@NotNull
	private String last;

	public RegistrationRequest() {

	}

	public RegistrationRequest(String email, String username, String password) {
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

}
