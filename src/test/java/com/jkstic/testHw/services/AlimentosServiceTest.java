package com.jkstic.testHw.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.jkstic.testHw.dto.AlimentoDto;
import com.jkstic.testHw.dto.StockDto;
import com.jkstic.testHw.models.Alimentos;
import com.jkstic.testHw.models.Stock;
import com.jkstic.testHw.repositories.AlimentoRepository;
import com.jkstic.testHw.services.impl.AlimentosServiceImpl;

@SpringBootTest
public class AlimentosServiceTest {

	@TestConfiguration
	static class AlimentosServiceTestContextConfiguration {
		
		@Bean 
		public AlimentosService alimentosService() {
			return new AlimentosServiceImpl();
		}
	}
	
	@Autowired
	private AlimentosService alimentosService;
	
	@MockBean
	private AlimentoRepository alimentoRepository;
	
	
	@Test
	public void when_findallAliment_then_listOK() {
		
		Stock stock = new Stock();
		Alimentos ali1 = new Alimentos(0L,"harina" ,50L, "gr",null, stock);
		Alimentos ali2 = new Alimentos(1L,"leche" ,200L, "ml",null, stock);
		Alimentos ali3 = new Alimentos(2L,"azucar" ,100L, "gr",null, stock);
		
		Mockito.when(alimentoRepository.findAll()).thenReturn(Arrays.asList(ali1,ali2,ali3));
		
		List<AlimentoDto> result = alimentosService.verAlimentos();
		
		assertEquals(3, result.size());
		assertEquals("leche", result.get(1).getNombre());
	}
	
	@Test
	public void when_saveAliment_then_ok() throws Exception {
		
		AlimentoDto aliDto = new AlimentoDto("harina" ,50L, "gr",new StockDto());
		Alimentos ali1 = new Alimentos(0L,"harina" ,50L, "gr",null, new Stock());
		
		Mockito.when(alimentoRepository.save(Mockito.any(Alimentos.class))).thenReturn(ali1);
		
		AlimentoDto result = alimentosService.createAliment(aliDto);
		
		assertEquals("harina", result.getNombre());
		
	}
	
	@Test
	public void when_updateAliment_then_ok() {
		
		AlimentoDto aliDto = new AlimentoDto("harina" ,50L, "gr",new StockDto());
		Alimentos ali1 = new Alimentos(0L,"leche" ,50L, "gr",null, new Stock());
		
		Mockito.when(alimentoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(ali1));
		Mockito.when(alimentoRepository.save(Mockito.any(Alimentos.class))).thenReturn(ali1);
		
		AlimentoDto result = alimentosService.actualizarAlimento(0L,aliDto);
		
		assertEquals("harina", result.getNombre());
		
	}
	
	@Test
	public void when_findByNameAliment_then_ok() {
		
		Alimentos ali1 = new Alimentos(0L,"leche" ,50L, "gr",null, new Stock());
		
		Mockito.when(alimentoRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.of(ali1));
		
		AlimentoDto result = alimentosService.verAliment("leche");
		
		assertEquals("leche", result.getNombre());
		
	}
}
