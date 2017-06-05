package com.nextfilme.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nextfilme.domain.Video;
import com.nextfilme.repository.VideoRepository;

@Service
public class VideoService {

	@Autowired
	private VideoRepository videoRepository;
	
	@Transactional(readOnly=true)
	private boolean exist(Integer id) {
		return videoRepository.exists(id);
	}

	@Transactional(readOnly=true)
	public Video buscaPorId(Integer id) {
		/*if (!exist(id)) {
			throw new EntityNotFoundException("Avaria com o id " + id + " não existe!");
		}*/
		
		return videoRepository.findOne(id);
	}

	@Transactional(readOnly=true)
	public List<Video> buscaTodos() {
		return videoRepository.findAll();
	}

	@Transactional(readOnly=false)
	public Video save(Video video) {
		return videoRepository.save(video);
	}

	@Transactional(readOnly=false)
	public void atualizar(Video video) {
		videoRepository.save(video);
	}

	@Transactional(readOnly=false)
	public void delete(Video video) {
		videoRepository.delete(video);
	}
	
}
