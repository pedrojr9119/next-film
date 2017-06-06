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

import com.nextfilme.domain.Favorito;
import com.nextfilme.representation.FavoritoRepresentation;
import com.nextfilme.service.FavoritoService;

@RestController
@RequestMapping("/favoritos")
public class FavoritoResource {
	
	@Autowired
	private FavoritoService favoritoService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<List<FavoritoRepresentation>> buscaTodos() {
		
		List<Favorito> favoritos = favoritoService.buscaTodos();
		List<FavoritoRepresentation> representations = new ArrayList<>();
		
		favoritos.forEach(favorito -> representations.add(new FavoritoRepresentation(favorito)));
		
		return ResponseEntity.ok(representations);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<FavoritoRepresentation> buscaPorId(@PathVariable("id") Integer id) {
		
		Favorito favorito = favoritoService.buscaPorId(id);
		FavoritoRepresentation representation = new FavoritoRepresentation(favorito);
		
		return ResponseEntity.ok(representation);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> salvar(@Valid @RequestBody FavoritoRepresentation representation) {
		
		Favorito favorito = favoritoService.save(FavoritoRepresentation.build(representation));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(favorito.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> atualizar(@RequestBody FavoritoRepresentation representation, @PathVariable("id") Integer id) {
		
		representation.setIdentifier(id);
		favoritoService.atualizar(FavoritoRepresentation.build(representation));
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<FavoritoRepresentation> delete(@PathVariable("id") Integer id) {
		
		Favorito favorito = favoritoService.buscaPorId(id);
		FavoritoRepresentation representation = new FavoritoRepresentation(favorito);
		favoritoService.delete(favorito);
		
		return ResponseEntity.ok(representation);
		
	}
}
