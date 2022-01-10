package com.jkstic.testHw.rest.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jkstic.testHw.dto.DiaDto;
import com.jkstic.testHw.services.DiaService;
import com.jkstic.testHw.validators.DiaValidators;

@RestController
@RequestMapping(value = "/api")
public class DiaController {
	
	@Autowired
	private DiaService diaService;
	
	@Autowired
	private DiaValidators diaValidators;
	
	@GetMapping(value = "/dia")
	public ResponseEntity<?> viewRecetasDia(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fecha){
		try {
			DiaDto result = diaService.viewDia(fecha);
			return new ResponseEntity<DiaDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/dia")
	public ResponseEntity<?> createDia(@RequestBody DiaDto dia){
		try {
			diaValidators.createDiaValidator(dia);
			DiaDto result = diaService.createDia(dia);
			return new ResponseEntity<DiaDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/dia")
	public ResponseEntity<?> updateDia(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fecha,@RequestBody DiaDto dia){
		try {
			DiaDto result = diaService.updateDia(fecha,dia);
			return new ResponseEntity<DiaDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value = "/dia")
	public ResponseEntity<?> deleteDia(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") Date fecha){
		try {
			diaService.deleteDia(fecha);
			return new ResponseEntity<String>("DELETE COMPLET", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/dias")
	public ResponseEntity<?> viewMoreDias(@RequestParam("dateFrom") @DateTimeFormat(pattern = "dd-MM-yyyy") Date from,@RequestParam("dateTo") @DateTimeFormat(pattern = "dd-MM-yyyy") Date to){
		try {
			List<DiaDto> result = diaService.viewDias(from,to);
			return new ResponseEntity<List<DiaDto>>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
