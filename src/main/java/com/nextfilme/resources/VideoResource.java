package com.nextfilme.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nextfilme.domain.Video;
import com.nextfilme.representation.VideoRepresentation;
import com.nextfilme.service.VideoService;

@RestController
@RequestMapping("/videos")
public class VideoResource {
	
	@Autowired
	private VideoService videoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<List<VideoRepresentation>> buscaTodos() {
		
		List<Video> videos = videoService.buscaTodos();
		List<VideoRepresentation> representations = new ArrayList<>();
		
		videos.forEach(video -> {
			video.setSinopse(video.getSinopse().substring(0, 40) + "...");
			representations.add(new VideoRepresentation(video));
		});
		
		return ResponseEntity.ok(representations);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<VideoRepresentation> buscaPorId(@PathVariable("id") Integer id) {
		
		Video video = videoService.buscaPorId(id);
		VideoRepresentation representation = new VideoRepresentation(video);
		
		return ResponseEntity.ok(representation);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> salvar(@Valid @RequestBody VideoRepresentation representation) {
		
		Video video = videoService.save(VideoRepresentation.build(representation));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(video.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> atualizar(@RequestBody VideoRepresentation representation, @PathVariable("id") Integer id) {
		
		representation.setIdentifier(id);
		videoService.atualizar(VideoRepresentation.build(representation));
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<VideoRepresentation> delete(@PathVariable("id") Integer id) {
		
		Video video = videoService.buscaPorId(id);
		VideoRepresentation representation = new VideoRepresentation(video);
		videoService.delete(video);
		
		return ResponseEntity.ok(representation);
		
	}
}
