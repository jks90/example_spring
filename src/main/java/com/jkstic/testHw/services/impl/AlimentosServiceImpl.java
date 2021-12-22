package com.jkstic.testHw.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkstic.testHw.dto.AlimentoDto;
import com.jkstic.testHw.models.Alimentos;
import com.jkstic.testHw.repositories.AlimentoRepository;
import com.jkstic.testHw.services.AlimentosService;
import com.jkstic.testHw.transformer.AlimentosMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlimentosServiceImpl implements AlimentosService{
	
	@Autowired
	private AlimentosMapper alimentosMapper;
	
	@Autowired
	private AlimentoRepository alimentoRepository;
	
	

	@Override
	public AlimentoDto createAliment(AlimentoDto aliment){
		log.info("createAliment: ", aliment.getNombre());
		return alimentosMapper.alimentosToAlimentoDto(alimentoRepository.save(alimentosMapper.alimentoDtotoAlimentos(aliment)));
	}

	@Override
	public AlimentoDto verAliment(Long id) {
		log.info("verAliment: ", id);
		return alimentosMapper.alimentosToAlimentoDto(alimentoRepository.findById(id).get());
	}

	@Override
	public AlimentoDto verAliment(String nombre) {
		log.info("verAliment: ", nombre);
		return alimentosMapper.alimentosToAlimentoDto(alimentoRepository.findByNombre(nombre));
	}

	@Override
	public AlimentoDto actualizarAlimento(Long id, AlimentoDto aliment) {
		log.info("actualizarAlimento: ", aliment.getNombre());
		Alimentos alimentToModificated = alimentoRepository.findById(id).get();
		alimentToModificated.setNombre(aliment.getNombre());
		alimentToModificated.setCantidad(aliment.getCantidad());
		alimentToModificated.setMedida(aliment.getMedida());
		return alimentosMapper.alimentosToAlimentoDto(alimentoRepository.save(alimentToModificated));
	}

	@Override
	public void deleteAliment(Long id) {
		log.info("deleteAliment: ", id);
		alimentoRepository.deleteById(id);
	}

	@Override
	public List<AlimentoDto> verAlimentos() {
		log.info("verAlimentos");
		List<Alimentos> listado = alimentoRepository.findAll();
		return listado.stream().map(x -> alimentosMapper.alimentosToAlimentoDto(x)).collect(Collectors.toList());
	}

}
