package org.fheragui.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.fheragui.exception.ModeloNotFoundException;
import org.fheragui.model.Venta;
import org.fheragui.service.IVentaService;
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
@RequestMapping("/ventas")
public class VentaController {
	@Autowired
	IVentaService service;

	@GetMapping(produces = "application/json")
	public ResponseEntity<List<Venta>> listar() {
		List<Venta> pro = new ArrayList<>();
		pro = service.listar();
		return new ResponseEntity<List<Venta>>(pro, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public Resource<Venta> listarPorId(@PathVariable("id") Integer id) {
		Venta v = service.listById(id);
		if (v == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		}
		Resource<Venta> resource = new Resource<Venta>(v);
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("venta-resource"));
		return resource;
	}

	@PostMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Venta> registrar(@RequestBody Venta venta) {
		Venta v = service.registrar(venta);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(v.getIdVenta())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(produces = "application/json", consumes = "application/json")
	public ResponseEntity<Venta> modificar(@RequestBody Venta venta) {
		service.modificar(venta);
		return new ResponseEntity<Venta>(HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		Venta v = service.listById(id);
		if (v == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		} else {
			service.eliminar(id);
		}
	}
}
