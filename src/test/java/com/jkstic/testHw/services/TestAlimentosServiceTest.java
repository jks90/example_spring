package com.jkstic.testHw.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import com.jkstic.testHw.services.impl.AlimentosServiceImpl;

@SpringBootTest
public class TestAlimentosServiceTest {

	@TestConfiguration
	static class AlimentosServiceTestContextConfiguration {
		
		@Bean 
		public AlimentosService AlimentosService() {
			return new AlimentosServiceImpl();
		}
	}
	
	
	@SuppressWarnings("unused")
	@Autowired
	private AlimentosService alimentosService;
	
	@Test
	public void when_findallAliment_then_listOK() {
		assertEquals(1, 1);
	}
}
