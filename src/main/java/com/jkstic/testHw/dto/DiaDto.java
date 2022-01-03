package com.jkstic.testHw.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class DiaDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private Date fecha;
	
	private String comentario;
	
	private List<RecetasDto> listadoRecetas;

}
