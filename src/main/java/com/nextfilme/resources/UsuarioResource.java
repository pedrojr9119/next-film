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

import com.nextfilme.domain.Usuario;
import com.nextfilme.representation.UsuarioRepresentation;
import com.nextfilme.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<List<UsuarioRepresentation>> buscaTodos() {
		
		List<Usuario> usuarios = usuarioService.buscaTodos();
		List<UsuarioRepresentation> representations = new ArrayList<>();
		
		usuarios.forEach(usuario -> representations.add(new UsuarioRepresentation(usuario)));
		
		return ResponseEntity.ok(representations);
	}
	
	@RequestMapping(value = "/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<UsuarioRepresentation> buscaPorLogin(@PathVariable("login") String login) {
		
		Usuario usuario = usuarioService.buscaPorLogin(login);
		UsuarioRepresentation representation = new UsuarioRepresentation(usuario);
		
		return ResponseEntity.ok(representation);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> save(@Valid @RequestBody UsuarioRepresentation representation) {
		
		Usuario usuario = usuarioService.salvar(UsuarioRepresentation.build(representation));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
