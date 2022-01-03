package com.jkstic.testHw.validators.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkstic.testHw.dto.DiaDto;
import com.jkstic.testHw.models.Dia;
import com.jkstic.testHw.repositories.DiaRepository;
import com.jkstic.testHw.validators.DiaValidators;
import com.jkstic.testHw.validators.RecetasValidators;

@Service
public class DiaValidatorsImpl implements DiaValidators {

	@Autowired
	private RecetasValidators recetasValidators;

	@Autowired
	private DiaRepository diaRepository;

	@Override
	public void diaValidator(DiaDto dia) throws Exception {
		Optional<Dia> diaTarget = diaRepository.findByFecha(dia.getFecha());
		if (!diaTarget.isPresent()) {
			dia.getListadoRecetas().forEach(x -> {
				try {
					recetasValidators.existReceta(x.getNombre());
				} catch (Exception e) {
					throw new RuntimeException("Receta no valida: " + e.getMessage());
				}
			});
		}else {
			throw new Exception("Este dia ya existe!");
		}
	}

}
