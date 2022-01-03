package com.jkstic.testHw.rest.controller;

import java.util.List;
import java.util.Map;

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

import com.jkstic.testHw.dto.RecetasDto;
import com.jkstic.testHw.services.RecetasService;
import com.jkstic.testHw.validators.RecetasValidators;

@RestController
@RequestMapping(value = "/api")
public class RecetasController {
	
	@Autowired
	private RecetasService recetasService;
	
	@Autowired
	private RecetasValidators recetasValidator;

	@GetMapping(value = "/recetas")
	public ResponseEntity<?> viewRecetas(){
		try {
			List<RecetasDto> result = recetasService.verRecetas();
			return new ResponseEntity<List<RecetasDto>>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/recetas/id/{id}")
	public ResponseEntity<?> viewRecetasById(@PathVariable Long id){
		try {
			RecetasDto result = recetasService.verReceta(id);
			return new ResponseEntity<RecetasDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/recetas/nombre/{nombre}")
	public ResponseEntity<?> viewRecetasByNombre(@PathVariable String nombre){
		try {
			RecetasDto result = recetasService.verReceta(nombre);
			return new ResponseEntity<RecetasDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/recetas/ids")
	public ResponseEntity<?> viewIdRecetas(){
		try {
			Map<Long,String> result = recetasService.verIdRecetas();
			return new ResponseEntity<Map<Long,String>>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/recetas")
	public ResponseEntity<?> createReceta(@RequestBody RecetasDto receta){
		try {
			recetasValidator.recetasValidator(receta);
			RecetasDto result = recetasService.createReceta(receta);
			return new ResponseEntity<RecetasDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/receta/id/{id}")
	public ResponseEntity<?> updateReceta(@PathVariable Long id, @RequestBody RecetasDto receta){
		try {
			RecetasDto result = recetasService.actualizarReceta(id, receta);
			return new ResponseEntity<RecetasDto>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping(value = "/receta/id/{id}")
	public ResponseEntity<?> deleteReceta(@PathVariable Long id){
		try {
			recetasService.deleteReceta(id);
			return new ResponseEntity<String>("DELETE COMPLETE", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
