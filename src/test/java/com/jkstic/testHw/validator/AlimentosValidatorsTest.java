package com.jkstic.testHw.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
import com.jkstic.testHw.validators.AlimentosValidators;
import com.jkstic.testHw.validators.impl.AlimentosValidatorsImpl;

@SpringBootTest
public class AlimentosValidatorsTest {
	
	@TestConfiguration
	static class AlimentosValidatorsTestContextConfiguration {
		
		@Bean 
		public AlimentosValidators alimentosValidators() {
			return new AlimentosValidatorsImpl();
		}
	}
	
	@MockBean
	private AlimentoRepository alimentoRepository;
	
	@Autowired
	private AlimentosValidators alimentosValidators;

	@Test
	public void when_validateAliment_then_statusExceptionMedidaNoApta() {
         
        AlimentoDto aliment = new AlimentoDto("", 20L, "gr",new StockDto());
        
        Exception exception = assertThrows(Exception.class, () -> alimentosValidators.alimentosValidatos(aliment));
        
        assertEquals("Medida no apta.", exception.getMessage());
	}
	
	@Test
	public void when_validateAliment_then_statusException() {
		
		
         
        AlimentoDto aliment = new AlimentoDto("", 30L, "gr",new StockDto());
        Alimentos ali1 = new Alimentos(0L,"" ,30L, "gr",null, new Stock());
        
        Mockito.when(alimentoRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.of(ali1));
        
        
        Exception exception = assertThrows(Exception.class, () -> alimentosValidators.alimentosValidatos(aliment));
        
        assertEquals("Este alimento ya existe.", exception.getMessage());
	}
	
	@Test
	public void when_validateAliment_then_OK() throws Exception {
		
        AlimentoDto aliment = new AlimentoDto("", 30L, "gr",new StockDto());
        
        alimentosValidators.alimentosValidatos(aliment);
        
	}
}
