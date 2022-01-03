package com.jkstic.testHw.dto;

import java.io.Serializable;

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
public class StockDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long cantidadStock;
}
