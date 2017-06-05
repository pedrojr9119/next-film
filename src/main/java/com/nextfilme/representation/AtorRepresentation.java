package com.nextfilme.representation;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nextfilme.domain.Ator;

public class AtorRepresentation extends ResourceSupport {

	@JsonInclude(Include.NON_NULL)
	private Integer identifier;
	
	@JsonInclude(Include.NON_NULL)
	private String nome;
	
	public AtorRepresentation() {
	}
	
	public AtorRepresentation(Ator ator) {
		this.identifier = ator.getId();
		this.nome = ator.getNome();
	}
	
	public static Ator build(AtorRepresentation representation) {
		
		Ator ator = new Ator();
		ator.setId(representation.getIdentifier());
		ator.setNome(representation.getNome());
		
		return ator;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
