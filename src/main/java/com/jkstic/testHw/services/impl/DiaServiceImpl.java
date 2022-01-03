package com.jkstic.testHw.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkstic.testHw.dto.DiaDto;
import com.jkstic.testHw.dto.RecetasDto;
import com.jkstic.testHw.models.Alimentos;
import com.jkstic.testHw.models.Dia;
import com.jkstic.testHw.models.Recetas;
import com.jkstic.testHw.repositories.AlimentoRepository;
import com.jkstic.testHw.repositories.DiaRepository;
import com.jkstic.testHw.repositories.RecetasRepository;
import com.jkstic.testHw.services.DiaService;
import com.jkstic.testHw.transformer.DiaMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DiaServiceImpl implements DiaService {

	@Autowired
	private DiaMapper diaMapper;

	@Autowired
	private DiaRepository diaRepository;
	
	@Autowired
	private RecetasRepository recetasRepository;
	
	@Autowired
	private AlimentoRepository alimentoRepository;
	
	@Override
	public DiaDto createDia(DiaDto dia) throws Exception {
		log.info("createDia");
		Dia diaTarget = diaMapper.diaDtotoDia(dia);
		try {

			diaTarget.getListadoRecetas().forEach(receta -> {
				Optional<Recetas> recetaTarget = recetasRepository.findByNombre(receta.getNombre());
				if (recetaTarget.orElseThrow() != null) {
					receta.setId(recetaTarget.get().getId());
					receta.setDescripcion(recetaTarget.get().getDescripcion());
					receta.setTypo(recetaTarget.get().getTypo());
					if(recetaTarget.get().getListadoAlimentos() != null) {
						receta.setListadoAlimentos(recetaTarget.get().getListadoAlimentos());
					}
				}
			});
			return diaMapper.diaToDiaDto(diaRepository.save(diaTarget));
		} catch (Exception e) {
			throw new Exception("Error " + e.getMessage());
		}
	}

	@Override
	public DiaDto viewDia(Date fecha) throws Exception {
		log.info("viewDia");
		try {
			Optional<Dia> diaTarged = diaRepository.findByFecha(fecha);
			if(diaTarged.isPresent()) {
				return diaMapper.diaToDiaDto(diaTarged.get());
			}else {
				throw new Exception("Dia no existe");
			}
		} catch (Exception e) {
			throw new Exception("Error: " + e.getMessage());
		}
	}

	
	@Override
	public DiaDto updateDia(Date fecha, DiaDto dia) throws Exception {
		log.info("updateDia");
		try {
			Optional<Dia> diaTarged = diaRepository.findByFecha(fecha);
			if(diaTarged.orElseThrow() != null) {
				Dia diaTargetToModificate = diaTarged.get();
				diaTargetToModificate.setFecha(dia.getFecha());
				diaTargetToModificate.setComentario(dia.getComentario());
				
				
				List<Recetas> listadoNewRecetas  = new ArrayList<Recetas>();
				diaTargetToModificate.getListadoRecetas().forEach( receta -> {
					
					Optional<Recetas> recetasTarget = recetasRepository.findByNombre(receta.getNombre());
					if (recetasTarget.isPresent() && isRecetaToDto(receta,dia.getListadoRecetas()) ) {
						
						receta.setId(recetasTarget.get().getId());
						receta.getListadoAlimentos().forEach(alimento -> {
							Optional<Alimentos> alimentoTarget = alimentoRepository.findByNombre(alimento.getNombre());
							if (alimentoTarget.orElseThrow() != null) {
								alimento.setId(alimentoTarget.get().getId());
							}
						});
						listadoNewRecetas.add(receta);
					}
				});
				diaTargetToModificate.setListadoRecetas(listadoNewRecetas);
				return diaMapper.diaToDiaDto(diaRepository.save(diaTargetToModificate));
			}
			return null;
		} catch (Exception e) {
			throw new Exception("Error: " + e.getMessage());
		}
	}
	
	private Boolean isRecetaToDto(Recetas receta, List<RecetasDto> listado) {
		AtomicBoolean result = new AtomicBoolean(false);
		listado.forEach( rec -> {
			if(rec.getNombre().equals(receta.getNombre())) {
				result.set(true);
			}
		});
		return result.get();
	}

	
	@Override
	public void deleteDia(Date fecha) throws Exception {
		log.info("deleteDia");
		try {
			Optional<Dia> diaTarged = diaRepository.findByFecha(fecha);
			if(diaTarged.isPresent()) {
				diaRepository.delete(diaTarged.get());
			}else {
				throw new Exception("Dia no existe");
			}
		} catch (Exception e) {
			throw new Exception("Error: " + e.getMessage());
		}
	}

	
	@Override
	public List<DiaDto> viewDias(Date from, Date to) throws Exception {
		log.info("deleteDia");
		try {
			Optional<List<Dia>> diasTarged = diaRepository.findByFechaBetween(from,to);
			if(diasTarged.isPresent()) {
				return diaMapper.listDiaToListDiaDto(diasTarged.get());
			}else {
				throw new Exception("Dias no existentes");
			}
		} catch (Exception e) {
			throw new Exception("Error: " + e.getMessage());
		}
	}

}
