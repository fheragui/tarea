package org.fheragui.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Obejeto Produto")
@Entity
@Table(name = "producto", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre" }) })
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;
	
	@ApiModelProperty(notes = "nombre del producto maximo 30 caracteres")
	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;
	
	@ApiModelProperty(notes = "marca del producto maximo 15 caracteres")
	@Column(name = "marca", nullable = false, length = 15)
	private String marca;

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

}
