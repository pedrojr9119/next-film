package com.nextfilme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nextfilme.domain.Categoria;
import com.nextfilme.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Transactional(readOnly=true)
	private boolean exist(Integer id) {
		return categoriaRepository.exists(id);
	}

	@Transactional(readOnly=true)
	public Categoria buscaPorId(Integer id) {
		/*if (!exist(id)) {
			throw new EntityNotFoundException("Categoria com o id " + id + " não existe!");
		}*/
		
		return categoriaRepository.findOne(id);
	}

	@Transactional(readOnly=true)
	public List<Categoria> buscaTodos() {
		return categoriaRepository.findAll();
	}

	@Transactional(readOnly=false)
	public Categoria save(Categoria video) {
		return categoriaRepository.save(video);
	}

	@Transactional(readOnly=false)
	public void atualizar(Categoria video) {
		categoriaRepository.save(video);
	}

	@Transactional(readOnly=false)
	public void delete(Categoria video) {
		categoriaRepository.delete(video);
	}
	
}
