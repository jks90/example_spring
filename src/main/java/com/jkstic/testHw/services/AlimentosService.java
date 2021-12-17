package com.jkstic.testHw.services;

import java.util.List;

import com.jkstic.testHw.dto.AlimentoDto;

public interface AlimentosService {
	
	public AlimentoDto createAliment(AlimentoDto aliment);
	public AlimentoDto verAliment(Long id);
	public AlimentoDto verAliment(String nombre);
	public AlimentoDto actualizarAlimento(Long id, AlimentoDto aliment);
	public void deleteAliment(Long id);
	public List<AlimentoDto> verAlimentos();

}
