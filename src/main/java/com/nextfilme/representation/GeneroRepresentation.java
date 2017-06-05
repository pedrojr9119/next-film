package com.nextfilme.representation;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nextfilme.domain.Genero;

public class GeneroRepresentation extends ResourceSupport {

	@JsonInclude(Include.NON_NULL)
	private Integer identifier;
	
	@JsonInclude(Include.NON_NULL)
	private String genero;
	
	public GeneroRepresentation() {
	}

	public GeneroRepresentation(Genero genero) {
		super();
		this.identifier = genero.getId();
		this.genero = genero.getGenero();
	}
	
	public static Genero build(GeneroRepresentation representation) {
		Genero genero = new Genero();
		genero.setId(representation.getIdentifier());
		genero.setGenero(representation.getGenero());
		
		return genero;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
}
