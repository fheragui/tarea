package org.fheragui.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.fheragui.exception.ModeloNotFoundException;
import org.fheragui.model.Producto;
import org.fheragui.service.IProductoService;
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
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	IProductoService service;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Producto>> listar() {
		List<Producto> pro = new ArrayList<>();
		pro = service.listar();
		return new ResponseEntity<List<Producto>>(pro, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public Resource<Producto> listarPorId(@PathVariable("id") Integer id) {
		Producto p = service.listById(id);
		if (p == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		}
		Resource<Producto> resource = new Resource<Producto>(p);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("producto-resource"));
		return resource;
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Producto> registrar(@RequestBody Producto producto) {
		Producto p = service.registrar(producto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getIdProducto())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Producto> modificar(@RequestBody Producto producto) {
		service.modificar(producto);
		return new ResponseEntity<Producto>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		Producto p = service.listById(id);
		if (p == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		} else {
			service.eliminar(id);
		}

	}
}
