package com.jkstic.testHw.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jkstic.testHw.dto.AlimentoDto;
import com.jkstic.testHw.dto.StockDto;
import com.jkstic.testHw.services.AlimentosService;
import com.jkstic.testHw.validators.AlimentosValidators;

@SpringBootTest
public class AlimentosControllerTest {
	
	@InjectMocks
	private AlimentosController alimentosController;
	
	@Mock
	private AlimentosService alimentosService;
	
	@Mock
	private AlimentosValidators alimentosValidators;

	@Test
	public void when_createAliment_then_statusOK() throws Exception {
		
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        AlimentoDto aliment = new AlimentoDto("papa", 200L, "gr", new StockDto());
        
        Mockito.when(alimentosService.createAliment(any(AlimentoDto.class))).thenReturn(aliment);
         
        ResponseEntity<?> responseEntity = alimentosController.createAliment(aliment);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void when_createAliment_then_statusError() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        AlimentoDto aliment = new AlimentoDto("papa", 2L, "gr",new StockDto());
        
        Mockito.doThrow(new Exception()).when(alimentosValidators).alimentosValidatos(aliment);
        
        Mockito.when(alimentosService.createAliment(any(AlimentoDto.class))).thenReturn(aliment);
         
        ResponseEntity<?> responseEntity = alimentosController.createAliment(aliment);
         
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
	}
	
}
