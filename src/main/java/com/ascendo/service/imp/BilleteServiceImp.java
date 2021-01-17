package com.ascendo.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ascendo.entity.Billete;
import com.ascendo.exception.BussinesLogicException;
import com.ascendo.repository.BilleteRepo;
import com.ascendo.service.BilleteService;

/*
 * Clase que implementa la interfaz BilleteService y permite tener el control lógico de los microservicios expuestos con sus correspondientes validaciones
 */
@Service()
public class BilleteServiceImp implements BilleteService{
	//Arreglo de datos que contiene las denominaciones permitidas para los billetes
	int[] valores=new int[]{10000, 20000, 50000, 100000};
	
	//Dependencia con el repositorio
	@Autowired
	private BilleteRepo repo;
	
	//Método que realiza la consulta en base de datos de los billetes
	@Override
	public List<Billete> get() {
		return repo.findAll();
	}
	
	//Método que guarda los billetes de acuerdos a las denominaciones permitidas
	@Override
	public Object save(Billete billete) {
		int contador=0;
		for(int i=0;i<valores.length;i++) {
			if(billete.getDenominacion()==valores[i]) {
				contador++;
			}
		}
		if(contador==0) {
			throw new BussinesLogicException("Denominación no válida");
		}
		repo.save(billete);
		return billete;
	}


}
