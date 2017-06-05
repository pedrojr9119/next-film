package com.nextfilme.representation;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nextfilme.domain.Sessao;
import com.nextfilme.domain.Usuario;
import com.nextfilme.domain.Video;

public class SessaoRepresentation extends ResourceSupport {

	@JsonInclude(Include.NON_NULL)
	private Integer identifier;
	
	@JsonInclude(Include.NON_NULL)
	private UsuarioRepresentation usuarioRepresentation;
	
	@JsonInclude(Include.NON_NULL)
	private VideoRepresentation videoRepresentation;
	
	@JsonInclude(Include.NON_NULL)
	private Double tempo;
	
	@JsonInclude(Include.NON_NULL)
	private String status;
	
	public SessaoRepresentation() {
	}

	public SessaoRepresentation(Sessao sessao) {
		super();
		this.identifier = sessao.getId();
		this.tempo = sessao.getTempo();
		this.status = sessao.getStatus();
		
		if (sessao.getUsuario() != null) {
			Usuario usuario = sessao.getUsuario();
			UsuarioRepresentation usuarioRepresentation = new UsuarioRepresentation(usuario);
			this.usuarioRepresentation = usuarioRepresentation;			
		}
		
		if (sessao.getVideo() != null) {
			Video video = sessao.getVideo();
			VideoRepresentation videoRepresentation = new VideoRepresentation(video);
			this.videoRepresentation = videoRepresentation;
		}
	}
	
	public static Sessao build(SessaoRepresentation representation) {
		Sessao sessao = new Sessao();
		sessao.setId(representation.getIdentifier());
		sessao.setTempo(representation.getTempo());
		sessao.setStatus(representation.getStatus());
		
		if (representation.getUsuarioRepresentation() != null) {
			sessao.setUsuario(UsuarioRepresentation.build(representation.getUsuarioRepresentation()));
		}
		
		if (representation.getVideoRepresentation() != null) {
			sessao.setVideo(VideoRepresentation.build(representation.getVideoRepresentation()));
		}
		
		return sessao;
	}

	public Integer getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Integer identifier) {
		this.identifier = identifier;
	}
	
	public UsuarioRepresentation getUsuarioRepresentation() {
		return usuarioRepresentation;
	}
	
	public void setUsuarioRepresentation(UsuarioRepresentation usuarioRepresentation) {
		this.usuarioRepresentation = usuarioRepresentation;
	}
	
	public VideoRepresentation getVideoRepresentation() {
		return videoRepresentation;
	}
	
	public void setVideoRepresentation(VideoRepresentation videoRepresentation) {
		this.videoRepresentation = videoRepresentation;
	}

	public Double getTempo() {
		return tempo;
	}
	
	public void setTempo(Double tempo) {
		this.tempo = tempo;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
