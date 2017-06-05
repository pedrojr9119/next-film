package com.nextfilme.representation;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nextfilme.domain.Usuario;

public class UsuarioRepresentation extends ResourceSupport {

	@JsonInclude(Include.NON_NULL)
	private Integer identifier;
	
	@JsonInclude(Include.NON_NULL)
	private String login;
	
	@JsonInclude(Include.NON_NULL)
	private String email;
	
	@JsonInclude(Include.NON_NULL)
	private String senha;
	
	@JsonInclude(Include.NON_NULL)
	private Boolean habilitado;
	
	public UsuarioRepresentation() {
	}

	public UsuarioRepresentation(Usuario usuario) {
		super();
		this.identifier = usuario.getId();
		this.login = usuario.getLogin();
		this.email = usuario.getEmail();
		this.senha = usuario.getSenha();
		this.habilitado = usuario.getHabilitado();
	}
	
	public static Usuario build(UsuarioRepresentation representation) {
		Usuario usuario = new Usuario();
		usuario.setId(representation.getIdentifier());
		usuario.setLogin(representation.getLogin());
		usuario.setEmail(representation.getEmail());
		usuario.setSenha(representation.getSenha());
		usuario.setHabilitado(representation.getHabilitado());
		
		return usuario;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Boolean getHabilitado() {
		return habilitado;
	}
	
	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}
	
}
