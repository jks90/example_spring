package com.jkstic.testHw.validators.impl;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkstic.testHw.dto.DiaDto;
import com.jkstic.testHw.enums.TypeRecetaEnum;
import com.jkstic.testHw.models.Dia;
import com.jkstic.testHw.models.Recetas;
import com.jkstic.testHw.repositories.DiaRepository;
import com.jkstic.testHw.repositories.RecetasRepository;
import com.jkstic.testHw.validators.DiaValidators;
import com.jkstic.testHw.validators.RecetasValidators;

@Service
public class DiaValidatorsImpl implements DiaValidators {

	@Autowired
	private RecetasValidators recetasValidators;
	
	@Autowired
	private RecetasRepository recetasRepository;

	@Autowired
	private DiaRepository diaRepository;
	
	static final int MAX_DESAYUNOS = 1;
	static final int MAX_MERIENDA = 1;
	static final int MAX_CENAS = 2;
	static final int MAX_COMIDAS = 2;

	@Override
	public void createDiaValidator(DiaDto dia) throws Exception {
		Optional<Dia> diaTarget = diaRepository.findByFecha(dia.getFecha());
		if (!diaTarget.isPresent()) {
			AtomicInteger contDesayunos = new AtomicInteger(0);
			AtomicInteger contMerienda = new AtomicInteger(0);
			AtomicInteger contComidas = new AtomicInteger(0);
			AtomicInteger contCenas = new AtomicInteger(0);
			
			dia.getListadoRecetas().forEach(x -> {
				try {
					recetasValidators.existReceta(x.getNombre());
					Recetas receta = recetasRepository.findByNombre(x.getNombre()).get();
					if(receta.getTypo() != null) {
						if (receta.getTypo().equals(TypeRecetaEnum.DESAYUNO)) {
							contDesayunos.incrementAndGet();
						}
						if (receta.getTypo().equals(TypeRecetaEnum.MERIENDA)) {
							contMerienda.incrementAndGet();
						}
						if (receta.getTypo().equals(TypeRecetaEnum.CENA)) {
							contCenas.incrementAndGet();
						}
						if (receta.getTypo().equals(TypeRecetaEnum.COMIDA)) {
							contComidas.incrementAndGet();
						}
					}
				} catch (Exception e) {
					throw new RuntimeException("Receta no valida: " + e.getMessage());
				}
			});
			
			if(contDesayunos.incrementAndGet() > MAX_DESAYUNOS) {
				throw new Exception("No puede añadir mas de un desayuno");
			}
			if(contMerienda.incrementAndGet() > MAX_MERIENDA) {
				throw new Exception("No puede añadir mas de una merienda");
			}
			if(contComidas.incrementAndGet() > MAX_COMIDAS) {
				throw new Exception("No puede mas de dos comidas");
			}
			if(contCenas.incrementAndGet() > MAX_CENAS) {
				throw new Exception("No puede mas de dos cenas");
			}
		}else {
			throw new Exception("Este dia ya existe!");
		}
	}

}
