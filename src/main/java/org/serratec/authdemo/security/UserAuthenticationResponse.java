package org.serratec.authdemo.security;

public class UserAuthenticationResponse {

	private final String token;

	public UserAuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;

	}
}
