package com.nextfilme.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nextfilme.domain.Sessao;
import com.nextfilme.domain.Usuario;
import com.nextfilme.domain.Video;
import com.nextfilme.repository.SessaoRepository;

@Service
public class SessaoService {

	@Autowired
	private SessaoRepository sessaoRepository;

	@Transactional(readOnly=true)
	public Sessao buscaPorId(Integer id) {
		return sessaoRepository.findOne(id);
	}
	
	@Transactional(readOnly=true)
	public Sessao buscaPorIdUsuario(Usuario usuario) {
		return sessaoRepository.findByUsuario(usuario);
	}

	@Transactional(readOnly=false)
	public Sessao atualizar(Sessao sessao) {
		return sessaoRepository.save(sessao);
	}
	
	@Transactional(readOnly=true)
	public Sessao findByVideoAndUsuario(Video video, Usuario usuario) {
		return sessaoRepository.findByVideoAndUsuario(video, usuario);
	}
	
}
