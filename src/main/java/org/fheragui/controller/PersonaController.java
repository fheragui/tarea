package org.fheragui.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.fheragui.exception.ModeloNotFoundException;
import org.fheragui.model.Persona;
import org.fheragui.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/personas")
public class PersonaController {
	@Autowired
	IPersonaService service;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Persona>> listar() {
		List<Persona> personas = new ArrayList<>();
		personas = service.listar();
		return new ResponseEntity<List<Persona>>(personas, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public Resource<Persona> listarPorId(@PathVariable("id") Integer id) {
		Persona p = service.listById(id);
		if (p == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		}
		Resource<Persona> resource = new Resource<Persona>(p);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("persona-resource"));
		return resource;
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> registrar(@RequestBody Persona persona) {
		Persona p = new Persona();
		p = service.registrar(persona);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdPersona())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> modificar(@RequestBody Persona persona) {
		service.modificar(persona);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		Persona p = service.listById(id);
		if (p != null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		} else {
			service.eliminar(id);
		}
	}
}
