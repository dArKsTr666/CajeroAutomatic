package com.ascendo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


/*
 * Clase de tipo entidad Billete
 */
@Entity
@Table(name = "billete")
public class Billete {
	
	//Valor autoincrementable identificador de la entidad
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private Integer idBillete;
	
	//Atributo donde se guarda la denominación de los billetes
	@Column(name = "denominacion", nullable = false)
	@Min(value = 10000, message = "La denominacion debe ser mayor a $10000")
	@Max(value = 100000, message = "La denominacion debe ser menor a $100000")
	private Long denominacion;

	//Atributo donde se guarda la cantidad de billetes disponibles para cada denominacion de billete
	@Column(name = "cantidad", nullable = false)
	private Integer cantidad;

	//Getters & setters de los diferentes atributos
	public Integer getIdBillete() {
		return idBillete;
	}

	public void setIdBillete(Integer idBillete) {
		this.idBillete = idBillete;
	}

	public Long getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(Long denominacion) {
		this.denominacion = denominacion;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
