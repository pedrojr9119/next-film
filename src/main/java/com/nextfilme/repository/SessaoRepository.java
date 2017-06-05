package com.nextfilme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextfilme.domain.Sessao;
import com.nextfilme.domain.Usuario;
import com.nextfilme.domain.Video;

@Repository
public interface SessaoRepository extends JpaRepository<Sessao, Integer> {

	Sessao findByUsuario(Usuario usuario);
	
	Sessao findByVideoAndUsuario(Video video, Usuario usuario);
	
}
