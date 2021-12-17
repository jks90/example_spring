package com.jkstic.testHw.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jkstic.testHw.dto.AlimentoDto;
import com.jkstic.testHw.services.AlimentosService;

@RestController
@RequestMapping(value = "/api")
public class AlimentosController {

	@Autowired
	private AlimentosService alimentosService;
	
	@GetMapping(value = "/alimentos")
	public ResponseEntity<?> viewAliments(){
		try {
			List<AlimentoDto> result = alimentosService.verAlimentos();
			return new ResponseEntity<List<AlimentoDto>>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/alimentos/id/{id}")
	public ResponseEntity<?> viewAlimentsById(@PathVariable Long id){
		try {
			AlimentoDto result = alimentosService.verAliment(id);
			return new ResponseEntity<AlimentoDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/alimentos/nombre/{nombre}")
	public ResponseEntity<?> viewAlimentsByNombre(@PathVariable String nombre){
		try {
			AlimentoDto result = alimentosService.verAliment(nombre);
			return new ResponseEntity<AlimentoDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/alimentos")
	public ResponseEntity<?> createAliment(@RequestBody AlimentoDto alimento){
		try {
			AlimentoDto result = alimentosService.createAliment(alimento);
			return new ResponseEntity<AlimentoDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/alimentos/id/{id}")
	public ResponseEntity<?> updateAliment(@PathVariable Long id, @RequestBody AlimentoDto alimento){
		try {
			AlimentoDto result = alimentosService.actualizarAlimento(id, alimento);
			return new ResponseEntity<AlimentoDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value = "/alimentos/id/{id}")
	public ResponseEntity<?> deleteAliment(@PathVariable Long id){
		try {
			alimentosService.deleteAliment(id);
			return new ResponseEntity<String>("DELETE COMPLETE", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
