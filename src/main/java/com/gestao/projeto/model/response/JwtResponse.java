package com.gestao.projeto.model.response;

import java.util.List;

import com.gestao.projeto.model.Empresa;

import lombok.Data;

@Data
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String username;
	private String email;
	private List<String> roles;
	private Empresa empresa;

	public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, Empresa empresa) {
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.empresa = empresa;
	}
}
