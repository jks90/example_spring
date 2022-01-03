package com.jkstic.testHw.services;

import java.util.List;
import java.util.Map;

import com.jkstic.testHw.dto.AlimentoDto;

public interface AlimentosService {
	
	public AlimentoDto createAliment(AlimentoDto aliment)throws Exception;
	public AlimentoDto verAliment(Long id);
	public AlimentoDto verAliment(String nombre);
	public AlimentoDto actualizarAlimento(Long id, AlimentoDto aliment);
	public void deleteAliment(Long id) throws Exception;
	public List<AlimentoDto> verAlimentos();
	public Map<Long,String> verIdAlimentos();

}
