package com.jkstic.testHw.validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.jkstic.testHw.dto.DiaDto;
import com.jkstic.testHw.dto.RecetasDto;
import com.jkstic.testHw.enums.TypeRecetaEnum;
import com.jkstic.testHw.models.Dia;
import com.jkstic.testHw.models.Recetas;
import com.jkstic.testHw.repositories.DiaRepository;
import com.jkstic.testHw.repositories.RecetasRepository;
import com.jkstic.testHw.validators.DiaValidators;
import com.jkstic.testHw.validators.RecetasValidators;
import com.jkstic.testHw.validators.impl.DiaValidatorsImpl;

@SpringBootTest
public class DiaValidatorsTest {

	@TestConfiguration
	static class DiaValidatorsTestContextConfiguration {
		@Bean
		public DiaValidators diaValidators() {
			return new DiaValidatorsImpl();
		}
	}

	@MockBean
	private RecetasValidators recetasValidators;

	@MockBean
	private RecetasRepository recetasRepository;

	@MockBean
	private DiaRepository diaRepository;

	@Autowired
	private DiaValidators diaValidators;

	@Test
	public void when_validatDia_then_statusExceptionDiaExist() {
		
		DiaDto dia = new DiaDto();
		
		Mockito.when(diaRepository.findByFecha(Mockito.any())).thenReturn(Optional.of(new Dia()));

		Exception exception = assertThrows(Exception.class, () -> diaValidators.createDiaValidator(dia));

		assertEquals("Este dia ya existe!", exception.getMessage());

	}
	
	@Test
	public void when_validateDia_then_statusExceptionMuchosDeayunos() throws Exception {
		
		RecetasDto receta1 = new RecetasDto();
		receta1.setNombre("name");
		receta1.setTypo(TypeRecetaEnum.DESAYUNO);
		RecetasDto receta2 = new RecetasDto();
		receta2.setNombre("name");
		receta2.setTypo(TypeRecetaEnum.CENA);
		List<RecetasDto> recetas = new ArrayList<RecetasDto>();
	
		recetas.add(receta1);
		recetas.add(receta2);
		DiaDto dia = new DiaDto();
		
		dia.setListadoRecetas(recetas);
		
		Recetas rec = new Recetas();
		rec.setTypo(TypeRecetaEnum.DESAYUNO);
		
//	    Mockito.doThrow(new Exception()).when(recetasValidators).existReceta(Mockito.anyString());
		
		Mockito.when(recetasRepository.findByNombre(Mockito.anyString())).thenReturn(Optional.of(rec));

		Exception exception = assertThrows(Exception.class, () -> diaValidators.createDiaValidator(dia));

		assertEquals("No puede añadir mas de un desayuno", exception.getMessage());

	}

}
