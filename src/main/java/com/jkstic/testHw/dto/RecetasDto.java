package com.jkstic.testHw.dto;

import java.io.Serializable;
import java.util.List;

import com.jkstic.testHw.enums.TypeRecetaEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecetasDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombre;
	private String descripcion;
	private List<AlimentoDto> listadoAlimentos;
	private TypeRecetaEnum typo;
}
