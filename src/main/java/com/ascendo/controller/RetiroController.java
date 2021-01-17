package com.ascendo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ascendo.entity.Retiro;
import com.ascendo.service.RetiroService;

/*
 * Clase que contiene los servicios correspondientes a la entidad Retiro
 */
@RestController
@RequestMapping("/retiros/")
public class RetiroController {
	
	//Dependencia con la clase Service de la entidad
	@Autowired
	RetiroService service;
	
	//Método que permite generar servicio POST para realizar el retiro del cajero
	@PostMapping("/save")
	public ResponseEntity<Object> retirar(@Valid @RequestBody Retiro retiro){
		Object objeto=service.save(retiro);
		return new ResponseEntity<Object>(objeto, HttpStatus.CREATED);
	}
}
