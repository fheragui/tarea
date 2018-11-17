package org.fheragui.dao;

import org.fheragui.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentaDAO extends JpaRepository<Venta, Integer> {

}
