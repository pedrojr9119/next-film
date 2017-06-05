package com.nextfilme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nextfilme.domain.Genero;
import com.nextfilme.repository.GeneroRepository;

@Service
public class GeneroService {

	@Autowired
	private GeneroRepository generoRepository;
	
	@Transactional(readOnly=true)
	private boolean exist(Integer id) {
		return generoRepository.exists(id);
	}

	@Transactional(readOnly=true)
	public Genero buscaPorId(Integer id) {
		/*if (!exist(id)) {
			throw new EntityNotFoundException("Genero com o id " + id + " não existe!");
		}*/
		
		return generoRepository.findOne(id);
	}

	@Transactional(readOnly=true)
	public List<Genero> buscaTodos() {
		return generoRepository.findAll();
	}

	@Transactional(readOnly=false)
	public Genero save(Genero genero) {
		return generoRepository.save(genero);
	}

	@Transactional(readOnly=false)
	public void atualizar(Genero genero) {
		generoRepository.save(genero);
	}

	@Transactional(readOnly=false)
	public void delete(Genero genero) {
		generoRepository.delete(genero);
	}
	
}
