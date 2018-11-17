package org.fheragui.service.impl;

import java.util.List;

import org.fheragui.dao.IProductoDAO;
import org.fheragui.model.Producto;
import org.fheragui.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private IProductoDAO dao;

	@Override
	public Producto registrar(Producto t) {
		return dao.save(t);
	}

	@Override
	public Producto modificar(Producto t) {
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		dao.delete(id);
	}

	@Override
	public Producto listById(int id) {
		return dao.findOne(id);
	}

	@Override
	public List<Producto> listar() {
		return dao.findAll();
	}
}
