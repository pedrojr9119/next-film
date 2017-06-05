package com.nextfilme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nextfilme.domain.Ator;
import com.nextfilme.repository.AtorRepository;

@Service
public class AtorService {

	@Autowired
	private AtorRepository atorRepository;
	
	@Transactional(readOnly=true)
	private boolean exist(Integer id) {
		return atorRepository.exists(id);
	}

	@Transactional(readOnly=true)
	public Ator buscaPorId(Integer id) {
		/*if (!exist(id)) {
			throw new EntityNotFoundException("Ator com o id " + id + " não existe!");
		}*/
		
		return atorRepository.findOne(id);
	}

	@Transactional(readOnly=true)
	public List<Ator> buscaTodos() {
		return atorRepository.findAll();
	}

	@Transactional(readOnly=false)
	public Ator save(Ator ator) {
		return atorRepository.save(ator);
	}

	@Transactional(readOnly=false)
	public void atualizar(Ator ator) {
		atorRepository.save(ator);
	}

	@Transactional(readOnly=false)
	public void delete(Ator ator) {
		atorRepository.delete(ator);
	}
	
}
