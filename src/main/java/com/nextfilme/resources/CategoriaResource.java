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

import com.nextfilme.domain.Categoria;
import com.nextfilme.representation.CategoriaRepresentation;
import com.nextfilme.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<List<CategoriaRepresentation>> buscaTodos() {
		
		List<Categoria> categorias = categoriaService.buscaTodos();
		List<CategoriaRepresentation> representations = new ArrayList<>();
		
		categorias.forEach(categoria -> representations.add(new CategoriaRepresentation(categoria)));
		
		return ResponseEntity.ok(representations);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<CategoriaRepresentation> buscaPorId(@PathVariable("id") Integer id) {
		
		Categoria categoria = categoriaService.buscaPorId(id);
		CategoriaRepresentation representation = new CategoriaRepresentation(categoria);
		
		return ResponseEntity.ok(representation);
	}
	
	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> salvar(@Valid @RequestBody CategoriaRepresentation representation) {
		
		Categoria categoria = categoriaService.save(CategoriaRepresentation.build(representation));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Void> atualizar(@RequestBody CategoriaRepresentation representation, @PathVariable("id") Integer id) {
		
		representation.setIdentifier(id);
		categoriaService.atualizar(CategoriaRepresentation.build(representation));
		
		return ResponseEntity.noContent().build();
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<CategoriaRepresentation> delete(@PathVariable("id") Integer id) {
		
		Categoria categoria = categoriaService.buscaPorId(id);
		CategoriaRepresentation representation = new CategoriaRepresentation(categoria);
		categoriaService.delete(categoria);
		
		return ResponseEntity.ok(representation);
		
	}
}
