package com.nextfilme.representation;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nextfilme.domain.Categoria;
import com.nextfilme.domain.Video;

public class VideoRepresentation extends ResourceSupport {

	@JsonInclude(Include.NON_NULL)
	private Integer identifier;
	
	@JsonInclude(Include.NON_NULL)
	private CategoriaRepresentation categoriaRepresentation;
	
	@JsonInclude(Include.NON_NULL)
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	private Date duracao;
	
	@JsonInclude(Include.NON_NULL)
	private String sinopse;
	
	@JsonInclude(Include.NON_NULL)
	private String direcao;
	
	@JsonInclude(Include.NON_NULL)
	private String url;
	
	@JsonInclude(Include.NON_NULL)
	private String capa;
	
	public VideoRepresentation() {
	}

	public VideoRepresentation(Video video) {
		super();
		this.identifier = video.getId();
		this.nome = video.getNome();
		this.duracao = video.getDuracao();
		this.sinopse = video.getSinopse();
		this.direcao = video.getDirecao();
		this.url = video.getUrl();
		this.capa = video.getCapa();
		
		if (video.getCategoria() != null) {
			Categoria categoria = video.getCategoria();
			CategoriaRepresentation categoriaRepresentation = new CategoriaRepresentation(categoria);
			this.categoriaRepresentation = categoriaRepresentation;
		}
	}
	
	public static Video build(VideoRepresentation representation) {
		Video video = new Video();
		video.setId(representation.getIdentifier());
		video.setNome(representation.getNome());
		video.setDuracao(representation.getDuracao());
		video.setSinopse(representation.getSinopse());
		video.setDirecao(representation.getDirecao());
		video.setUrl(representation.getUrl());
		video.setCapa(representation.getCapa());
		
		if (representation.getCategoriaRepresentation() != null) {
			video.setCategoria(CategoriaRepresentation.build(representation.getCategoriaRepresentation()));
		}
		
		return video;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	
	public CategoriaRepresentation getCategoriaRepresentation() {
		return categoriaRepresentation;
	}
	
	public void setCategoriaRepresentation(CategoriaRepresentation categoriaRepresentation) {
		this.categoriaRepresentation = categoriaRepresentation;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDuracao() {
		return duracao;
	}

	public void setDuracao(Date duracao) {
		this.duracao = duracao;
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getDirecao() {
		return direcao;
	}

	public void setDirecao(String direcao) {
		this.direcao = direcao;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}
}
