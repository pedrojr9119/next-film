package com.nextfilme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nextfilme.domain.PapelUsuario;
import com.nextfilme.domain.Usuario;
import com.nextfilme.exception.EntityAlreadyExistException;
import com.nextfilme.repository.PapelUsuarioRepository;
import com.nextfilme.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PapelUsuarioRepository papelUsuarioRepository;
	
	@Transactional(readOnly=false)
	public Usuario salvar(Usuario usuario) {
		if (usuario.getId() != null && exist(usuario.getId())) {
			throw new EntityAlreadyExistException("Usuario com o id " + usuario.getId() + " já existe!");
		}
		
		PapelUsuario papelUsuario = new PapelUsuario();
		papelUsuario.setUsuario(usuario.getLogin());
		papelUsuario.setPapel("ROLE_USER");
		
		papelUsuarioRepository.save(papelUsuario);
		
		usuario.setHabilitado(true);
		
		return usuarioRepository.save(usuario);
	}
	
	@Transactional(readOnly=true)
	private boolean exist(Integer id) {
		return usuarioRepository.exists(id);
	}

	@Transactional(readOnly=true)
	public Usuario buscaPorLogin(String login) {
		/*if (!exist(id)) {
			throw new EntityNotFoundException("Usuário com o login " + login + " não existe!");
		}*/
		
		return usuarioRepository.findByLogin(login);
	}

	@Transactional(readOnly=true)
	public List<Usuario> buscaTodos() {
		return usuarioRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Usuario buscaPorId(Integer id) {	
		return usuarioRepository.findOne(id);
	}
	
	@Transactional(readOnly=false)
	public void atualizar(Usuario usuario) {
		usuarioRepository.save(usuario);
	}

	@Transactional(readOnly=false)
	public void delete(Usuario usuario) {
		usuarioRepository.delete(usuario);
	}
}
