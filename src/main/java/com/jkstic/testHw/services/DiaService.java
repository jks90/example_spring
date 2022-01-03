package com.jkstic.testHw.services;

import java.util.Date;
import java.util.List;

import com.jkstic.testHw.dto.DiaDto;

public interface DiaService {
	
	public DiaDto viewDia(Date fecha) throws Exception;
	public DiaDto createDia(DiaDto dia) throws Exception;
	public DiaDto updateDia(Date fecha, DiaDto dia) throws Exception;
	public void deleteDia(Date fecha) throws Exception;
	public List<DiaDto> viewDias(Date from, Date to) throws Exception;

}
