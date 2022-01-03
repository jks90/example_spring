package com.jkstic.testHw.validators;

import com.jkstic.testHw.dto.RecetasDto;

public interface RecetasValidators {
	
	public void recetasValidator(RecetasDto receta) throws Exception;
	public void existReceta(String name) throws Exception;
}
