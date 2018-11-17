package org.fheragui.service.impl;

import java.util.List;

import org.fheragui.dao.IVentaDAO;
import org.fheragui.model.Venta;
import org.fheragui.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaServiceImpl implements IVentaService {

	@Autowired
	private IVentaDAO dao;

	@Transactional
	@Override
	public Venta registrar(Venta t) {
		t.getVentaDetalles().forEach(d -> {
			d.setVenta(t);
		});
		return dao.save(t);
	}

	@Override
	public Venta modificar(Venta t) {
		return dao.save(t);
	}

	@Override
	public void eliminar(int id) {
		dao.delete(id);
	}

	@Override
	public Venta listById(int id) {
		return dao.findOne(id);
	}

	@Override
	public List<Venta> listar() {
		return dao.findAll();
	}

}
