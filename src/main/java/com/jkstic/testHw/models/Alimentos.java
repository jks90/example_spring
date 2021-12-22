package com.jkstic.testHw.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="ALIMENTO")
public class Alimentos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private int cantidad;
	
	private String medida;
	
	@ManyToMany(mappedBy = "listadoAlimentos")
	private List<Recetas> listadoRecetas;

	public Alimentos() {
		super();
	}

	public Alimentos(Long id, String nombre, int cantidad, String medida) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.medida = medida;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public String getMedida() {
		return medida;
	}

	public void setMedida(String medida) {
		this.medida = medida;
	}
	
	public List<Recetas> getListadoRecetas() {
		return listadoRecetas;
	}

	public void setListadoRecetas(List<Recetas> listadoRecetas) {
		this.listadoRecetas = listadoRecetas;
	}

	@Override
	public String toString() {
		return "Alimento [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", medida=" + medida + "]";
	}

}
