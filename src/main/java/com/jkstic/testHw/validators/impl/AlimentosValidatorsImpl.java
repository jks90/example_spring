package com.jkstic.testHw.validators.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkstic.testHw.dto.AlimentoDto;
import com.jkstic.testHw.repositories.AlimentoRepository;
import com.jkstic.testHw.validators.AlimentosValidators;

@Service
public class AlimentosValidatorsImpl implements AlimentosValidators {

	final int MIN_MEDIDA = 25;
	
	@Autowired
	private AlimentoRepository alimentoRepository;

	@Override
	public void alimentosValidatos(AlimentoDto alimento) throws Exception {
		if (alimento.getCantidad() < MIN_MEDIDA) {
			throw new Exception("Medida no apta.");
		}

		if (alimentoRepository.findByNombre(alimento.getNombre()).isPresent()) {
			throw new Exception("Este alimento ya existe.");
		}
	}

}
