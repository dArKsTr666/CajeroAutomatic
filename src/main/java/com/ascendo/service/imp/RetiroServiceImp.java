package com.ascendo.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ascendo.entity.Billete;
import com.ascendo.entity.Retiro;
import com.ascendo.exception.BussinesLogicException;
import com.ascendo.repository.BilleteRepo;
import com.ascendo.repository.RetiroRepo;
import com.ascendo.service.RetiroService;

/*
 * Clase que implementa la interfaz BilleteService y permite tener el control lógico de los microservicios expuestos con sus correspondientes validaciones
 */
@Service
public class RetiroServiceImp implements RetiroService {

	//Dependencia con el repositorio Retiro
	@Autowired
	private RetiroRepo repoRetiro;
	//Dependencia con el repositorio Billete
	@Autowired
	private BilleteRepo repoBillete;
	
	/*
	 * Este método se encarga de guarda los valores correspondientes al retiro
	 * Para ellos primero realiza validaciones de denominaciones, valor a retirar, cantidades disponibles. 
	 * Si la información ingresada es válida realiza el descuento en las diferentes cantidades de billetes
	 * y le devuelve la información de cuantos billetes fueron retirados
	 */
	@Override
	public Object save(Retiro retiro) {
		long valor = retiro.getValor();
		if (valor % 10000 != 0) {
			throw new BussinesLogicException("VAlor no permitido");
		}
		List<String> listado = new ArrayList<>();
		boolean terminoProceso = true;
		int cantidadesDes = 0;
		List<Billete> billetes = repoBillete.findAll(Sort.by("denominacion").descending());
		int tamano = billetes.size();
		int cantidadExistente = 0;
		int i = 0;
		do {
			if (billetes.get(i).getCantidad() > 0 && billetes.get(i).getCantidad()-cantidadesDes>0) {
				if (valor >= billetes.get(i).getDenominacion()) {
					valor = valor - billetes.get(i).getDenominacion();
					cantidadesDes++;
				} else {
					cantidadExistente = billetes.get(i).getCantidad();
					billetes.get(i).setCantidad(cantidadExistente - cantidadesDes);
					repoBillete.save(billetes.get(i));
					if(cantidadesDes!=0)
						listado.add(" -> " + billetes.get(i).getDenominacion() + "  " + cantidadesDes);
					i++;
					cantidadesDes = 0;
				}
			} else {
				i++;
			}
			if (i >= tamano && valor > 0) {
				return "No hay fondos suficientes";
			}
			if (valor == 0) {
				terminoProceso = false;
				repoRetiro.save(retiro);
				cantidadExistente = billetes.get(i).getCantidad();
			    billetes.get(i).setCantidad(cantidadExistente - cantidadesDes);
				repoBillete.save(billetes.get(i));
				if(cantidadesDes != 0)
					listado.add(" -> " + billetes.get(i).getDenominacion() + "  " + cantidadesDes);
			}
		} while (terminoProceso);

		return listado;

	}

}
