package com.ascendo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ascendo.entity.Billete;
import com.ascendo.service.BilleteService;


/*
 * Clase que contiene los servicios correspondientes a la entidad Billete
 */
@RestController
@RequestMapping("/billetes/")
public class BilleteController {

	//Dependencia con la clase Service de la entidad
	@Autowired
	BilleteService service;
	
	//Método que permite generar servicio POST para guardar los billetes en sus diferentes denominaciones (10000, 20000, 50000, 100000)
	@PostMapping("/save")
	public ResponseEntity<Object> guardar(@Valid @RequestBody Billete billete){
		service.save(billete);
		return new ResponseEntity<Object>(HttpStatus.CREATED);
	}
	
	//Método que permite generar servicio GET, sirve para consultar el listado de billetes y su cantidad
	@GetMapping("/consult")
	public ResponseEntity<List<Billete>> obtener(){
		List<Billete> billetes = service.get();
		return new ResponseEntity<List<Billete>>(billetes, HttpStatus.OK);
	}
	
}
