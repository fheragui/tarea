package org.fheragui.service;

import java.util.List;

public interface ICRUD<T> {

	T registrar(T t);

	T modificar(T t);

	void eliminar(int id);

	T listById(int id);

	List<T> listar();

}
