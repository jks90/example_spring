package com.jkstic.testHw.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="RECETAS")
public class Recetas implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	@ManyToMany
	@JoinTable(
	  name = "recetas_alimento", 
	  joinColumns = @JoinColumn(name = "recetas_id"), 
	  inverseJoinColumns = @JoinColumn(name = "alimentos_id"))
	private List<Alimentos> listadoAlimentos;

	public Recetas() {
		super();
	}

	public Recetas(Long id, String nombre, List<Alimentos> listadoAlimentos) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.listadoAlimentos = listadoAlimentos;
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

	public List<Alimentos> getListadoAlimentos() {
		return listadoAlimentos;
	}

	public void setListadoAlimentos(List<Alimentos> listadoAlimentos) {
		this.listadoAlimentos = listadoAlimentos;
	}

	@Override
	public String toString() {
		return "Recetas [id=" + id + ", nombre=" + nombre + ", listadoAlimentos=" + listadoAlimentos + "]";
	}
	
}
