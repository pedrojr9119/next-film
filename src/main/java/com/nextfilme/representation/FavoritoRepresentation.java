package com.nextfilme.representation;

import java.util.Date;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nextfilme.domain.Favorito;
import com.nextfilme.domain.Usuario;
import com.nextfilme.domain.Video;

public class FavoritoRepresentation extends ResourceSupport {

	@JsonInclude(Include.NON_NULL)
	private Integer identifier;
	
	@JsonInclude(Include.NON_NULL)
	private String nome;
	
	@JsonInclude(Include.NON_NULL)
	private Date dataCriacao;
	
	@JsonInclude(Include.NON_NULL)
	private VideoRepresentation videoRepresentation;

	@JsonInclude(Include.NON_NULL)
	private UsuarioRepresentation usuarioRepresentation;
	
	public FavoritoRepresentation() {
	}

	public FavoritoRepresentation(Favorito favorito) {
		super();
		this.identifier = favorito.getId();
		this.nome = favorito.getNome();
		this.dataCriacao = favorito.getDataCriacao();
		
		if (favorito.getVideo() != null) {
			Video video = favorito.getVideo();
			VideoRepresentation videoRepresentation = new VideoRepresentation(video);
			this.videoRepresentation = videoRepresentation;
		}
		
		if (favorito.getUsuario() != null) {
			Usuario usuario = favorito.getUsuario();
			UsuarioRepresentation usuarioRepresentation = new UsuarioRepresentation(usuario);
			this.usuarioRepresentation = usuarioRepresentation;
		}
	}
	
	public static Favorito build(FavoritoRepresentation representation) {
		Favorito favorito = new Favorito();
		favorito.setId(representation.getIdentifier());
		favorito.setNome(representation.getNome());
		favorito.setDataCriacao(representation.getDataCriacao());
		
		if (representation.getVideoRepresentation() != null) {
			favorito.setVideo(VideoRepresentation.build(representation.getVideoRepresentation()));
		}
		
		if (representation.getUsuarioRepresentation() != null) {
			favorito.setUsuario(UsuarioRepresentation.build(representation.getUsuarioRepresentation()));
		}
		
		return favorito;
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

	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public VideoRepresentation getVideoRepresentation() {
		return videoRepresentation;
	}
	
	public void setVideoRepresentation(VideoRepresentation videoRepresentation) {
		this.videoRepresentation = videoRepresentation;
	}
	
	public UsuarioRepresentation getUsuarioRepresentation() {
		return usuarioRepresentation;
	}
	
	public void setUsuarioRepresentation(UsuarioRepresentation usuarioRepresentation) {
		this.usuarioRepresentation = usuarioRepresentation;
	}
}
