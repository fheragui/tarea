package org.fheragui.service.impl;

import java.util.List;

import org.fheragui.dao.IPersonaDAO;
import org.fheragui.model.Persona;
import org.fheragui.service.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaDAO dao;

	@Override
	public Persona registrar(Persona t) {
		return dao.save(t);
	}

	@Override
	public Persona modificar(Persona t) {
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		dao.delete(id);
	}

	@Override
	public Persona listById(int id) {
		return dao.findOne(id);
	}

	@Override
	public List<Persona> listar() {
		return dao.findAll();
	}

}
