package com.jkstic.testHw.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkstic.testHw.dto.AlimentoDto;
import com.jkstic.testHw.models.Alimentos;
import com.jkstic.testHw.models.Stock;
import com.jkstic.testHw.repositories.AlimentoRepository;
import com.jkstic.testHw.repositories.StockRepository;
import com.jkstic.testHw.services.AlimentosService;
import com.jkstic.testHw.transformer.AlimentosMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AlimentosServiceImpl implements AlimentosService {

	@Autowired
	private AlimentosMapper alimentosMapper;

	@Autowired
	private AlimentoRepository alimentoRepository;

	@Autowired
	private StockRepository stockRepository;

	@Override
	public AlimentoDto createAliment(AlimentoDto aliment) throws Exception {
		log.info("createAliment: ", aliment.getNombre().toString());
		Alimentos alimentoTarget = alimentosMapper.alimentoDtotoAlimentos(aliment);

		try {

			Stock stockTarget = stockRepository
					.save(Stock.builder().cantidadStock(aliment.getStock().getCantidadStock()).build());

			alimentoTarget.setStock(stockTarget);

			Alimentos alimentoSaved = alimentoRepository.save(alimentoTarget);
			return alimentosMapper.alimentosToAlimentoDto(alimentoSaved);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public AlimentoDto verAliment(Long id) {
		log.info("verAliment: ", id);
		return alimentosMapper.alimentosToAlimentoDto(alimentoRepository.findById(id).get());
	}

	@Override
	public AlimentoDto verAliment(String nombre) {
		log.info("verAliment: ", nombre);
		return alimentosMapper.alimentosToAlimentoDto(alimentoRepository.findByNombre(nombre).get());
	}

	@Override
	public AlimentoDto actualizarAlimento(Long id, AlimentoDto aliment) {
		log.info("actualizarAlimento: ", aliment.getNombre());
		Alimentos alimentToModificated = alimentoRepository.findById(id).get();
		alimentToModificated.setNombre(aliment.getNombre());
		alimentToModificated.setCantidad(aliment.getCantidad());
		alimentToModificated.setMedida(aliment.getMedida());

		if (alimentToModificated.getStock() == null) {
			Stock stockTarget = stockRepository
					.save(Stock.builder().cantidadStock(aliment.getStock().getCantidadStock()).build());
			alimentToModificated.setStock(stockTarget);
		}else {
			alimentToModificated.getStock().setCantidadStock(aliment.getStock().getCantidadStock());
		}

		return alimentosMapper.alimentosToAlimentoDto(alimentoRepository.save(alimentToModificated));
	}

	@Override
	public void deleteAliment(Long id) throws Exception {
		log.info("deleteAliment: ", id);
		Optional<Alimentos> alimento = alimentoRepository.findById(id);
		if (alimento.isPresent()) {
			
			
			alimentoRepository.deleteById(id);
			if(alimento.get().getStock() != null) {
				stockRepository.deleteById(alimento.get().getStock().getId());
			}
		}else {
			throw new Exception("No encontrado el alimento");
		}
			
	}

	@Override
	public List<AlimentoDto> verAlimentos() {
		log.info("verAlimentos");
		List<Alimentos> listado = alimentoRepository.findAll();
		return listado.stream().map(x -> alimentosMapper.alimentosToAlimentoDto(x)).collect(Collectors.toList());
	}

	@Override
	public Map<Long, String> verIdAlimentos() {
		Map<Long, String> result = new HashMap<Long, String>();
		alimentoRepository.findAll().forEach(alimento -> {
			result.put(alimento.getId(), alimento.getNombre());
		});
		return result;
	}

}
