package com.Brasilprev.gateways.http.jsons.requests;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class JwtRequest implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	@ApiModelProperty(value = "username", required = true, example = "userPrevi")
	private String username;
	
	@ApiModelProperty(value = "any", required = true, example = "any")
	private String password;

	public JwtRequest() {
	}

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}