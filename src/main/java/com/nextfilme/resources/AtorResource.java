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

import com.nextfilme.domain.Ator;
import com.nextfilme.representation.AtorRepresentation;
import com.nextfilme.service.AtorService;

@RestController
@RequestMapping("/atores")
public class AtorResource {
	
	@Autowired
	private AtorService atorService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<List<AtorRepresentation>> buscaTodos() {
		
		List<Ator> atores = atorService.buscaTodos();
		List<AtorRepresentation> representations = new ArrayList<>();
		
		atores.forEach(ator -> representations.add(new AtorRepresentation(ator)));
		
		return ResponseEntity.ok(representations);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<AtorRepresentation> buscaPorId(@PathVariable("id") Integer id) {
		
		Ator ator = atorService.buscaPorId(id);
		AtorRepresentation representation = new AtorRepresentation(ator);
		
		return ResponseEntity.ok(representation);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> salvar(@Valid @RequestBody AtorRepresentation representation) {
		
		Ator ator = atorService.save(AtorRepresentation.build(representation));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ator.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> atualizar(@RequestBody AtorRepresentation representation, @PathVariable("id") Integer id) {
		
		representation.setIdentifier(id);
		atorService.atualizar(AtorRepresentation.build(representation));
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<AtorRepresentation> delete(@PathVariable("id") Integer id) {
		
		Ator ator = atorService.buscaPorId(id);
		AtorRepresentation representation = new AtorRepresentation(ator);
		atorService.delete(ator);
		
		return ResponseEntity.ok(representation);
		
	}
}
