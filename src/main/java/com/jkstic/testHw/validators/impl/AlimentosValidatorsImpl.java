package com.jkstic.testHw.validators.impl;

import org.springframework.stereotype.Service;

import com.jkstic.testHw.dto.AlimentoDto;
import com.jkstic.testHw.validators.AlimentosValidators;

@Service
public class AlimentosValidatorsImpl implements AlimentosValidators {

	@Override
	public void alimentosValidatos(AlimentoDto alimento) throws Exception {
		if(alimento.getCantidad() < 25) {
			 throw new Exception("Medida no apta.");
		}
	}

}
