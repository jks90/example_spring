package com.jkstic.testHw.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.jkstic.testHw.dto.AlimentoDto;
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
	
	@Autowired
	private AlimentosValidators alimentosValidators;

	@Test
	public void when_validateAliment_then_statusException() {
         
        AlimentoDto aliment = new AlimentoDto("", 20, "gr");
        
        Exception exception = assertThrows(Exception.class, () -> alimentosValidators.alimentosValidatos(aliment));
        
        assertEquals("Medida no apta.", exception.getMessage());
	}
}
