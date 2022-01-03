package com.jkstic.testHw.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkstic.testHw.dto.RecetasDto;
import com.jkstic.testHw.models.Alimentos;
import com.jkstic.testHw.models.Recetas;
import com.jkstic.testHw.repositories.AlimentoRepository;
import com.jkstic.testHw.repositories.RecetasRepository;
import com.jkstic.testHw.services.RecetasService;
import com.jkstic.testHw.transformer.RecetasMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecetasServiceImpl implements RecetasService {

	@Autowired
	private RecetasMapper recetasMapper;

	@Autowired
	private RecetasRepository recetasRepository;

	@Autowired
	private AlimentoRepository alimentoRepository;

	@Override
	public RecetasDto createReceta(RecetasDto receta) throws Exception {
		log.info("createReceta");
		try {
			Recetas recetaTarget = recetasMapper.recetasDtotoRecetas(receta);
			recetaTarget.getListadoAlimentos().forEach(alimento -> {
				Optional<Alimentos> alimentoTarget = alimentoRepository.findByNombre(alimento.getNombre());
				if(alimentoTarget.orElseThrow() != null) {
					alimento.setId(alimentoTarget.get().getId());
					alimento.setCantidad(alimentoTarget.get().getCantidad());
					alimento.setMedida(alimentoTarget.get().getMedida());
					alimento.setStock(alimentoTarget.get().getStock());
				}
			});
			return recetasMapper.recetasToRecetasDto(recetasRepository.save(recetaTarget));
		} catch (NoSuchElementException e) {
			throw new Exception("Alimento no encontrado.");
		}
	}

	@Override
	public RecetasDto verReceta(Long id) throws Exception {
		log.info("verReceta");
		Optional<Recetas> receta = recetasRepository.findById(id);
		if (receta.isPresent()) {
			return recetasMapper.recetasToRecetasDto(receta.get());
		} else {
			throw new Exception("Receta no encontrada.");
		}
	}

	@Override
	public RecetasDto verReceta(String nombre) throws Exception {
		log.info("verReceta");
		Optional<Recetas> receta = recetasRepository.findByNombre(nombre);
		if (receta.isPresent()) {
			return recetasMapper.recetasToRecetasDto(receta.get());
		} else {
			throw new Exception("Receta no encontrada.");
		}
	}
//
	@Override
	public RecetasDto actualizarReceta(Long id, RecetasDto receta) throws Exception {
		log.info("actualizarReceta");
		Optional<Recetas> recetaOld = recetasRepository.findById(id);
		if (recetaOld.isPresent()) {

			Recetas recetaTarget = recetasMapper.recetasDtotoRecetas(receta);
			recetaTarget.setId(id);

			try {
				recetaTarget.getListadoAlimentos().forEach(alimento -> {
					Optional<Alimentos> alimentoTarget = alimentoRepository.findByNombre(alimento.getNombre());
					if (alimentoTarget.orElseThrow() != null) {
						alimento.setId(alimentoTarget.get().getId());
					}
				});
			} catch (NoSuchElementException e) {
				throw new Exception("Alimento no encontrado.");
			}

			return recetasMapper.recetasToRecetasDto(recetasRepository.save(recetaTarget));
		} else {
			throw new Exception("Receta no encontrada.");
		}
	}

	@Override
	public void deleteReceta(Long id) throws Exception {
		log.info("deleteReceta");
		Optional<Recetas> receta = recetasRepository.findById(id);
		if (receta.isPresent()) {
			recetasRepository.delete(receta.get());
		} else {
			throw new Exception("Receta no encontrada.");
		}
	}

	@Override
	public List<RecetasDto> verRecetas() throws Exception {
		log.info("verRecetas");
		return recetasMapper.listRecetasToListRecetasDto(recetasRepository.findAll());
	}

	@Override
	public Map<Long, String> verIdRecetas() {
		Map<Long, String> result = new HashMap<Long, String>();
		recetasRepository.findAll().forEach( receta -> {
			result.put(receta.getId(), receta.getNombre());
		});
		return result;
	}
}