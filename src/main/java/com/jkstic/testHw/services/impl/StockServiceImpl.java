package com.jkstic.testHw.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jkstic.testHw.repositories.AlimentoRepository;
import com.jkstic.testHw.services.StockService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private AlimentoRepository alimentoRepository;

	@Override
	public Map<String, Long> verStock() {
		Map<String, Long> result = new HashMap<String, Long>();
		alimentoRepository.findAll().forEach(aliment -> {
			log.info(aliment.toString());
			if (aliment.getStock() != null) {
				result.put(aliment.getNombre(), aliment.getStock().getCantidadStock());
			}
		});

		return result;
	}

}
