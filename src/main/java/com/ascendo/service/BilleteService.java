package com.ascendo.service;

import java.util.List;

import com.ascendo.entity.Billete;

/*
 * Interfaz que contiene los métodos que serán implementados por la capa Service de la entidad Billete
 */
public interface BilleteService {

	public List<Billete> get();
		
	public  Object save(Billete billete);
	
}
