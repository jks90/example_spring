package com.jkstic.testHw.validators.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkstic.testHw.dto.RecetasDto;
import com.jkstic.testHw.models.Recetas;
import com.jkstic.testHw.repositories.RecetasRepository;
import com.jkstic.testHw.validators.RecetasValidators;

@Service
public class RecetasValidatorsImpl implements RecetasValidators {
	
	@Autowired
	private RecetasRepository recetasRepository;

	@Override
	public void recetasValidator(RecetasDto receta) throws Exception {
		if(receta.getListadoAlimentos().size() < 0) {
			throw new Exception("Necesitas mas de un alimento.");
		}
		
		if(recetasRepository.findByNombre(receta.getNombre()).isPresent()) {
			throw new Exception("Nombre de receta existente.");
		}
	}

	@Override
	public void existReceta(String name) throws Exception {
		Optional<Recetas> recetaTarget = recetasRepository.findByNombre(name);
		if(!recetaTarget.isPresent()) {
			throw new Exception("Receta no existente: " + name);
		}
	}

}
