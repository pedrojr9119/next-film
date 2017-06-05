package com.nextfilme.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sessao implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Video video;
	
	private Double tempo;
	
	private String status;
	
	public Sessao() {
	}
	
	public Sessao(Integer id, Double tempo, String status, Usuario usuario, Video video) {
		super();
		this.id = id;
		this.tempo = tempo;
		this.status = status;
		this.usuario = usuario;
		this.video = video;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Video getVideo() {
		return video;
	}
	
	public void setVideo(Video video) {
		this.video = video;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sessao other = (Sessao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
