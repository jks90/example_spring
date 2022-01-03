package com.jkstic.testHw.services;

import java.util.List;
import java.util.Map;

import com.jkstic.testHw.dto.RecetasDto;

public interface RecetasService {

	public RecetasDto createReceta(RecetasDto receta) throws Exception;
	public RecetasDto verReceta(Long id) throws Exception;
	public RecetasDto verReceta(String nombre) throws Exception;
	public RecetasDto actualizarReceta(Long id, RecetasDto receta) throws Exception;
	public void deleteReceta(Long id) throws Exception;
	public List<RecetasDto> verRecetas() throws Exception;
	public Map<Long,String> verIdRecetas();
}
