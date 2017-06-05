package com.nextfilme.representation;

import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nextfilme.domain.Categoria;

public class CategoriaRepresentation extends ResourceSupport {

	@JsonInclude(Include.NON_NULL)
	private Integer identifier;
	
	@JsonInclude(Include.NON_NULL)
	private String categoria;
	
	private List<VideoRepresentation> videoRepresentations;
	
	public CategoriaRepresentation() {
	}
	
	public CategoriaRepresentation(com.nextfilme.domain.Categoria categoria) {
		this.identifier = categoria.getId();
		this.categoria = categoria.getCategoria();
	}
	
	public static Categoria build(CategoriaRepresentation representation) {
		
		Categoria categoria = new Categoria();
		categoria.setId(representation.getIdentifier());
		categoria.setCategoria(representation.getCategoria());
		
		return categoria;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}

	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public List<VideoRepresentation> getVideoRepresentations() {
		return videoRepresentations;
	}
	
	public void setVideoRepresentations(List<VideoRepresentation> videoRepresentations) {
		this.videoRepresentations = videoRepresentations;
	}
	
}
