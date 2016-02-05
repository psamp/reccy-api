package com.ficcy.api.rep;

import javax.validation.constraints.NotNull;

public class AuthRequest {
	
	@NotNull
	private String login;
	@NotNull
	private String password;

	public String getLogin() {
		return login;
	}

	public void setLogin(String usernameOrEmail) {
		this.login = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
