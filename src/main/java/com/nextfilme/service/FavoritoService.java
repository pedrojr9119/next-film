package com.nextfilme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nextfilme.domain.Favorito;
import com.nextfilme.repository.FavoritoRepository;

@Service
public class FavoritoService {

	@Autowired
	private FavoritoRepository favoritoRepository;
	
	@Transactional(readOnly=true)
	private boolean exist(Integer id) {
		return favoritoRepository.exists(id);
	}

	@Transactional(readOnly=true)
	public Favorito buscaPorId(Integer id) {
		/*if (!exist(id)) {
			throw new EntityNotFoundException("Favorito com o id " + id + " não existe!");
		}*/
		
		return favoritoRepository.findOne(id);
	}

	@Transactional(readOnly=true)
	public List<Favorito> buscaTodos() {
		return favoritoRepository.findAll();
	}

	@Transactional(readOnly=false)
	public Favorito save(Favorito favorito) {
		return favoritoRepository.save(favorito);
	}

	@Transactional(readOnly=false)
	public void atualizar(Favorito favorito) {
		favoritoRepository.save(favorito);
	}

	@Transactional(readOnly=false)
	public void delete(Favorito favorito) {
		favoritoRepository.delete(favorito);
	}
	
}
