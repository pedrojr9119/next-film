package com.nextfilme.resources;

import java.net.URI;

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

import com.nextfilme.domain.Sessao;
import com.nextfilme.domain.Usuario;
import com.nextfilme.domain.Video;
import com.nextfilme.representation.SessaoRepresentation;
import com.nextfilme.service.SessaoService;
import com.nextfilme.service.UsuarioService;
import com.nextfilme.service.VideoService;

@RestController
@RequestMapping("/sessao")
public class SessaoResource {
	
	@Autowired
	private SessaoService sessaoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private VideoService videoService;
	
	@RequestMapping(value = "/video/{idVideo}/usuario/{usuarioLogin}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<SessaoRepresentation> buscaPorLogin(@PathVariable("idVideo") Integer idVideo, @PathVariable("usuarioLogin") String usuarioLogin) {
		
		Video video = videoService.buscaPorId(idVideo);
		Usuario usuario = usuarioService.buscaPorLogin(usuarioLogin);
		Sessao sessao = sessaoService.findByVideoAndUsuario(video, usuario);
		SessaoRepresentation representation = new SessaoRepresentation(sessao);
		
		return ResponseEntity.ok(representation);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> atualizar(@Valid @RequestBody SessaoRepresentation representation, @PathVariable("id") Integer id) {
		
		representation.setIdentifier(id);
		sessaoService.atualizar(SessaoRepresentation.build(representation));
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> save(@Valid @RequestBody SessaoRepresentation representation) {
		
		Sessao sessao = sessaoService.atualizar(SessaoRepresentation.build(representation));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sessao.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
