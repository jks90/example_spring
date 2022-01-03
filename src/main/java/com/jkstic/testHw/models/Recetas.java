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

import com.jkstic.testHw.enums.TypeRecetaEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="RECETAS")
public class Recetas implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private String descripcion;
	
	@ManyToMany
	@JoinTable(
	  name = "recetas_alimento", 
	  joinColumns = @JoinColumn(name = "recetas_id"), 
	  inverseJoinColumns = @JoinColumn(name = "alimentos_id"))
	private List<Alimentos> listadoAlimentos;
	
	@ManyToMany(mappedBy = "listadoRecetas")
	private List<Dia> listadoDias;
	
	private TypeRecetaEnum typo;

}
