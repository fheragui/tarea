package org.fheragui.dao;

import org.fheragui.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoDAO extends JpaRepository<Producto, Integer> {
	
}
