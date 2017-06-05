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

import com.nextfilme.domain.Genero;
import com.nextfilme.representation.GeneroRepresentation;
import com.nextfilme.service.GeneroService;

@RestController
@RequestMapping("/generos")
public class GeneroResource {
	
	@Autowired
	private GeneroService generoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<List<GeneroRepresentation>> buscaTodos() {
		
		List<Genero> generos = generoService.buscaTodos();
		List<GeneroRepresentation> representations = new ArrayList<>();
		
		generos.forEach(genero -> representations.add(new GeneroRepresentation(genero)));
		
		return ResponseEntity.ok(representations);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<GeneroRepresentation> buscaPorId(@PathVariable("id") Integer id) {
		
		Genero genero = generoService.buscaPorId(id);
		GeneroRepresentation representation = new GeneroRepresentation(genero);
		
		return ResponseEntity.ok(representation);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> salvar(@Valid @RequestBody GeneroRepresentation representation) {
		
		Genero genero = generoService.save(GeneroRepresentation.build(representation));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(genero.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> atualizar(@RequestBody GeneroRepresentation representation, @PathVariable("id") Integer id) {
		
		representation.setIdentifier(id);
		generoService.atualizar(GeneroRepresentation.build(representation));
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<GeneroRepresentation> delete(@PathVariable("id") Integer id) {
		
		Genero genero = generoService.buscaPorId(id);
		GeneroRepresentation representation = new GeneroRepresentation(genero);
		generoService.delete(genero);
		
		return ResponseEntity.ok(representation);
		
	}
}
